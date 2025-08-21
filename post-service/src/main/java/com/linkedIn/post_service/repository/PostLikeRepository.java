package com.linkedIn.post_service.repository;

import com.linkedIn.post_service.entity.PostLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existByPostIdAndUSerId(Long postId, Long userId);

    @Transactional
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
