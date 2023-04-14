package com.chordncode.springmvcboard.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chordncode.springmvcboard.board.service.BoardService;
import com.chordncode.springmvcboard.data.dto.BoardDto;

@Controller
@RequestMapping("/board")
@PreAuthorize("hasRole('ROLE_MEMBER')")
public class BoardController {

    private BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping(value = {"", "/list"})
    public String listBoard(){
        return "board/list";
    }

    @GetMapping("/insert")
    public String insertBoard(){
        return "board/insert";
    }

    @PostMapping("/insert")
    public String insertBoard(@ModelAttribute BoardDto boardDto, Model model){
        BoardDto insertedBoard = boardService.insertBoard(boardDto);
        if(insertedBoard == null){
            model.addAttribute("msg", "에러가 발생했습니다.");
            return "alertBack";
        }
        return "redirect:/board/detail?boardId=" + insertedBoard.getBoardId();
    }

    @GetMapping("/detail")
    public String detailBoard(@RequestParam String boardId, Model model){
        BoardDto boardDto = null;
        try {
            boardDto = boardService.selectBoard(boardId);
        } catch (Exception e) {
            model.addAttribute("msg", "게시글이 존재하지 않습니다.");
            return "alertBack";
        }
        model.addAttribute("board", boardDto);
        return "board/detail";
    }

    
}
