package org.example.repository;

import org.example.Board;

import java.util.Optional;

public interface BoardRepository {
    Board create(Board board);
    Board update(UpdateBoard updateBoard);
    Optional<Board> findById(String id);
    void deleteById();
}
