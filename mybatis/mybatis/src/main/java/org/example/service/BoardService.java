package org.example.service;

import org.example.Board;

import java.util.Optional;

public interface BoardService {
    Board createBoard(
            String title,
            String content,
            String author
    );

    Board updateBoard(
            Long boardId,
            Board updateBoard
    );

    Optional<Board> findById(Long id);

    void deleteById(Long id);
}
