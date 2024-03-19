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
    @Column(columnDefinition="TEXT")
    private String text;
    private Integer viewCount;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;


    @Builder
    public Post(String title, Long teamId, Boolean isNotice, String text, Integer viewCount, String createdBy, LocalDateTime createdDatetime, Team team) {
        this.title = title;
        this.text = text;
        this.isNotice = isNotice;
        this.viewCount = viewCount;
        this.createdBy = createdBy;
        this.createdDatetime = createdDatetime;
        this.teamId = teamId;
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
