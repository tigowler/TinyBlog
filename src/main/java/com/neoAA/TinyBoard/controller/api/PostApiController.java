package com.neoAA.TinyBoard.controller.api;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.model.User;
import com.neoAA.TinyBoard.repository.PostRepository;
import com.neoAA.TinyBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/post")
    List<Post> all(@RequestParam(required = false, defaultValue = "") String title,
                   @RequestParam(required = false, defaultValue = "") String content){
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
            return postRepository.findAll();
        }
        else {
            return postRepository.findByTitleOrContent(title, content);
        }
    }

    @PostMapping("/post")
    Post newPost(@RequestBody Post newPost){
        return postRepository.save(newPost);
    }

    //LOVE
    @Secured("ROLE_USER")
    @PutMapping("/post/love/{id}")
    void lovePost(@PathVariable Long id, Authentication authentication){
        //auth 사용자 가져오기
        User user = userRepository.findByUsername(authentication.getName());
        Post post = postRepository.findById(id).orElse(null);

        user.getPostsLoved().add(post);
        userRepository.save(user);
    }

    //UNLOVE
    @Secured("ROLE_USER")
    @PutMapping("/post/unlove/{id}")
    void unLovePost(@PathVariable Long id, Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName());
        List<Post> newLovedPosts = new ArrayList<>();
        for (Post p : user.getPostsLoved()){
            if (Objects.equals(p.getId(), id)) continue;
            newLovedPosts.add(p);
        }

        user.getPostsLoved().clear();
        user.getPostsLoved().addAll(newLovedPosts);
        userRepository.save(user);
    }

    @GetMapping("/post/{id}")
    Post one(@PathVariable Long id){
        return postRepository.findById(id).orElse(null);
    }

    @PutMapping("/post/{id}")
    Post replacePost(@RequestBody Post newPost, @PathVariable Long id){
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setContent(newPost.getContent());
                    return postRepository.save(post);
                })
                .orElseGet(()->{
                    newPost.setId(id);
                    return postRepository.save(newPost);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/post/{id}")
    void deletePost(@PathVariable Long id){
        Post post = postRepository.findById(id).orElse(null);
        List<Post> postList = post.getUser().getPost();
        List<Post> newPosts = new ArrayList<>();
        for (Post p: postList){
            if (p.getId()!=id){
                newPosts.add(p);
            }
        }
        post.getUser().setPost(newPosts);

        List<User> userList = userRepository.findAll();
        for (User user: userList){
            List<Post> posts = user.getPostsLoved();
            List<Post> newPostList = new ArrayList<>();
            for (Post p: posts){
                if (p.getId()!=id){
                    newPostList.add(p);
                }
            }
            user.setPostsLoved(newPostList);
        }
        postRepository.deleteById(id);
    }
}
