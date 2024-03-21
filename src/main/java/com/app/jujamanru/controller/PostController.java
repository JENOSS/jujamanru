package com.app.jujamanru.controller;

import com.app.jujamanru.dto.post.PostDto;
import com.app.jujamanru.dto.post.PostListItemDto;
import com.app.jujamanru.dto.post.PostSearchRequest;
import com.app.jujamanru.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<PostListItemDto>> getScraps(PostSearchRequest request) {
        return ResponseEntity.ok(postService.getScraps(request));
    }
}
