package com.koreait.commuity.user;

import com.koreait.commuity.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserEntity selUser(UserEntity entity);
    int insUser(UserEntity entity);
    int updUser(UserEntity entity);
}
