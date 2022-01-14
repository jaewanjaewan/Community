package com.koreait.commuity.board.cmt;

import com.koreait.commuity.model.BoardCmtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController //return이 모두 json형태라서
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
}
