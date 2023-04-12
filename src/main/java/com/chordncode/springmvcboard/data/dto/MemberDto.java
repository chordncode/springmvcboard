package com.chordncode.springmvcboard.data.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.chordncode.springmvcboard.data.entity.Member;
import com.chordncode.springmvcboard.data.entity.MemberAuth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    public MemberDto(Member member){
        this.memId = member.getMemId();
        this.memPw = member.getMemPw();
        this.memNick = member.getMemNick();
        this.memEmail = member.getMemEmail();
        this.memSignupAt = member.getMemSignupAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.authList = member.getAuthList();
    }
    
    private String memId;
    private String memPw;
    private String memNick;
    private String memEmail;
    private String memSignupAt;
    List<MemberAuth> authList;

}
