package com.chordncode.springmvcboard.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.chordncode.springmvcboard.data.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member {

    public Member(MemberDto memberDto){
        this.memId = memberDto.getMemId();
        this.memPw = memberDto.getMemPw();
        this.memNick = memberDto.getMemNick();
        this.memEmail = memberDto.getMemEmail();
    }
    
    @Id
    @Column(name = "mem_id",  nullable = false)
    private String memId;

    @Column(name = "mem_pw",  nullable = false)
    private String memPw;

    @Column(name = "mem_nick",  nullable = false)
    private String memNick;

    @Column(name = "mem_email",  nullable = false)
    private String memEmail;

    @Column(name = "mem_signup_at", nullable = false)
    private LocalDateTime memSignupAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<MemberAuth> authList;

    @OneToMany(mappedBy = "member")
    List<Board> boardList;

    @OneToMany(mappedBy = "member")
    List<Comment> commentList;

}
