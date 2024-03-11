package com.sparta.lv3project.service;

import com.sparta.lv3project.dto.comment.CommentRequestDto;
import com.sparta.lv3project.dto.comment.CommentResponseDto;
import com.sparta.lv3project.entity.comment.Comment;
import com.sparta.lv3project.repository.CommentRepository;
import jakarta.persistence.EntityListeners;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public ResponseEntity<?> createComment(Long lectureId, Long userId, CommentRequestDto requestDto) {
        Comment comment = new Comment(lectureId, userId, requestDto);
        commentRepository.save(comment);
        return ResponseEntity.ok(comment);
    }

    public List<CommentResponseDto> getComments(Long id) {
        return commentRepository.findAllByLectureId(id).stream().map(CommentResponseDto::new).toList();
    }

    public ResponseEntity<?> deleteComment(Long commentId, Long userId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId); // 겹치는 부분 함수로
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            if(comment.getId().equals(userId)){
                commentRepository.delete(comment);
                return ResponseEntity.ok(comment);
            }
            else{
                throw new IllegalArgumentException("댓글 작성자가 아닙니다."); //상태코드 변경
            }

        }else{
            throw new IllegalArgumentException("코멘트를 삭제 할 수 없습니다.");
        }
    }
    // readonly -> 보관x 속도 빠름
    @Transactional
    public ResponseEntity<?> updateComment(Long commentId, Long userId, CommentRequestDto requestDto) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            if(comment.getId().equals(userId)){
                comment.setContent(requestDto.getContent());
                return ResponseEntity.ok(comment);
            }
            else{
                throw new IllegalArgumentException("댓글 작성자가 아닙니다.");
            }

        }else{
            throw new IllegalArgumentException("코멘트를 수정 할 수 없습니다.");
        }
    }
}
