package org.example.expert.domain.comment.dto;

import org.example.expert.domain.comment.dto.response.CommentResponse;
import org.example.expert.domain.comment.dto.response.CommentSaveResponse;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment comment) {
        User user = comment.getUser();
        return new CommentResponse(
                comment.getId(),
                comment.getContents(),
                new UserResponse(user.getId(), user.getEmail())
        );
    }

    public CommentSaveResponse toSaveResponse(Comment comment) {
        User user = comment.getUser();
        return new CommentSaveResponse(
                comment.getId(),
                comment.getContents(),
                new UserResponse(user.getId(), user.getEmail())
        );
    }
}