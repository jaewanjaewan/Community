package com.koreait.commuity.user;

import com.koreait.commuity.Const;
import com.koreait.commuity.MyFileUtils;
import com.koreait.commuity.UserUtils;
import com.koreait.commuity.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private HttpSession hs;
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private MyFileUtils fileUtils;

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

    //이미지 업로드 처리 및 저장 파일명 리턴
    public String uploadProfileImg(MultipartFile mf){
        if(mf == null) { return null; }
        UserEntity loginUser = userUtils.getLoginUser();

        final String PATH = Const.UPLOAD_IMG_PATH + "/user/" + loginUser.getIuser();
        String fileNm = fileUtils.saveFiles(PATH, mf);
        if(fileNm == null){ return null; }

        UserEntity entity = new UserEntity();
        entity.setIuser(loginUser.getIuser());

        //기존 파일명
        String oldFilePath = PATH + "/" + userUtils.getLoginUser().getProfileimg();
        fileUtils.delFile(oldFilePath);

        //파일명을 t_user 테이블에 update
        entity.setProfileimg(fileNm);
        mapper.updUser(entity);

        //세션 프로필 파일명을 수정해준다.
        loginUser.setProfileimg(fileNm); //주소값으로 접근하기때문에 setattribute할필요X
        return fileNm;
    }
}
