package com.chordncode.springmvcboard.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.chordncode.springmvcboard.data.dto.MemberDto;
import com.chordncode.springmvcboard.member.service.MemberService;

@Component
public class MemberDetailsService implements UserDetailsService{

    private MemberService memberService;

    public MemberDetailsService(MemberService memberService){
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto memDto = memberService.selectMemberForDetails(username);
        return memDto == null ? null : new MemberAuth(memDto);
    }
    
}
