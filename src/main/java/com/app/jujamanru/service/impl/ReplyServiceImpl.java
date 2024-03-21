package com.app.jujamanru.service.impl;

import com.app.jujamanru.domain.reply.repository.ReplyRepository;
import com.app.jujamanru.dto.reply.ReplyDto;
import com.app.jujamanru.dto.reply.ReplySearchRequest;
import com.app.jujamanru.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
    public Page<ReplyDto> getReplies(ReplySearchRequest request) {
        return replyRepository.findPageBySearch(request);
    }
}
