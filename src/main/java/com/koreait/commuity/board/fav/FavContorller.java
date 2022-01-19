package com.koreait.commuity.board.fav;

import com.koreait.commuity.model.BoardCmtEntity;
import com.koreait.commuity.model.BoardFavEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController //return타입이 모두 json이다.
@RequestMapping("/board/fav")
public class FavContorller {

    @Autowired private FavService service;

    @PostMapping
    public Map<String, Integer> insBoardFav(@RequestBody BoardFavEntity entity){
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insBoardFav(entity));
        return result;
    }

    @GetMapping("/{iboard}")
    public Map<String, Integer> selBoardFav(@PathVariable int iboard){
        BoardFavEntity dbEntity = service.selBoardFav(iboard);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", dbEntity == null ? 0 : 1);
        return result;
    }

    @DeleteMapping("/{iboard}") //위에랑 주소값이 같기때문에 이럴땐 deletemapping
    public Map<String, Integer> delBoardFav(@PathVariable int iboard){
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delBoardFav(iboard));
        return result;
    }
}
