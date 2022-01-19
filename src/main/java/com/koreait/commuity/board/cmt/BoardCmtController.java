package com.koreait.commuity.board.cmt;

import com.koreait.commuity.model.BoardCmtEntity;
import com.koreait.commuity.model.BoardCmtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //return이 모두 json형태가 된다
@RequestMapping("/board/cmt")
public class BoardCmtController {

    @Autowired private BoardCmtService service;

    @PostMapping
    public Map<String, Integer> insBoardCmt(@RequestBody BoardCmtEntity entity){ //json으로 넘어오는걸
        // boardcmtentity타입으로 converting해준다 json으로넘어오면 @requestbody 줘야됨
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insBoardCmt(entity));
        return result;
    }

    @GetMapping("/{iboard}")
    public List<BoardCmtVo> selBoardCmtList(@PathVariable int iboard){
        return service.selBoardCmtList(iboard);
    }

    @PutMapping
    public Map<String, Integer> updBoardCmt(@RequestBody BoardCmtEntity entity){
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.updBoardCmt(entity));
        return result;
    }

    @DeleteMapping("/{icmt}") //메소드를 구분하기위해 deletemapping줬다 삭제 getmapping줘도된다.
    public Map<String, Integer> delBoardCmt(@PathVariable int icmt){
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delBoardCmt(icmt));
        return result;
    }
}
