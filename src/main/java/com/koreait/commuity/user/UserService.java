package com.koreait.commuity.user;

import com.koreait.commuity.Const;
import com.koreait.commuity.UserUtils;
import com.koreait.commuity.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private HttpSession hs;
    @Autowired
    private UserUtils userUtils;

    //아이디가 없으면 리턴1, 있으면 리턴 0
    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        UserEntity result = mapper.selUser(entity);

        if(result == null){
            return 1;
        } else {
            return 0;
        }
    }

    public int join(UserEntity entity){
        String hashpw = BCrypt.hashpw(entity.getUpw(), BCrypt.gensalt());
        entity.setUpw(hashpw);
        int result = mapper.insUser(entity);
        return result;
    }

    public int login(UserEntity entity){
        UserEntity loginUser = null;
        loginUser = mapper.selUser(entity);
        if(loginUser == null){
            return 2; //아이디 없음
        } else if(BCrypt.checkpw(entity.getUpw(), loginUser.getUpw())){
            loginUser.setUpw(null);
            loginUser.setRdt(null);
            loginUser.setMdt(null);
            userUtils.setLoginUser(loginUser);
            return 1; //로그인 성공
        }
        return 3; //비밀번호 틀림
    }
}
