package com.bm.jw.entity.com;

import lombok.Data;

@Data
public class XjXjb{
    private Integer Id;
    private String Xh;      //学号
    private String Xm;      //姓名
    private String Xb;      //性别
    private String Xyh;     //学院号
    private String Xym;     //学院名
    private String Bm;      //班名
    private String Ssnj;    //所属年级
    private String IsDel;
    public XjXjb(){
        this.IsDel="false";
    }
}
