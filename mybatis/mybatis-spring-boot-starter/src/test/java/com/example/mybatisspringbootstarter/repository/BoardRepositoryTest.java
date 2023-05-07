package com.example.mybatisspringbootstarter.repository;

import com.example.mybatisspringbootstarter.Board;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

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

    @Test
    void 모든_BOARD를_조회할_수_있다() {
        // given
        int total = 10;
        for (int idx = 0; idx < total; idx++) {
            Board board = Board.newBoard(idx + "번째 글", "내용", "sol");
            boardRepository.create(board);
        }

        // when
        List<Board> boards = boardRepository.findAll();

        // then
        assertThat(boards).hasSize(total);
    }

    @Test
    void BOARD의_제목과_내용을_수정할_수_있다() {
        // given
        Board board = Board.newBoard("새로운 제목", "새로운 내용", "sol");
        boardRepository.create(board);
        UpdateBoard updateBoard = UpdateBoard.of(board.getId(), "변경된 제목", "변경된 내용");

        // when
        boardRepository.update(updateBoard);
        Board updatedBoard = boardRepository.findById(board.getId());

        // when
        assertThat(updatedBoard).isNotNull();
        assertAll(
                () -> assertThat(updatedBoard.getId()).isEqualTo(updateBoard.getId()),
                () -> assertThat(updatedBoard.getTitle()).isEqualTo(updateBoard.getTitle()),
                () -> assertThat(updatedBoard.getContent()).isEqualTo(updateBoard.getContent())
        );
    }

    @Test
    @Disabled
    void BOARD의_ID를_통해_BOARD를_삭제할_수_있다() {

    }
}
