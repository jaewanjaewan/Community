package com.koreait.commuity;

import com.koreait.commuity.model.BoardCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
    List<BoardCategoryEntity> selMenuCategoryList();
}
