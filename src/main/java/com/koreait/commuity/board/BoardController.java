package com.koreait.commuity.board;

import com.koreait.commuity.Const;
import com.koreait.commuity.model.BoardDto;
import com.koreait.commuity.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, BoardDto dto, Model model){
        model.addAttribute(Const.I_CATEGORY, icategory);
        dto.setIcategory(icategory);
        model.addAttribute(Const.LIST, service.selBoardList(dto));
        return "board/list"; //본인이 본인꺼 여는거라 redirect주면 무한루프에 빠진다
        // 리턴 board/list안써주면 board/list/2 이런식으로 가서 jsp파일 못찾음
        // redirect없이 jsp 페이지를 열고싶을땐 model로 값을 보내주면 된다.
    }

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity) {
        int result = service.insBoard(entity);
        return "redirect:/board/list/" + entity.getIcategory();
    }

    @GetMapping("/detail") //쿼리스트링으로 iboard값을 넘겨주기때문에 BoardDto에 값이들어간다(자동으로)
    public void detail(BoardDto dto, Model model, HttpServletRequest req){
        String lastIp = req.getHeader("X-FORWARDED-FOR"); //getHeader: 클라이언트 ip주소를 정확하게 가져온다
        if(lastIp == null){ //X-Forwared-For 헤더부터 검증한 뒤 점점 내려가는 구조이다
            lastIp = req.getRemoteAddr();
        }
        dto.setLastip(lastIp);
        model.addAttribute(Const.DATA, service.selBoard(dto));
    }

    @GetMapping("/del")
    public String delProc(BoardEntity entity){
         int rs = service.delBoard(entity);
         return "redirect:/board/list/"+entity.getIcategory();
    }
}
