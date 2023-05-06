package org.example.repository;

import org.example.Board;

import java.util.Optional;

public interface BoardRepository {
    Board create(Board board);
    Board update(Board board);
    Optional<Board> findById(String id);
    void deleteById();
}
