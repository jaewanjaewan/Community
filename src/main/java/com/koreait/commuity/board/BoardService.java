package com.koreait.commuity.board;

import com.koreait.commuity.UserUtils;
import com.koreait.commuity.model.BoardDto;
import com.koreait.commuity.model.BoardEntity;
import com.koreait.commuity.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;
    @Autowired
    private UserUtils userUtils;

    public int insBoard(BoardEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoard(entity);
    }

    public List<BoardVo> selBoardList(BoardDto dto){
        return mapper.selBoardList(dto);
    }

    public BoardVo selBoard(BoardDto dto){ //dto에는 iboard, lastip만 저장되어있을것
        BoardVo detail = mapper.selBoard(dto);
        if(!Objects.equals(dto.getLastip(), detail.getLastip())){ //Objects.equals() : 같으면 true 다르면 false리턴
            int hitsResult = mapper.addHits(dto);
            if(hitsResult == 1){ //detail로 들어갔을때 올려진 hits가 바로보이게하기위해
                detail.setHits(detail.getHits() + 1);
            }
            mapper.updBoard(dto); //게시판의 lastip를 수정함으로써 새로고침해도 조회수 올라가는걸 방지
        }
        return detail;
    }

    public int delBoard(BoardEntity entity){ //entity에 icategory, iboard담겨있음
        entity.setIuser(userUtils.getLoginUserPk());
        entity.setIsdel(1);
        return mapper.updBoard(entity);
    }
}
