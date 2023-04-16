package com.chordncode.springmvcboard.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Entity
@IdClass(CommentKey.class)
@Table(name = "comment")
public class Comment {
    
    @Id
    @Column(name = "comment_id")
    private Long commentId;

    @Id
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "mem_id")
    private String memId;

    @Column(name = "mem_nick")
    private String memNick;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "mem_id", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id", insertable = false, updatable = false)
    private Board board;

}
