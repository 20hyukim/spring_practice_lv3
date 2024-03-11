package com.sparta.lv3project.entity.comment;

import com.sparta.lv3project.dto.comment.CommentRequestDto;
import com.sparta.lv3project.entity.Lecture.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long lectureId;
    @Column(nullable = false)
    private Long userId;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Comment(Long lectureId, Long userId, CommentRequestDto requestDto) {
        this.lectureId = lectureId;
        this.userId = userId;
        this.content = requestDto.getContent();
    }
}
