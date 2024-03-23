package com.app.jujamanru.controller;

import com.app.jujamanru.dto.reply.ReplyDto;
import com.app.jujamanru.dto.reply.ReplySaveRequest;
import com.app.jujamanru.dto.reply.ReplySearchRequest;
import com.app.jujamanru.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping
    public ResponseEntity<Page<ReplyDto>> getReplies(ReplySearchRequest request) {
        return ResponseEntity.ok(replyService.getReplies(request));
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody ReplySaveRequest request) {
        return ResponseEntity.ok(replyService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable("id") Long id,@RequestBody ReplySaveRequest request) {
        return ResponseEntity.ok(replyService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        replyService.delete(id);
        return ResponseEntity.ok().build();
    }

}
