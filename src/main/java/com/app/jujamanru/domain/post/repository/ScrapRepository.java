package com.app.jujamanru.domain.post.repository;

import com.app.jujamanru.domain.post.model.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findByUserIdAndPostId(String userId, Long postId);
}
