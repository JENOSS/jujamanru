package com.app.jujamanru.domain.reply.repository;

import com.app.jujamanru.dto.reply.ReplyDto;
import com.app.jujamanru.dto.reply.ReplySearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

public interface CustomReplyRepository {

    Page<ReplyDto> findPageBySearch(ReplySearchRequest request);
}
