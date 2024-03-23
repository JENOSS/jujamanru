package com.app.jujamanru.controller;

import com.app.jujamanru.dto.post.*;
import com.app.jujamanru.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostListItemDto>> getPosts(PostSearchRequest request) {
        return ResponseEntity.ok(postService.getPosts(request));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping("/scraps")
    public ResponseEntity<Page<ScrapListItemDto>> getScraps(PostSearchRequest request) {
        return ResponseEntity.ok(postService.getScraps(request));
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody PostSaveRequest request) {
        return ResponseEntity.ok(postService.save(request));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Long> update(@PathVariable("postId") Long postId, @RequestBody PostSaveRequest request) {
        return ResponseEntity.ok(postService.update(postId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/view-count")
    public ResponseEntity<Void> updateViewCount(@PathVariable("postId") Long postId) {
        postService.updateViewCount(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/scraps")
    public ResponseEntity<Long> scrapSave(@RequestBody ScrapSaveRequest request) {
        return ResponseEntity.ok(postService.scrapSave(request));
    }

    @DeleteMapping("/scraps/{scrapId}")
    public ResponseEntity<Void> deleteScrap(@PathVariable("scrapId") Long scrapId) {
        postService.deleteScrap(scrapId);
        return ResponseEntity.ok().build();
    }
}
