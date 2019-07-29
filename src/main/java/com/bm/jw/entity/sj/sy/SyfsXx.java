package com.bm.jw.entity.sj.sy;

import lombok.Data;

@Data
public class SyfsXx {
    private Integer Id;
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Syfsm;  //实验室分室名
    private String Fjh;     //房间号
    private String Fzr;     //负责人
    private String IsDel;
    public SyfsXx(){
        this.IsDel="false";
    }
}
