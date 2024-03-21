package com.app.jujamanru.service.impl;

import com.app.jujamanru.domain.post.converter.PostDtoConverter;
import com.app.jujamanru.domain.post.repository.PostRepository;
import com.app.jujamanru.domain.reply.repository.ReplyRepository;
import com.app.jujamanru.domain.team.repository.TeamRepository;
import com.app.jujamanru.dto.post.PostDto;
import com.app.jujamanru.dto.post.PostListItemDto;
import com.app.jujamanru.dto.post.PostSearchRequest;
import com.app.jujamanru.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Page<PostListItemDto> getPosts(PostSearchRequest postSearchRequest) {
        var result = postRepository.findPageBySearch(postSearchRequest);
        return result;
    }

    @Override
    public PostDto getPost(Long postId) {
        var post = postRepository.findById(postId).orElseThrow();
        var team = Objects.nonNull(post.getTeamId())
                ? teamRepository.findById(post.getTeamId()).orElseThrow()
                : null;
        var replies = replyRepository.findTop10ByPostIdOrderByIdDesc(postId);
        var replyCount = replyRepository.countByPostId(postId);
        return new PostDtoConverter(post, team, replyCount, replies).convert();
    }

    @Override
    public Page<PostListItemDto> getScraps(PostSearchRequest postSearchRequest) {
        return postRepository.findScrapPageBySearch(postSearchRequest);
    }
}
