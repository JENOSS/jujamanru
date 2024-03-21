package com.app.jujamanru.service;

import com.app.jujamanru.dto.reply.ReplyDto;
import com.app.jujamanru.dto.reply.ReplySearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyService {
    Page<ReplyDto> getReplies(ReplySearchRequest request);
}
