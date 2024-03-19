package com.app.jujamanru.domain.reply.model;

import com.app.jujamanru.domain.post.model.Post;
import com.app.jujamanru.domain.team.model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT")
    private String text;
    private Long postId;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;

    @Builder
    public Reply(String text, Long postId, String createdBy, LocalDateTime createdDatetime) {
        this.text = text;
        this.postId = postId;
        this.createdBy = createdBy;
        this.createdDatetime = createdDatetime;
    }

    public Reply changeText(String text) {
        this.text = text;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

}
