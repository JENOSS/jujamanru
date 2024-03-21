package com.app.jujamanru.domain.post.repository.impl;

import com.app.jujamanru.domain.post.repository.CustomPostRepository;
import com.app.jujamanru.dto.post.PostListItemDto;
import com.app.jujamanru.dto.post.PostSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.app.jujamanru.domain.post.model.QPost.post;
import static com.app.jujamanru.domain.post.model.QScrap.scrap;
import static com.app.jujamanru.domain.reply.model.QReply.reply;
import static com.app.jujamanru.domain.team.model.QTeam.team;

@Repository
public class CustomPostRepositoryImpl implements CustomPostRepository {
    private final JPAQueryFactory queryFactory;

    public CustomPostRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<PostListItemDto> findPageBySearch(PostSearchRequest request) {
        var pageRequest = PageRequest.of(request.getPage(), request.getSize());
        var result = queryFactory
                .from(post)
                .leftJoin(team).on(post.teamId.eq(team.id))
                .select(Projections.constructor(PostListItemDto.class,
                                post.id,
                                post.title,
                                ExpressionUtils.as(team.id, "teamId"),
                                ExpressionUtils.as(team.name, "teamName"),
                                post.viewCount,
                                ExpressionUtils.as(JPAExpressions.select(reply.count())
                                                 .from(reply)
                                                 .where(reply.postId.eq(post.id)), "replyCount"),
                                post.createdBy,
                                new CaseBuilder().when(post.modifiedDatetime.isNotNull())
                                                 .then(post.modifiedDatetime)
                                                 .otherwise(post.createdDatetime),
                                post.isNotice,
                                post.mustRead))
                .where(this.getSearchPredicate(request))
                .orderBy(post.id.desc())
                .limit(request.getSize()).offset((long)request.getPage() * (long)request.getSize())
                .fetchResults();
        return new PageImpl(result.getResults(), pageRequest, result.getTotal());
    }

    private BooleanBuilder getSearchPredicate(PostSearchRequest request) {
        var booleanBuilder = new BooleanBuilder();

        if (Objects.nonNull(request.getTeamId())) {
            booleanBuilder.and(post.teamId.eq(request.getTeamId()));
        }

        if (Objects.nonNull(request.getIsNotice()) && request.getIsNotice()) {
            booleanBuilder.and(post.isNotice.eq(true));
        }

        if (Objects.nonNull(request.getMustRead()) && request.getMustRead()) {
            booleanBuilder.and(post.mustRead.eq(true));
        }

        if (Objects.nonNull(request.getUserId())) {
            booleanBuilder.and(post.createdBy.eq(request.getUserId()));
        }

        return booleanBuilder;
    }

    @Override
    public Page<PostListItemDto> findScrapPageBySearch(PostSearchRequest request) {
        var pageRequest = PageRequest.of(request.getPage(), request.getSize());
        var result = queryFactory
                .from(scrap)
                .join(post).on(post.id.eq(scrap.postId))
                .leftJoin(team).on(post.teamId.eq(team.id))
                .select(Projections.constructor(PostListItemDto.class,
                        post.id,
                        post.title,
                        ExpressionUtils.as(team.id, "teamId"),
                        ExpressionUtils.as(team.name, "teamName"),
                        post.viewCount,
                        ExpressionUtils.as(JPAExpressions.select(reply.count())
                                    .from(reply)
                                    .where(reply.postId.eq(post.id)), "replyCount"),
                        post.createdBy,
                        new CaseBuilder().when(post.modifiedDatetime.isNotNull())
                                .then(post.modifiedDatetime)
                                .otherwise(post.createdDatetime),
                        post.isNotice,
                        post.mustRead))
                .where(scrap.userId.eq(request.getUserId()))
                .orderBy(post.id.desc())
                .limit(request.getSize()).offset((long)request.getPage() * (long)request.getSize())
                .fetchResults();
        return new PageImpl(result.getResults(), pageRequest, result.getTotal());
    }
}
