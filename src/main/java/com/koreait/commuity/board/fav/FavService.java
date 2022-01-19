package com.koreait.commuity.board.fav;

import com.koreait.commuity.UserUtils;
import com.koreait.commuity.model.BoardFavEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavService {

    @Autowired private FavMapper mapper;
    @Autowired private UserUtils utils;

    public int insBoardFav(BoardFavEntity entity){
        entity.setIuser(utils.getLoginUserPk()); //iuser값(로직부분)은 웬만하면 service에서
        return mapper.insBoardFav(entity);
    }

    public BoardFavEntity selBoardFav(int iboard){
//        BoardFavEntity entity = new BoardFavEntity();
//        entity.setIboard(iboard);
//        entity.setIuser(utils.getLoginUserPk());
//        return mapper.selBoardFav(entity);
        return mapper.selBoardFav(setBoardFavEntity(iboard));
    }

    public int delBoardFav(int iboard){
//        BoardFavEntity entity = new BoardFavEntity();
//        entity.setIboard(iboard);
//        entity.setIuser(utils.getLoginUserPk());
//        return mapper.delBoardFav(entity);
        return mapper.delBoardFav(setBoardFavEntity(iboard));
    }

    //중복된 소스 때문에 메소드로 구현
    private BoardFavEntity setBoardFavEntity(int iboard){
        BoardFavEntity entity = new BoardFavEntity();
        entity.setIboard(iboard);
        entity.setIuser(utils.getLoginUserPk());
        return entity;
    }
}
