package com.example.board.repository;

import com.example.board.Dto.BoardDto;
import com.example.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
   List<Board> findAllByOrderByCreatedAtDesc();
}
