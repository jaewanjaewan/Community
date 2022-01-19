package com.koreait.commuity.board.cmt;

import com.koreait.commuity.model.BoardCmtEntity;
import com.koreait.commuity.model.BoardCmtVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity entity);
    List<BoardCmtVo> selBoardCmtList(BoardCmtEntity entity);
    int updBoardCmt(BoardCmtEntity entity);
    int delBoardCmt(BoardCmtEntity entity);
}
