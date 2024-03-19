package com.app.jujamanru.domain.post.repository;

import com.app.jujamanru.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
