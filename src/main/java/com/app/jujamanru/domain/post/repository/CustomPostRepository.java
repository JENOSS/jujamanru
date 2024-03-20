package com.app.jujamanru.domain.post.repository;

import com.app.jujamanru.dto.post.PostListItemDto;
import com.app.jujamanru.dto.post.PostSearchRequest;
import org.springframework.data.domain.Page;

public interface CustomPostRepository {

    Page<PostListItemDto> findPageBySearch(PostSearchRequest request);

    Page<PostListItemDto> findScrapPageBySearch(PostSearchRequest request);
}
