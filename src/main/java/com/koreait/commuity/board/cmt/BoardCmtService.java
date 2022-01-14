package com.koreait.commuity.board.cmt;

import com.koreait.commuity.UserUtils;
import com.koreait.commuity.model.BoardCmtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardCmtService {

    @Autowired private UserUtils userUtils;
    @Autowired private BoardCmtMapper mapper;

    public int insBoardCmt(BoardCmtEntity entity){
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoardCmt(entity);
    }
}
