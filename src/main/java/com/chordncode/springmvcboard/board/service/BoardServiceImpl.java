package com.chordncode.springmvcboard.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springmvcboard.config.MemberDetails;
import com.chordncode.springmvcboard.data.dto.BoardDto;
import com.chordncode.springmvcboard.data.dto.MemberDto;
import com.chordncode.springmvcboard.data.entity.Board;
import com.chordncode.springmvcboard.data.repository.BoardRepository;
import com.chordncode.springmvcboard.data.util.ArticleInfo;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepo;
    public BoardServiceImpl(BoardRepository boardRepo){
        this.boardRepo = boardRepo;
    }
    
    @Override
    public ArticleInfo<BoardDto> listBoard(long page) {
        long totalRows = boardRepo.countBy();
        ArticleInfo<BoardDto> articleInfo = new ArticleInfo<>(page, totalRows, 10, 10);
        List<Board> boardList = boardRepo.findByRows(articleInfo.getStartRow(), articleInfo.getEndRow()).orElse(null);
        articleInfo.setContentList(boardList.stream().map(board -> new BoardDto(board)).collect(Collectors.toList()));
        return articleInfo;
    }

    @Override
    public BoardDto insertBoard(BoardDto boardDto) {
        MemberDetails memDet = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberDto memDto = memDet.getMemDto();
        Board board = Board.builder()
                            .boardTitle(boardDto.getBoardTitle())
                            .boardContent(boardDto.getBoardContent())
                            .memId(memDto.getMemId())
                            .memNick(memDto.getMemNick())
                            .createdAt(LocalDateTime.now())
                            .build();
        
        return new BoardDto(boardRepo.save(board));
    }

    @Override
    public BoardDto selectBoard(Long boardId) throws Exception {
        Board board = boardRepo.findById(boardId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BoardDto boardDto = new BoardDto(board);
        long rows = boardRepo.findCurrentPage(boardId);
        boardDto.setCurrentPage((long) Math.ceil((double)rows / 10));
        return boardDto;
    }

    @Override
    public BoardDto updateBoard(BoardDto boardDto) throws Exception {
        Board board = boardRepo.findById(boardDto.getBoardId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(board != null){
            board.setBoardTitle(boardDto.getBoardTitle());
            board.setBoardContent(boardDto.getBoardContent());
        }
        Board updatedBoard = boardRepo.save(board);
        return new BoardDto(updatedBoard);
    }

    @Override
    public void deleteBoard(Long boardId) throws Exception {
        boardRepo.deleteById(boardId);
    }

}
