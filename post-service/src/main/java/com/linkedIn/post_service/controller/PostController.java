package com.linkedIn.post_service.controller;

import com.linkedIn.post_service.auth.UserContextLoader;
import com.linkedIn.post_service.dto.PostCreateRequestDto;
import com.linkedIn.post_service.dto.PostDto;
import com.linkedIn.post_service.entity.Post;
import com.linkedIn.post_service.service.PostServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto){
        Long userId = UserContextLoader.getCurrentUserId();
        PostDto createPost= postService.createPost(postCreateRequestDto, userId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);

    }

//    @GetMapping
//    public ResponseEntity<List<PostDto>> getAllPost(){
//        List<PostDto> allPost = postService.getAllPost();
//        return new ResponseEntity<>(allPost,HttpStatus.OK);
//    }

    @GetMapping("{/postId}")
    public ResponseEntity<PostDto> getPostById (@PathVariable Long postId){
       PostDto postDto =postService.getPostById(postId);
       return ResponseEntity.ok(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId){
        List<PostDto> allPostOfTheUser = postService.getAllPostOfTheUser(userId);
        return new ResponseEntity<>(allPostOfTheUser, HttpStatus.OK);
    }


}
