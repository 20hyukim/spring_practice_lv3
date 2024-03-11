package com.sparta.lv3project.repository;

import com.sparta.lv3project.dto.comment.CommentResponseDto;
import com.sparta.lv3project.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByLectureId(Long id);

    Optional<Comment> findAllByLectureIdAndUserId(Long lectureId, Long userId);

    Optional<Comment> findByLectureIdAndUserId(Long lectureId, Long userId);
}
