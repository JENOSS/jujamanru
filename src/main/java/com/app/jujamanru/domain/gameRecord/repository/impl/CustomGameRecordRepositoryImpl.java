package com.app.jujamanru.domain.gameRecord.repository.impl;

import com.app.jujamanru.domain.gameRecord.repository.CustomGameRecordRepository;
import com.app.jujamanru.domain.team.model.QTeam;
import com.app.jujamanru.dto.gameRecord.GameRecordDto;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.CollectionExpressionBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.ListExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.criterion.EmptyExpression;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import static com.app.jujamanru.domain.gameRecord.model.QGameRecord.gameRecord;
import static com.app.jujamanru.domain.team.model.QTeam.team;
@Repository
public class CustomGameRecordRepositoryImpl implements CustomGameRecordRepository {
    private final JPAQueryFactory queryFactory;

    public CustomGameRecordRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<GameRecordDto> getGameRecords(String userId) {
        var opponentTeam = new QTeam("opponentTeam");
        return queryFactory
                .from(gameRecord)
                .join(team).on(gameRecord.myTeamId.eq(team.id))
                .join(opponentTeam).on(gameRecord.opponentTeamId.eq(opponentTeam.id))
                .select(Projections.constructor(GameRecordDto.class,
                        gameRecord.id,
                        gameRecord.matchDate,
                        gameRecord.myTeamId,
                        team.name.as("myTeamName"),
                        gameRecord.opponentTeamId,
                        opponentTeam.name.as("opponentTeamName"),
                        gameRecord.gameResult.stringValue(),
                        gameRecord.text,
                        gameRecord.createdBy,
                        gameRecord.createdDatetime,
                        Expressions.as(ConstantImpl.create(Collections.emptyList()), "images")))
                .where(gameRecord.createdBy.eq(userId))
                .fetch();
    }
}
