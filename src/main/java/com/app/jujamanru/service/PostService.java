package com.app.jujamanru.service;

import com.app.jujamanru.dto.post.PostDto;
import com.app.jujamanru.dto.post.PostListItemDto;
import com.app.jujamanru.dto.post.PostSaveRequest;
import com.app.jujamanru.dto.post.PostSearchRequest;
import org.springframework.data.domain.Page;

public interface PostService {

    Page<PostListItemDto> getPosts(PostSearchRequest postSearchRequest);

    PostDto getPost(Long postId);

    Page<PostListItemDto> getScraps(PostSearchRequest postSearchRequest);

    Long save(PostSaveRequest request);

    Long update(Long id, PostSaveRequest request);

    void delete(Long postId);

    void updateViewCount(Long postId);

    Long scrapSave(String userId, Long postId);

    void deleteScrap(Long scrapId);

}
