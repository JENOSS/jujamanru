package com.app.jujamanru.service.impl;

import com.app.jujamanru.domain.reply.converter.ReplyConverter;
import com.app.jujamanru.domain.reply.converter.ReplyUpdater;
import com.app.jujamanru.domain.reply.model.Reply;
import com.app.jujamanru.domain.reply.repository.ReplyRepository;
import com.app.jujamanru.dto.reply.ReplyDto;
import com.app.jujamanru.dto.reply.ReplySaveRequest;
import com.app.jujamanru.dto.reply.ReplySearchRequest;
import com.app.jujamanru.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<ReplyDto> getReplies(ReplySearchRequest request) {
        return replyRepository.findPageBySearch(request);
    }


    @Override
    @Transactional
    public Long save(ReplySaveRequest request) {
        var entity = replyRepository.save(new ReplyConverter(request).convert());
        return entity.getId();
    }

    @Override
    @Transactional
    public Long update(Long id, ReplySaveRequest request) {
        return replyRepository.findById(id)
                .map(entity -> new ReplyUpdater(entity, request).update())
                .map(replyRepository::save)
                .map(Reply::getId)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
