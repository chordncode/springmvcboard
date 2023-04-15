package com.chordncode.springmvcboard.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.chordncode.springmvcboard.board.service.BoardService;
import com.chordncode.springmvcboard.data.dto.BoardDto;
import com.chordncode.springmvcboard.data.util.ArticleInfo;

@Controller
@RequestMapping("/boards")
@PreAuthorize("hasRole('ROLE_MEMBER')")
public class BoardController {

    private BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping(value = {"", "/pages/{page}"})
    public String listBoard(@PathVariable(required = false) Long page, Model model){
        if(page == null) page = 1L;
        ArticleInfo<BoardDto> boardInfo = boardService.listBoard(page);
        model.addAttribute("boardInfo", boardInfo);
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
        return "redirect:/boards";
    }

    @GetMapping("/{boardId}")
    public String detailBoard(@PathVariable Long boardId, Model model){
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

    @PostMapping("/update")
    public String updateBoard(@RequestParam Long boardId, Model model){
        BoardDto boardDto = null;
        try{
            boardDto = boardService.selectBoard(boardId);
        } catch(Exception e) {
            model.addAttribute("msg", "에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
            return "alertBack";
        }
        model.addAttribute("board", boardDto);
        return "board/update";
    }

    @PostMapping("/updatePost")
    public String updateBoard(@ModelAttribute BoardDto boardDto, Model model){
        try{
            BoardDto updatedBoard = boardService.updateBoard(boardDto);
            if(updatedBoard == null) throw new Exception();
        } catch(ResponseStatusException e){
            model.addAttribute("msg", "게시글이 존재하지 않습니다.");
            model.addAttribute("loc", "/boards");
            return "alertLoc";
        } catch(Exception e){
            model.addAttribute("msg", "에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
            return "alertBack";
        }
        return "redirect:/boards/" + boardDto.getBoardId();
    }

    @PostMapping("/delete")
    public String deleteBoard(@RequestParam Long boardId, Model model){
        try{
            boardService.deleteBoard(boardId);
        } catch(Exception e){
            model.addAttribute("msg", "에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
            return "alertBack";
        }
        return "redirect:/boards";
    }
    
}
