package com.chordncode.springmvcboard.data.dto;

import java.time.format.DateTimeFormatter;

import com.chordncode.springmvcboard.data.entity.Comment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

    public CommentDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.boardId = comment.getBoardId();
        this.commentContent = comment.getCommentContent();
        this.memId = comment.getMemId();
        this.memNick = comment.getMemNick();
        if(comment.getCreatedAt() != null){
            this.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
    
    private Long commentId;
    private Long boardId;
    private String commentContent;
    private String memId;
    private String memNick;
    private String createdAt;

}
