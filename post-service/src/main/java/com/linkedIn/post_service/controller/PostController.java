package com.linkedIn.post_service.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest){
       PostDto createPost= postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);

    }
}
