package com.chordncode.springmvcboard.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chordncode.springmvcboard.data.dto.MemberDto;
import com.chordncode.springmvcboard.member.service.MemberService;

@Controller
@RequestMapping("/")
public class MemberController {

    @Autowired
    private MemberService memService;
    
    @GetMapping("")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(MemberDto memDto, Model model){
        MemberDto insertedMemDto = null;

        try {
            insertedMemDto = memService.insertMember(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(insertedMemDto == null) {
            model.addAttribute("msg", "에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
            model.addAttribute("loc", "/signup");
            return "alert";
        }
        return "redirect:/login";
    }

}
