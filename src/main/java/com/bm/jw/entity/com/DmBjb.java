package com.bm.jw.entity.com;

import lombok.Data;

@Data
public class DmBjb{
    private Integer Id;
    private String Nj;      //年级
    private String Bm;      //班名
    private String Xyh;     //学院号
    private String Xym;     //学院名
    private String IsDel;
    public DmBjb(){
        this.IsDel="false";
    }
}
