package com.bm.jw.entity.sj.sy;

import lombok.Data;

@Data
public class SysSz {
    private Integer Id;
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Syszr;   //实验室主任
    private String IsDel;
    public SysSz(){
        this.IsDel="false";
    }
}
