package com.app.jujamanru.domain.post.model;

import com.app.jujamanru.domain.team.model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long teamId;
    private Boolean isNotice;
    private Boolean mustRead;
    @Column(columnDefinition="TEXT")
    private String text;
    private Integer viewCount;
    private Integer replyCount;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;


    @Builder
    public Post(String title, Long teamId, Boolean isNotice, Boolean mustRead,
                String text, Integer viewCount, Integer replyCount, String createdBy, LocalDateTime createdDatetime) {
        this.title = title;
        this.text = text;
        this.isNotice = isNotice;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
        this.createdBy = createdBy;
        this.createdDatetime = createdDatetime;
        this.teamId = teamId;
        this.mustRead = mustRead;
    }

    public Post changeTitle(String title) {
        this.text = title;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

    public Post changeText(String text) {
        this.text = text;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

}
