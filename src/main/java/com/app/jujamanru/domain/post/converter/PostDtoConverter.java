package com.app.jujamanru.domain.post.converter;

import com.app.jujamanru.domain.post.model.Post;
import com.app.jujamanru.domain.reply.converter.ReplyDtoConverter;
import com.app.jujamanru.domain.reply.model.Reply;
import com.app.jujamanru.domain.team.model.Team;
import com.app.jujamanru.dto.post.PostDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class PostDtoConverter {
    private final Post origin;
    private final Team team;
    private final Long replyCount;
    private final List<Reply> replies;

    public PostDto convert() {
        var teamId = Objects.isNull(team) ? null : team.getId();
        var teamName = Objects.isNull(team) ? null : team.getName();

        return PostDto.builder()
                .id(origin.getId())
                .title(origin.getTitle())
                .text(origin.getText())
                .teamId(teamId)
                .teamName(teamName)
                .viewCount(origin.getViewCount())
                .replyCount(replyCount)
                .createdBy(origin.getCreatedBy())
                .modifiedDatetime(Objects.requireNonNullElse(origin.getModifiedDatetime(), origin.getCreatedDatetime()))
                .isUpdated(Objects.nonNull(origin.getModifiedDatetime()))
                .isNotice(origin.getIsNotice())
                .mustRead(origin.getMustRead())
                .replies(replies.stream()
                        .map(ReplyDtoConverter::new)
                        .map(ReplyDtoConverter::convert)
                        .toList())
                .build();
    }
}
