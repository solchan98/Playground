package org.example.service.impl;

import org.example.Board;
import org.example.repository.BoardRepository;
import org.example.service.BoardService;

import java.util.Optional;

public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board createBoard(String title, String content, String author) {
        Board board = Board.newBoard(title, content, author);
        return boardRepository.create(board);
    }

    @Override
    public Board updateBoard(Long boardId, Board updateBoard) {
        return null;
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
