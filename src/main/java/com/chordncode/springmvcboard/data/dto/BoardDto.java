package com.chordncode.springmvcboard.data.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.chordncode.springmvcboard.data.entity.Board;

import lombok.Data;

@Data
public class BoardDto {

    public BoardDto(Board board){
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.memId = board.getMemId();

        this.createdAt = board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updatedAt = board.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String memId;

    private String createdAt;
    private String updatedAt;

}
