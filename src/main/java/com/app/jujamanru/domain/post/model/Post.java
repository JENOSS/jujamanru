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
    private String createdBy;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;


    @Builder
    public Post(String title, Long teamId, Boolean isNotice, Boolean mustRead,
                String text, Integer viewCount,String createdBy, LocalDateTime createdDatetime) {
        this.title = title;
        this.text = text;
        this.isNotice = isNotice;
        this.viewCount = viewCount;
        this.createdBy = createdBy;
        this.createdDatetime = createdDatetime;
        this.teamId = teamId;
        this.mustRead = mustRead;
    }

    public Post changeTitle(String title) {
        this.title = title;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

    public Post changeText(String text) {
        this.text = text;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

    public Post changeIsNotice(Boolean isNotice) {
        this.isNotice = isNotice;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

    public Post changeMustRead(Boolean mustRead) {
        this.mustRead = mustRead;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

    public Post changeTeamId(Long teamId) {
        this.teamId = teamId;
        this.modifiedDatetime = LocalDateTime.now();
        return this;
    }

    public void updateViewCount() {
        this.viewCount++;
    }
}
