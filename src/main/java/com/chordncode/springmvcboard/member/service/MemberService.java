package com.chordncode.springmvcboard.member.service;

import com.chordncode.springmvcboard.data.dto.MemberDto;

public interface MemberService {
    
    /**
     * 회원가입
     * 
     * @param memberDto 회원가입하는 환자의 정보
     * @return 회원가입을 완료한 환자의 정보
     */
    public MemberDto insertMember(MemberDto memDto) throws Exception;

}
