package com.chordncode.springmvcboard.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.chordncode.springmvcboard.data.dto.MemberDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDetails extends User {

    private MemberDto memDto;

    public MemberDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MemberDetails(MemberDto memDto){

        super(memDto.getMemId(), memDto.getMemPw(),
              memDto.getAuthList().stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.getMemAuth()))
                    .collect(Collectors.toList()));

        this.memDto = memDto;

    }

}
