package com.chordncode.springmvcboard.data.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberAuthKey implements Serializable {
    
    private String memId;
    private String memAuth;

}
