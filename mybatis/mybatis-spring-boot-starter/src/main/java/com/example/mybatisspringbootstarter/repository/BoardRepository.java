package com.example.mybatisspringbootstarter.repository;

import com.example.mybatisspringbootstarter.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    String ALL_FIELD_OF_BOARD =
            "id, title, content, author, created_at, updated_at";

    String ALL_PARAM_FIELD_OF_BOARD =
            "#{board.id}, #{board.title}, #{board.content}, #{board.author}, #{board.createdAt}, #{board.updatedAt}";

    @Insert(
            "insert into board values(" + ALL_PARAM_FIELD_OF_BOARD + ")"
    )
    void create(@Param("board") Board board);

    @Select("select " + ALL_FIELD_OF_BOARD + " from board")
    List<Board> findAll();

    @Select("select " + ALL_FIELD_OF_BOARD + " from board where id = #{id}")
    Board findById(@Param("id") String id);
}
