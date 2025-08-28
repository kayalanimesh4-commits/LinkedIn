package com.linkedIn.post_service.service;

import com.linkedIn.post_service.auth.UserContextLoader;
import com.linkedIn.post_service.clients.ConnectionClient;
import com.linkedIn.post_service.dto.PersonDto;
import com.linkedIn.post_service.dto.PostCreateRequestDto;
import com.linkedIn.post_service.dto.PostDto;
import com.linkedIn.post_service.entity.Post;
import com.linkedIn.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ConnectionClient connectionClient;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = new Post();
        BeanUtils.copyProperties(postCreateRequestDto, post);
        post.setUserId(userId);
        Post save = postRepository.save(post);
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(save, postDto);
        return postDto;
    }

    public PostDto getPostById(Long postId) {
        Long userId = UserContextLoader.getCurrentUserId();
        Post idFound = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Id not Found"));
        List<PersonDto> firstConnection = connectionClient.getFirstConnection();

//TODO kafka
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(idFound, postDto);
        return postDto;
    }

    public List<PostDto> getAllPostOfTheUser(Long userId) {
        List<Post> byUserId = postRepository.findByUserId(userId);
        return byUserId.stream().map(post -> {
            PostDto dto = new PostDto();
            BeanUtils.copyProperties(byUserId, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
