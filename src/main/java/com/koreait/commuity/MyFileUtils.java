package com.koreait.commuity;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class MyFileUtils {

    //폴더 만들기
    public void makeFolders(String path){
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    //폴더 삭제
    public void delFolderFiles(String path, boolean isDelFolder){
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File[] fileArr = file.listFiles(); //그폴더에있는 폴더, 사진들이 파일객체로 파일배열에 저장
                for(File f : fileArr) {
                    if (f.isDirectory()) { //재귀처리
                        delFolderFiles(f.getPath(), true);
                    } else {
                            f.delete();
                        }
                    }
                }
        if(isDelFolder){ //isDelFolder가 true이면 폴더안에 파일이없다는뜻으로써 폴더를 삭제한다
            file.delete();
        }
    }

    public void delFile(String path){
        File f = new File(path);
        if(f.exists()) {
            f.delete();
        }
    }

    //랜덤파일명 만들기
    public String getRandomFileNm(){
        return UUID.randomUUID().toString(); //중복된값이 안나옴
    }

    public String getRandomFileNm(String fileNm){
        return getRandomFileNm() + getExt(fileNm); //랜덤한 파일명을 만들어주고 확장자는 유지
    }

    //확장자 구하기
    public String getExt(String fileNm){
        return fileNm.substring(fileNm.lastIndexOf('.')); //substring : 인덱스로자른다
//        String[] ext = fileNm.split(".");
//        return "." + ext[ext.length-1];
    }

    //파일 저장 > 저장된 랜덤 파일명 리턴
    public String saveFiles(String path, MultipartFile mf){
        makeFolders(path);
        String randomFileNm = getRandomFileNm(mf.getOriginalFilename());
        File targetFile = new File(path, randomFileNm);
        try {
            mf.transferTo(targetFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        return randomFileNm;
    }
}
