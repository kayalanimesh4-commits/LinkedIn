package com.linkedIn.post_service.repository;

import com.linkedIn.post_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.userId = ?1")
    List<Post> findByUserId(Long userId);
}
