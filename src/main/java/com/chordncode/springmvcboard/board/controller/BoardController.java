package com.chordncode.springmvcboard.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping(value = {"", "/list"})
    public String listBoard(){
        return "board";
    }

    
}
