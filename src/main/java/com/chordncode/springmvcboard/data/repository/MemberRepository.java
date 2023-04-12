package com.chordncode.springmvcboard.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chordncode.springmvcboard.data.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
    

}
