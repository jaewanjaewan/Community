package com.koreait.commuity.board.cmt;

import com.koreait.commuity.model.BoardCmtEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity entity);
}
