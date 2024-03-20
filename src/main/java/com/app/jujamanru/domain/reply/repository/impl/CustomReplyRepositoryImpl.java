package com.app.jujamanru.domain.reply.repository.impl;


import com.app.jujamanru.domain.reply.repository.CustomReplyRepository;
import com.app.jujamanru.dto.post.PostListItemDto;
import com.app.jujamanru.dto.post.PostSearchRequest;
import com.app.jujamanru.dto.reply.ReplyDto;
import com.app.jujamanru.dto.reply.ReplySearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.app.jujamanru.domain.post.model.QPost.post;
import static com.app.jujamanru.domain.reply.model.QReply.reply;
import static com.app.jujamanru.domain.team.model.QTeam.team;

@Repository
public class CustomReplyRepositoryImpl implements CustomReplyRepository {
    private final JPAQueryFactory queryFactory;

    public CustomReplyRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<ReplyDto> findPageBySearch(ReplySearchRequest request) {
        var pageRequest = PageRequest.of(request.getPage(), request.getSize());
        var result = queryFactory
                .from(reply)
                .join(post).on(reply.postId.eq(post.id))
                .join(team).on(post.teamId.eq(team.id))
                .select(Projections.constructor(ReplyDto.class,
                        reply.id,
                        reply.postId,
                        team.id,
                        team.name,
                        reply.text,
                        reply.createdBy,
                        new CaseBuilder().when(post.modifiedDatetime.isNotNull())
                                .then(post.modifiedDatetime)
                                .otherwise(post.createdDatetime),
                        new CaseBuilder().when(post.modifiedDatetime.isNotNull())
                                .then(true)
                                .otherwise(false)))
                .where(this.getSearchPredicate(request))
                .orderBy(reply.id.desc())
                .limit(request.getSize()).offset((long)request.getPage() * (long)request.getSize())
                .fetchResults();
        return new PageImpl(result.getResults(), pageRequest, result.getTotal());
    }

    private BooleanBuilder getSearchPredicate(ReplySearchRequest request) {
        var booleanBuilder = new BooleanBuilder();

        if (Objects.nonNull(request.getPostId())) {
            booleanBuilder.and(reply.postId.eq(request.getPostId()));
        }

        if (Objects.nonNull(request.getUserId())) {
            booleanBuilder.and(reply.createdBy.eq(request.getUserId()));
        }

        return booleanBuilder;
    }
}
