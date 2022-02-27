package com.neoAA.TinyBoard.controller.api;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @Autowired
    private PostRepository postRepository;

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
        postRepository.deleteById(id);
    }
}
