package com.koreait.commuity.board;

import com.koreait.commuity.model.BoardDto;
import com.koreait.commuity.model.BoardEntity;
import com.koreait.commuity.model.BoardPrevNextVo;
import com.koreait.commuity.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity entity);
    List<BoardVo> selBoardList(BoardDto dto);
    BoardVo selBoard(BoardDto dto);
    BoardPrevNextVo selPrevNext(BoardVo vo);
    int addHits(BoardEntity dto);
    int updBoard(BoardEntity dto);
}
