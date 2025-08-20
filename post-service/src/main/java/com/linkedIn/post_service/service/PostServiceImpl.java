package com.linkedIn.post_service.service;

import com.linkedIn.post_service.dto.PostCreateRequestDto;
import com.linkedIn.post_service.dto.PostDto;
import com.linkedIn.post_service.entity.Post;
import com.linkedIn.post_service.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl {

    @Autowired
    private PostRepository postRepository;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = new Post();
        BeanUtils.copyProperties(postCreateRequestDto, post);
        post.setUserId(userId);
        Post save = postRepository.save(post);
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(save, postDto);
        return postDto;
    }
}
