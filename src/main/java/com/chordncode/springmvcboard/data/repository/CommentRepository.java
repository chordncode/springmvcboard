package com.chordncode.springmvcboard.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.chordncode.springmvcboard.data.entity.Comment;
import com.chordncode.springmvcboard.data.entity.CommentKey;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {

    @Query("SELECT c " +
           "  FROM Comment c " +
           " WHERE boardId = :boardId " +
           " ORDER BY c.createdAt")
    List<Comment> findAllByBoardId(Long boardId);

    @Query("SELECT COALESCE(MAX(commentId), 0) " +
           "  FROM Comment " +
           " WHERE boardId = :boardId")
    Long findMaxCommentIdByBoardId(Long boardId);

    @Modifying
    @Transactional
    @Query("DELETE " +
           "  FROM Comment " +
           " WHERE commentId = :commentId " +
           "   AND boardId = :boardId")
    void deleteByCommentIdAndBoardId(Long commentId, Long boardId);

}
