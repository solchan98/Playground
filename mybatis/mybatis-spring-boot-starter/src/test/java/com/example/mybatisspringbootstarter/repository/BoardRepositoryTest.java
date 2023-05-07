package com.example.mybatisspringbootstarter.repository;

import com.example.mybatisspringbootstarter.Board;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void BOARD를_생성하고_ID를_통해_조회할_수_있다() {
        // given
        Board board = Board.newBoard("새로운 제목", "새로운 내용", "sol");

        // when
        boardRepository.create(board);
        Board boardInDB = boardRepository.findById(board.getId());

        // then
        assertThat(boardInDB).isNotNull();
        assertAll(
                () -> assertThat(boardInDB.getTitle()).isEqualTo(board.getTitle()),
                () -> assertThat(boardInDB.getContent()).isEqualTo(board.getContent()),
                () -> assertThat(boardInDB.getAuthor()).isEqualTo(board.getAuthor())
        );
    }
}
