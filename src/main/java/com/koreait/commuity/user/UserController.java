package com.koreait.commuity.user;

import com.koreait.commuity.Const;
import com.koreait.commuity.model.UserDto;
import com.koreait.commuity.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private HttpSession hs;

    @GetMapping("/join")
    public void join(){}

    @PostMapping("/join")
    public String joinProc(UserEntity entity, RedirectAttributes rAttr){
        int rs = service.join(entity);
        if(rs == 0){
            rAttr.addAttribute(Const.MESSAGE, "회원가입에 실패했습니다.");
            return "redirect:/user/join";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/idChk/{uid}")
    @ResponseBody //return이 json이됨
    public Map<String, Integer> idChk(@PathVariable String uid){ //uid에 아이디값이 담긴다
        System.out.println("uid : " + uid);
        Map<String, Integer> res = new HashMap<>(); //암호화
        res.put("result", service.idChk(uid));
        return res; //json형태로 변환해서 날림
    }

    @PostMapping("/login")
    public String loginProc(UserEntity entity, RedirectAttributes rAttr){ //RedirectAttributes는 requestScope로 씀
        int rs = service.login(entity);
        switch (rs){
            case 1: return "redirect:/board/list/1";
            case 2:
                rAttr.addFlashAttribute(Const.MESSAGE, "아이디를 잘못입력하였습니다");
                rAttr.addFlashAttribute("id", entity.getUid());
                return "redirect:/user/login";
                //addAttribute는 쿼리스트링 이용 ?뒤에 파라미터보임, addFlashAttribute는 세션이용 ?뒤에 파라미터가 안보인다
            case 3:
                rAttr.addFlashAttribute(Const.MESSAGE, "비밀번호를 잘못입력하였습니다.");
                rAttr.addFlashAttribute("id", entity.getUid());
                return "redirect:/user/login";
        }
        return null;
    }

    @GetMapping("/logout")
    public String logout(){
        hs.invalidate();
        return "redirect:/user/login";
    }

    @GetMapping("/mypage/profile")
    public void mypageProfile(){}

    @ResponseBody//return이 json이됨
    @PostMapping("/mypage/profile")
    public Map<String, String> mypageProfileProc(MultipartFile profileimg){
        String fileNm = service.uploadProfileImg(profileimg);
        System.out.println("fileName : " + profileimg.getOriginalFilename());
        Map<String, String> result = new HashMap<>();
        /*UserEntity entity = (UserEntity) hs.getAttribute(Const.LOGIN_USER);
        entity.setProfileimg(fileNm);
        hs.setAttribute(Const.LOGIN_USER, entity);*/
        result.put("result", fileNm);
        return result;
    }

    @GetMapping("/mypage/password")
    public void password(){}

    @PostMapping("/mypage/password")
    public String passwordProc(UserDto dto, RedirectAttributes rAttr){
        int rs = service.changePassword(dto);
        if(rs != 1){
            switch (rs){
                case 0:
                    rAttr.addFlashAttribute(Const.MESSAGE, "비밀번호 변경에 실패하였습니다.");
                    break;
                case 2:
                    rAttr.addFlashAttribute(Const.MESSAGE, "현재 비밀번호를 확인해 주세요.");
                    break;
            }
            return "redirect:/user/mypage/password";
        }
        return "redirect:/user/logout";
    }
}
