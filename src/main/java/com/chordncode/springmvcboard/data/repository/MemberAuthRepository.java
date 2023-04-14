package com.chordncode.springmvcboard.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chordncode.springmvcboard.data.entity.MemberAuth;
import com.chordncode.springmvcboard.data.entity.MemberAuthKey;

public interface MemberAuthRepository extends JpaRepository<MemberAuth, MemberAuthKey> {
    
}
