package org.example.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.Board;
import org.example.repository.BoardMapper;
import org.example.repository.BoardRepository;
import org.example.config.mybatis.MyBatisSessionManager;
import org.example.repository.UpdateBoard;

import java.util.Optional;

public class BoardRepositoryImpl implements BoardRepository {
    private final MyBatisSessionManager sessionManager;


    public BoardRepositoryImpl(MyBatisSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    private BoardMapper getMapper(SqlSession session) {
        return session.getMapper(BoardMapper.class);
    }

    @Override
    public Board create(Board board) {
        SqlSession session = sessionManager.getSession();
        getMapper(session).create(board);

        Optional<Board> boardOptional = findById(board.getId());
        if (boardOptional.isEmpty()) {
            // TODO: 2023/05/06 생성 실패에 대한 예외 처리 추가
            System.out.println("생성 실패");
            return null;
        }
        sessionManager.closeSession(session);

        return boardOptional.get();
    }

    @Override
    public Board update(UpdateBoard updateBoard) {
        SqlSession session = sessionManager.getSession();
        findById(updateBoard.getId())
                .orElseThrow(() -> new RuntimeException("해당 Board가 존재하지 않습니다."));

        getMapper(session).updateBoard(updateBoard);
        sessionManager.closeSession(session);

        return findById(updateBoard.getId())
                .orElseThrow(() -> new RuntimeException("에러가 발생하였습니다."));
    }

    @Override
    public Optional<Board> findById(String uuid) {
        SqlSession session = sessionManager.getSession();

        Board board = getMapper(session).selectBoard(uuid);

        sessionManager.closeSession(session);
        return Optional.ofNullable(board);
    }

    @Override
    public void deleteById() {

    }
}
