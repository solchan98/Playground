package org.example.repository;

import org.example.Board;

public interface BoardMapper {
    void create(Board board);
    Board selectBoard(String id);
}
