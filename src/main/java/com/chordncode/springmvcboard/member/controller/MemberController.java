package com.chordncode.springmvcboard.member.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chordncode.springmvcboard.data.dto.MemberDto;
import com.chordncode.springmvcboard.data.util.ResultStatus;
import com.chordncode.springmvcboard.member.service.MemberService;

@Controller
@RequestMapping("/")
public class MemberController {

    @Autowired
    private MemberService memService;
    
    @GetMapping("")
    public String home(){
        return "redirect:/boards";
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
        if(request.getParameterMap().containsKey("error")){
            model.addAttribute("errMsg", "아이디 또는 비밀번호가 틀렸습니다.");
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

    @ResponseBody
    @PostMapping("/sendAuthMail")
    public ResponseEntity<?> sendAuthMail(@RequestBody MemberDto memDto){
        try{
            memService.sendAuthMail(memDto.getMemEmail());
        } catch (Exception e){
            if(e.getMessage().equals("Duplicated")){
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PostMapping("/checkAuthNum")
    public ResponseEntity<?> checkAuthNum(@RequestBody Map<String, String> paramMap){
        ResultStatus status = memService.checkAuthNum(paramMap.get("authNum"));
        if(status.name().equals("FAILED")) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findId")
    public String findId(){
        return "member/findId";
    }

    @PostMapping("/findId")
    public String findId(@RequestParam String memEmail, Model model){
        ResultStatus status = null;
        try{
            status = memService.findId(memEmail);
        } catch(Exception e){
            model.addAttribute("msg", "잠시 후 다시 시도해주세요.");
            return "alertBack";
        }
        if(status.name().equals("NONE")){
            model.addAttribute("msg", "가입하지 않은 이메일입니다.");
            return "alertBack";
        }
        model.addAttribute("msg", "이메일이 발송되었습니다.");
        model.addAttribute("loc", "/login");
        return "alertLoc";
    }

    @GetMapping("/findPw")
    public String findPw(){
        return "member/findPw";
    }

    @PostMapping("/findPw")
    public String findPw(@ModelAttribute MemberDto memDto, Model model){
        ResultStatus status = null;
        try {
            status = memService.findPw(memDto);
        } catch (Exception e) {
            model.addAttribute("msg", "잠시 후 다시 시도해주세요.");
            return "alertBack";
        }
        if(status.name().equals("NONE")){
            model.addAttribute("msg", "가입하지 않은 아이디입니다.");
            return "alertBack";
        }
        model.addAttribute("msg", "임시 비밀번호가 발송되었습니다.");
        model.addAttribute("loc", "/login");
        return "alertLoc";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/toMypage")
    public String toMypage(){
        return "member/toMypage";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/mypage")
    public String mypage(@ModelAttribute MemberDto memDto, Model model){
        ResultStatus status = memService.mypage(memDto);
        if(status.name().equals("FAILED")){
            model.addAttribute("msg", "비밀번호가 틀렸습니다.");
            return "alertBack";
        }
        return "member/mypage";
    }

    @ResponseBody
    @PostMapping("/updateMemPw")
    public ResponseEntity<?> updateMemPw(@RequestBody MemberDto memDto){
        ResultStatus status = memService.updateMemPw(memDto);
        if(status.name().equals("FAILED")) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PostMapping("/updateMemNick")
    public ResponseEntity<?> updateMemNick(@RequestBody MemberDto memDto){
        ResultStatus status = memService.updateMemNick(memDto);
        if(status.name().equals("FAILED")) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PostMapping("/updateMemEmail")
    public ResponseEntity<?> updateMemEmail(@RequestBody Map<String, String> paramMap){
        ResultStatus status = memService.checkAuthNum(paramMap.get("authNum"));
        if(status.name().equals("FAILED")) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        status = memService.updateMemEmail(paramMap.get("memId"), paramMap.get("memEmail"));
        if(status.name().equals("FAILED")) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
