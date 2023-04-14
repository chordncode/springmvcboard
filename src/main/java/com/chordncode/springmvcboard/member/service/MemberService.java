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

    /**
     * 로그인을 하기 위해 MemberDetailsService에 회원정보를 가져옴
     * 
     * @param memId 로그인을 하기 위해 입력한 아이디
     * @return 일치하는 아이디가 있을 경우 회원정보가 담긴 DTO, 없을 경우 null
     * @throws Exception
     */
    public MemberDto selectMemberForDetails(String memId);

}
