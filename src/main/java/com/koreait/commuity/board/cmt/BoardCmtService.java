package com.koreait.commuity.board.cmt;

import com.koreait.commuity.UserUtils;
import com.koreait.commuity.model.BoardCmtEntity;
import com.koreait.commuity.model.BoardCmtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCmtService {

    @Autowired private UserUtils userUtils;
    @Autowired private BoardCmtMapper mapper;

    public int insBoardCmt(BoardCmtEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        mapper.insBoardCmt(entity);
        return entity.getIcmt();
    }

    public List<BoardCmtVo> selBoardCmtList(int iboard){
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        return mapper.selBoardCmtList(entity);
    }

    public int updBoardCmt(BoardCmtEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.updBoardCmt(entity);
    }

    public int delBoardCmt(int icmt){
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIcmt(icmt);
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.delBoardCmt(entity);
    }
}
