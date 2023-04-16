package com.chordncode.springmvcboard.board.service;

import java.util.List;

import com.chordncode.springmvcboard.data.dto.BoardDto;
import com.chordncode.springmvcboard.data.dto.CommentDto;
import com.chordncode.springmvcboard.data.util.ArticleInfo;

public interface BoardService {
    
    /**
     * 게시글 목록
     * 
     * @param page 현재 페이지
     * @return 현재 페이지의 게시물 목록
     */
    public ArticleInfo<BoardDto> listBoard(long page);

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
     * @throws Exception
     */
    public BoardDto selectBoard(Long boardId) throws Exception;

    /**
     * 게시글 수정
     * 
     * @param boardDto 수정할 게시글 정보
     * @return 수정된 게시글 정보
     * @throws Exception
     */
    public BoardDto updateBoard(BoardDto boardDto) throws Exception;

    /**
     * 게시글 삭제
     * 
     * @param boardId 삭제할 게시글 번호
     * @throws Exception
     */
    public void deleteBoard(Long boardId) throws Exception;

    /**
     * 게시글 댓글 조회
     * 
     * @param boardId 댓글을 조회할 게시글 번호
     * @return 해당 게시글의 댓글 목록
     */
    public List<CommentDto> listComment(Long boardId);

    /**
     * 댓글 등록
     * 
     * @param commentDto 등록할 댓글 정보
     * @return 등록된 댓글 정보
     * @throws Exception
     */
    public CommentDto insertComment(CommentDto commentDto) throws Exception;

    /**
     * 댓글 삭제
     * 
     * @param commentDto 삭제할 댓글의 게시글 번호, 댓글 번호가 담긴 Dto
     * @throws Exception
     */
    public void deleteComment(CommentDto commentDto) throws Exception;
}
