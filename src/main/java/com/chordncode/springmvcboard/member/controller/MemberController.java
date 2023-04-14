package com.chordncode.springmvcboard.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
        return "redirect:/board";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("rememberId")){
                    model.addAttribute("rememberId", cookie.getValue());
                }
            }
        }
        return "member/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "member/signup";
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
            return "alertBack";
        }
        return "redirect:/login";
    }

}
