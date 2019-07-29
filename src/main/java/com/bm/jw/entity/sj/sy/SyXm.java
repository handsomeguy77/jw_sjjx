package com.bm.jw.entity.sj.sy;

import lombok.Data;

@Data
public class SyXm {
    private Integer Id;
    private String Sysm;        //实验室名
    private String Kch;         //课程号
    private String Syxmmc;      //实验项目名称
    private String Syxmbh;      //实验项目编号
    private Integer Syxs;           //实验学时
    private String Syfsm;       //实验分室名
    private String IsDel;
    public SyXm(){
        this.IsDel="false";
    }
}
