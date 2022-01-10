package com.koreait.commuity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVo extends BoardEntity{
    private String writernm;
    private String profileimg;
    private String categorynm;
}
