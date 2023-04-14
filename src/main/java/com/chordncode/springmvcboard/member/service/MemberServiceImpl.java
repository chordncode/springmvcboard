package com.chordncode.springmvcboard.member.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chordncode.springmvcboard.data.dto.MemberDto;
import com.chordncode.springmvcboard.data.entity.Member;
import com.chordncode.springmvcboard.data.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memRepo;
    
    @Override
    public MemberDto insertMember(MemberDto memDto) throws Exception {

        Member member = new Member(memDto);
        member.setMemSignupAt(LocalDateTime.now());

        Member insertedMember = memRepo.save(member);

        return new MemberDto(insertedMember);
    }

    @Override
    public MemberDto selectMemberForDetails(String memId) {
        Member member = memRepo.findById(memId).orElse(null);
        return new MemberDto(member);
    }

}
