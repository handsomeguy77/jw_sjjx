package com.bm.jw.entity.com;

import lombok.Data;

@Data
public class DmJsb{
    private Integer Id;
    private String Jsh;     //教师号
    private String Jsm;     //教师名
    private String Xb;      //性别
    private String Zc;      //职称
    private String Xyh;     //学院号
    private String Xym;     //学院名(教师表中不存在该字段)
    private String IsDel;
    public DmJsb(){
        this.IsDel="false";
    }
}
