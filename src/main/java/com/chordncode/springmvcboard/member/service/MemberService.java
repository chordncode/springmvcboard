package com.chordncode.springmvcboard.member.service;

import com.chordncode.springmvcboard.data.dto.MemberDto;
import com.chordncode.springmvcboard.data.util.ResultStatus;

public interface MemberService {

    /**
     * 본인 인증 이메일 발송
     * 
     * @param memEmail 본인 인증할 이메일 주소
     * @throws Exception
     */
    public void sendAuthMail(String memEmail) throws Exception;

    /**
     * 인증 번호 확인
     * 
     * @param authNum 사용자가 입력한 인증 번호
     * @return 성공 : "SUCCESS", 실패 : "FAILED"
     */
    public ResultStatus checkAuthNum(String authNum);

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

    /**
     * 아이디 찾기
     * 
     * @param memEmail 아이디를 전송할 이메일 주소
     * @return 성공 : "SUCCESS", 계정 존재하지 않음 : "NONE"
     * @throws Exception
     */
    public ResultStatus findId(String memEmail) throws Exception;

    /**
     * 비밀번호 찾기
     * 
     * @param memDto 비밀번호를 찾을 회원의 아이디와 이메일 주소가 담긴 Dto
     * @return 성공 : "SUCCESS", 계정 존재하지 않음 : "NONE"
     * @throws Exception
     */
    public ResultStatus findPw(MemberDto memDto) throws Exception;

    /**
     * 마이페이지 이동 본인 확인
     * 
     * @param memDto 아이디와 비밀번호가 담긴 Dto
     * @return 성공 : "SUCCESS", 실패 : "FAILED"
     */
    public ResultStatus mypage(MemberDto memDto);

    /**
     * 비밀번호 변경
     * 
     * @param memDto 아이디와 비밀번호가 담긴 Dto
     * @return 성공 : "SUCCESS", 실패 : "FAILED"
     */
    public ResultStatus updateMemPw(MemberDto memDto);

    /**
     * 닉네임 변경
     * 
     * @param memDto 아이디와 닉네임이 담긴 Dto
     * @return 성공 : "SUCCESS", 실패 : "FAILED"
     */
    public ResultStatus updateMemNick(MemberDto memDto);

    /**
     * 이메일 변경
     * 
     * @param memId 아이디
     * @param memEmail 이메일
     * @return 성공 : "SUCCESS", 실패 : "FAILED"
     */
    public ResultStatus updateMemEmail(String memId, String memEmail);
}
