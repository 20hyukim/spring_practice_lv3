package com.sparta.lv3project.dto.comment;

import com.sparta.lv3project.entity.comment.Comment;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long lectureId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        lectureId = comment.getLectureId();
        userId = comment.getUserId();
        content = comment.getContent();
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();
    }
}
