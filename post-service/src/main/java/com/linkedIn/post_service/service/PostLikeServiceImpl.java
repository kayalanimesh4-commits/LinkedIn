package com.linkedIn.post_service.service;

import com.linkedIn.post_service.entity.Post;
import com.linkedIn.post_service.entity.PostLike;
import com.linkedIn.post_service.exception.BadRequestException;
import com.linkedIn.post_service.exception.ResourceNotFoundException;
import com.linkedIn.post_service.repository.PostLikeRepository;
import com.linkedIn.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeServiceImpl {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;


    public void likePost(Long postId, Long userId){

        if(!(postRepository.existsById(postId))) throw new ResourceNotFoundException("You Already liked this Post");

        boolean existByPostIdAndUSerId = postLikeRepository.existByPostIdAndUSerId(postId, userId);
        if((existByPostIdAndUSerId)){
            throw new BadRequestException("Post Already liked by you");
        }
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
    }


    public void unlikePost(Long postId, Long userId) {
        if(!(postRepository.existsById(postId))) throw new ResourceNotFoundException("Post was not Found");
        boolean existed = postLikeRepository.existByPostIdAndUSerId(postId, userId);
        if(!existed){
            throw new BadRequestException("Post cannot be disliked since post not liked");
        }
        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
        log.info("Post disliked successfully", postId);

    }
}
