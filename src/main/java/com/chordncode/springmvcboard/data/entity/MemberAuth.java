package com.chordncode.springmvcboard.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(MemberAuthKey.class)
@Entity
@Table(name = "member_auth")
public class MemberAuth {

    public MemberAuth(String memId, String memAuth){
        this.memId = memId;
        this.memAuth = memAuth;
    }
    
    @Id
    @Column(name = "mem_id")
    private String memId;

    @Id
    @Column(name = "mem_auth")
    private String memAuth;

    @ManyToOne
    @JoinColumn(name = "mem_id", insertable = false, updatable = false)
    private Member member;
    
}
