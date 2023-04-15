package com.chordncode.springmvcboard.data.dto;

import java.time.format.DateTimeFormatter;

import com.chordncode.springmvcboard.data.entity.Board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {

    public BoardDto(Board board){
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.memId = board.getMemId();
        this.memNick = board.getMemNick();

        if(board.getCreatedAt() != null){
            this.createdAt = board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        if(board.getUpdatedAt() != null){
            this.updatedAt = board.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

    }

    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private String memId;
    private String memNick;

    private String createdAt;
    private String updatedAt;

    private Long currentPage;

}
