package com.app.jujamanru.domain.post.repository;

import com.app.jujamanru.dto.post.PostListItemDto;
import com.app.jujamanru.dto.post.PostSearchRequest;
import com.app.jujamanru.dto.post.ScrapListItemDto;
import org.springframework.data.domain.Page;

public interface CustomPostRepository {

    Page<PostListItemDto> findPageBySearch(PostSearchRequest request);

    Page<ScrapListItemDto> findScrapPageBySearch(PostSearchRequest request);
}
