package com.chordncode.springmvcboard.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chordncode.springmvcboard.data.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Long countBy();

    @Query("SELECT COUNT(b.id) " +
           "  FROM Board b " +
           " WHERE b.id >= :boardId")
    Long findCurrentPage(Long boardId);

    @Query(value = "WITH v AS ( " +
          "SELECT b.board_id, b.board_title, b.board_content, b.mem_id, b.mem_nick, b.created_at, b.updated_at, " +
          "       ROW_NUMBER() OVER(ORDER BY b.created_at desc) rownum " +
          "  FROM Board b " +
          ") " +
          "SELECT * " +
          "  FROM v " +
          " WHERE v.rownum BETWEEN :startRows AND :endRows", nativeQuery = true)
    Optional<List<Board>> findByRows(long startRows, long endRows);

}
