package com.chordncode.springmvcboard.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chordncode.springmvcboard.data.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
    long countByMemEmail(String memEmail);

    @Query("SELECT m.memId FROM Member m WHERE m.memEmail = :memEmail")
    String findMemIdByMemEmail(String memEmail);

    Optional<Member> findByMemIdAndMemEmail(String memId, String memEmail);

    Optional<Member> findByMemIdAndMemPw(String memId, String memPw);

}
