package com.app.jujamanru.domain.reply.repository;

import com.app.jujamanru.domain.reply.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long>, CustomReplyRepository {
    List<Reply> findTop10ByPostIdOrderByIdDesc(Long postId);
    Long countByPostId(Long postId);
}
