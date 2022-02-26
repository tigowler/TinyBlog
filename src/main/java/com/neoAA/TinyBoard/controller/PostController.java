package com.neoAA.TinyBoard.controller;

import com.neoAA.TinyBoard.model.Post;
import com.neoAA.TinyBoard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public String post(Model model, @RequestParam(required = true) Long id,
                       HttpServletRequest request){
        Post post = postRepository.findById(id).orElse(null);
        String referer = request.getHeader("Referer");
        model.addAttribute("post", post);
        model.addAttribute("referer", referer);
        return "post/post";
    }

    @GetMapping("/list")
    public String postList(Model model, @PageableDefault(size = 2) Pageable pageable,
                           @RequestParam(required = false, defaultValue = "") String searchText){
        Page<Post> posts = postRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, posts.getPageable().getPageNumber()-4);
        int endPage = Math.min(posts.getTotalPages(), posts.getPageable().getPageNumber()+4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts", posts);

        return "post/list";
    }

    @GetMapping("/form")
    public String postForm(Model model, @RequestParam(required = false) Long id){
        if(id==null){
            model.addAttribute("post", new Post());
        } else {
            Post post = postRepository.findById(id).orElse(null);
            model.addAttribute("post", post);
        }
        return "post/post-form";
    }

    @PostMapping("/form")
    public String form(@Valid @ModelAttribute Post post, BindingResult bindingResult){
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()){
            return "post/post-form";
        }
        postRepository.save(post);
        return "redirect:/post/list";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return "redirect:/post";
    }
}
