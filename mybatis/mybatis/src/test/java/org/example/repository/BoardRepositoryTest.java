package org.example.repository;

import org.example.Board;
import org.example.config.mybatis.MyBatisSessionManager;
import org.example.repository.impl.BoardRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BoardRepositoryTest {
    private final BoardRepository boardRepository = new BoardRepositoryImpl(
            new MyBatisSessionManager()
    );

    @Test
    void 새로운_BOARD를_생성할_수_있다(){
        // given
        Board board = Board.newBoard("새로운 제목", "새로운 내용", "sol");

        // when
        board = boardRepository.create(board);

        // then
        assertThat(board.getTitle()).isEqualTo("새로운 제목");
    }

    @Test
    void UUID로_조회하는_경우_데이터가_없을_때는_정상적으로_NULL_OF_OPTIONAL_응답한다() {
        // given
        String uuid = "-1";

        // when
        Optional<Board> optionalBoard = boardRepository.findById(uuid);

        // then
        assertThat(optionalBoard).isEmpty();
    }

    @Test
    void BOARD의_TITLE_및_CONTENT는_수정할_수_있다() {
        // given
        Board board = Board.newBoard("새로운 제목", "새로운 내용", "sol");
        boardRepository.create(board);
        UpdateBoard updateBoard = UpdateBoard.of(board.getId(), "업데이트 제목", "업데이트 내용");

        // when
        Board updatedBoard = boardRepository.update(updateBoard);

        // then
        assertAll(
                () -> assertThat(updatedBoard.getTitle()).isEqualTo(updateBoard.getTitle()),
                () -> assertThat(updatedBoard.getContent()).isEqualTo(updateBoard.getContent())
        );
    }
}
