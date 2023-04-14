package com.chordncode.springmvcboard.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chordncode.springmvcboard.data.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
