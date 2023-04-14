package com.chordncode.springmvcboard.board.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springmvcboard.data.dto.BoardDto;
import com.chordncode.springmvcboard.data.entity.Board;
import com.chordncode.springmvcboard.data.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepo;
    public BoardServiceImpl(BoardRepository boardRepo){
        this.boardRepo = boardRepo;
    }
    
    @Override
    public List<BoardDto> listBoard() {
        return null;
    }

    @Override
    public BoardDto insertBoard(BoardDto boardDto) {
        String memId = SecurityContextHolder.getContext().getAuthentication().getName();
        Board board = Board.builder()
                            .boardTitle(boardDto.getBoardTitle())
                            .boardContent(boardDto.getBoardContent())
                            .memId(memId)
                            .createdAt(LocalDateTime.now())
                            .build();
        
        return new BoardDto(boardRepo.save(board));
    }

    @Override
    public BoardDto selectBoard(String boardId) throws Exception {
        Board board = boardRepo.findById(Long.valueOf(boardId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new BoardDto(board);
    }

}
