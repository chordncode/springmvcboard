package com.chordncode.springmvcboard.member.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chordncode.springmvcboard.config.MemberDetails;
import com.chordncode.springmvcboard.data.dto.MemberDto;
import com.chordncode.springmvcboard.data.entity.Member;
import com.chordncode.springmvcboard.data.entity.MemberAuth;
import com.chordncode.springmvcboard.data.repository.MemberAuthRepository;
import com.chordncode.springmvcboard.data.repository.MemberRepository;
import com.chordncode.springmvcboard.data.util.MailUtil;
import com.chordncode.springmvcboard.data.util.ResultStatus;


@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memRepo;
    private MemberAuthRepository memAuthRepo;
    private MailUtil mailUtil;

    public MemberServiceImpl(MemberRepository memRepo, MemberAuthRepository memAuthRepo, MailUtil mailUtil){
        this.memRepo = memRepo;
        this.memAuthRepo = memAuthRepo;
        this.mailUtil = mailUtil;
    }

    private HttpSession getSession(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession(true);
    }

    @Override
    public void sendAuthMail(String memEmail) throws Exception {

        long isDuplicatedEmail = memRepo.countByMemEmail(memEmail);
        if(isDuplicatedEmail > 0) throw new Exception("Duplicated");

        int authNum = (int)(Math.random() * 900000) + 100000;
        String mailSubject = "본인 인증 메일입니다.";
        String mailBody = "익명게시판 본인 인증 메일입니다.\n화면에 [" + authNum + "]을 정확히 입력해주세요.";
        mailUtil.sendMail(memEmail, mailSubject, mailBody);

        HttpSession session = getSession();
        session.setAttribute("authNum", authNum);
    }

    @Override
    public ResultStatus checkAuthNum(String authNum) {
        HttpSession session = getSession();
        String sendedAuthNum = String.valueOf((int)session.getAttribute("authNum"));
        if(!sendedAuthNum.equals(authNum)) return ResultStatus.FAILED;
        return ResultStatus.SUCCESS;
    }
    
    @Override
    public MemberDto insertMember(MemberDto memDto) throws Exception {

        Member member = new Member(memDto);
        member.setMemSignupAt(LocalDateTime.now());

        Member insertedMember = memRepo.save(member);
        memAuthRepo.save(new MemberAuth(insertedMember.getMemId(), "ROLE_MEMBER"));

        getSession().removeAttribute("authNum");

        return new MemberDto(insertedMember);
    }

    @Override
    public MemberDto selectMemberForDetails(String memId) {
        Member member = memRepo.findById(memId).orElse(null);
        return new MemberDto(member);
    }

    @Override
    public ResultStatus findId(String memEmail) throws Exception {
        String memId = memRepo.findMemIdByMemEmail(memEmail);
        if(memId == null) return ResultStatus.NONE;

        String mailSubject = "아이디 찾기 메일입니다.";
        String mailBody = "익명 게시판 아이디 찾기 메일입니다.\n회원님의 아이디는 [" + memId + "] 입니다.";
        mailUtil.sendMail(memEmail, mailSubject, mailBody);

        return ResultStatus.SUCCESS;
    }

    @Override
    public ResultStatus findPw(MemberDto memDto) throws Exception {
        Member member = memRepo.findByMemIdAndMemEmail(memDto.getMemId(), memDto.getMemEmail()).orElse(null);
        if(member == null) return ResultStatus.NONE;

        String memPw = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        member.setMemPw(memPw);
        Member updatedMember = memRepo.save(member);
        
        String updatedMemPw = updatedMember.getMemPw();
        String mailSubject = "임시 비밀번호 메일입니다.";
        String mailBody = "익명 게시판 임시 비밀번호 메일입니다.\n회원님의 임시 비밀번호는 [" + updatedMemPw + "] 입니다.";
        mailUtil.sendMail(updatedMember.getMemEmail(), mailSubject, mailBody);

        return ResultStatus.SUCCESS;
    }

    @Override
    public ResultStatus mypage(MemberDto memDto) {
        Member member = memRepo.findByMemIdAndMemPw(memDto.getMemId(), memDto.getMemPw()).orElse(null);
        if(member == null) return ResultStatus.FAILED;
        return ResultStatus.SUCCESS;
    }

    @Override
    public ResultStatus updateMemPw(MemberDto memDto) {
        Member member = memRepo.findById(memDto.getMemId()).orElse(null);
        if(member == null) return ResultStatus.FAILED;
        member.setMemPw(memDto.getMemPw());
        memRepo.save(member);
        updatePrincipal(member);
        return ResultStatus.SUCCESS;
    }

    @Override
    public ResultStatus updateMemNick(MemberDto memDto) {
        Member member = memRepo.findById(memDto.getMemId()).orElse(null);
        if(member == null) return ResultStatus.FAILED;
        member.setMemNick(memDto.getMemNick());
        memRepo.save(member);
        updatePrincipal(member);
        return ResultStatus.SUCCESS;
    }

    @Override
    public ResultStatus updateMemEmail(String memId, String memEmail) {
        Member member = memRepo.findById(memId).orElse(null);
        if(member == null) return ResultStatus.FAILED;
        member.setMemEmail(memEmail);
        memRepo.save(member);
        updatePrincipal(member);
        return ResultStatus.SUCCESS;
    }

    private void updatePrincipal(Member member){
        ((MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setMemDto(new MemberDto(member));
    }

}
