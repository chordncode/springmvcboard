package com.chordncode.springmvcboard.data.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentKey implements Serializable {

    private Long commentId;
    private Long boardId;
    
}
