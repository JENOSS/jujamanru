package com.app.jujamanru.controller;

import com.app.jujamanru.dto.reply.ReplyDto;
import com.app.jujamanru.dto.reply.ReplySearchRequest;
import com.app.jujamanru.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping
    public ResponseEntity<Page<ReplyDto>> getReplies(ReplySearchRequest request) {
        return ResponseEntity.ok(replyService.getReplies(request));
    }
}
