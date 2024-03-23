package com.app.jujamanru.service;

import com.app.jujamanru.domain.post.model.Scrap;
import com.app.jujamanru.dto.post.*;
import org.springframework.data.domain.Page;

public interface PostService {

    Page<PostListItemDto> getPosts(PostSearchRequest postSearchRequest);

    PostDto getPost(Long postId);

    Page<ScrapListItemDto> getScraps(PostSearchRequest postSearchRequest);

    Long save(PostSaveRequest request);

    Long update(Long id, PostSaveRequest request);

    void delete(Long postId);

    void updateViewCount(Long postId);

    Long scrapSave(ScrapSaveRequest request);

    void deleteScrap(Long scrapId);

}
