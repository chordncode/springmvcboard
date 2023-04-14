package com.chordncode.springmvcboard.board.service;

import java.util.List;

import com.chordncode.springmvcboard.data.dto.BoardDto;

public interface BoardService {
    
    public List<BoardDto> listBoard();

    /**
     * 게시글 등록
     * 
     * @param boardDto 등록할 게시글 정보
     * @return 동록된 게시글 정보
     */
    public BoardDto insertBoard(BoardDto boardDto);

    /**
     * 게시글 조회
     * 
     * @param boardId 조회할 게시글 번호
     * @return 게시글 번호와 일치하는 게시글 정보
     */
    public BoardDto selectBoard(String boardId) throws Exception;
}
