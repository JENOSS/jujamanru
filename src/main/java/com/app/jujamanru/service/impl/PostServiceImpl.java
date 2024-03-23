package com.app.jujamanru.service.impl;

import com.app.jujamanru.domain.post.converter.PostConverter;
import com.app.jujamanru.domain.post.converter.PostDtoConverter;
import com.app.jujamanru.domain.post.converter.PostUpdater;
import com.app.jujamanru.domain.post.model.Post;
import com.app.jujamanru.domain.post.model.Scrap;
import com.app.jujamanru.domain.post.repository.PostRepository;
import com.app.jujamanru.domain.post.repository.ScrapRepository;
import com.app.jujamanru.domain.reply.repository.ReplyRepository;
import com.app.jujamanru.domain.team.repository.TeamRepository;
import com.app.jujamanru.dto.post.*;
import com.app.jujamanru.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TeamRepository teamRepository;
    private final ReplyRepository replyRepository;
    private final ScrapRepository scrapRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<PostListItemDto> getPosts(PostSearchRequest postSearchRequest) {
        return postRepository.findPageBySearch(postSearchRequest);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public Page<ScrapListItemDto> getScraps(PostSearchRequest postSearchRequest) {
        return postRepository.findScrapPageBySearch(postSearchRequest);
    }

    @Override
    @Transactional
    public Long save(PostSaveRequest request) {
        var entity = postRepository.save(new PostConverter(request).convert());
        return entity.getId();
    }

    @Override
    @Transactional
    public Long update(Long id, PostSaveRequest request) {
        return postRepository.findById(id)
                .map(entity -> new PostUpdater(entity, request).update())
                .map(postRepository::save)
                .map(Post::getId)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    @Transactional
    public void updateViewCount(Long postId) {
        postRepository.findById(postId)
                .ifPresent(post -> {
                    post.updateViewCount();
                    postRepository.save(post);
                });
    }

    @Override
    @Transactional
    public Long scrapSave(ScrapSaveRequest request) {
        return scrapRepository.save(new Scrap(request.getPostId(), request.getUserId())).getId();
    }

    @Override
    @Transactional
    public void deleteScrap(Long scrapId) {
        scrapRepository.deleteById(scrapId);
    }
}
