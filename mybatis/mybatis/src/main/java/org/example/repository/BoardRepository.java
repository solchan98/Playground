package org.example.repository;

import org.example.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board create(Board board);
    Board update(UpdateBoard updateBoard);
    Optional<Board> findById(String id);
    List<Board> findAll();
    void deleteById(String id);
    void deleteAll();
}
