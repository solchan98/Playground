package org.example.repository;

import org.example.Board;

import java.util.List;

public interface BoardMapper {
    void create(Board board);
    Board selectBoard(String id);
    List<Board> selectAll();
    void updateBoard(UpdateBoard updateBoard);
    void deleteById(String id);
    void deleteAll();
}
