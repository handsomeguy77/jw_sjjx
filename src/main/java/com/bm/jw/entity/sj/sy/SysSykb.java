package com.bm.jw.entity.sj.sy;

import lombok.Data;

@Data
public class SysSykb {
    private Integer Id;
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Zc;      //周次
    private String Xq;      //星期
    private String Kch;     //课程号
    private String Kcm;     //课程名
    private String Syxm;    //实验项目
    private String Sylb;    //实验类别
    private String Fjh;     //房间号
    private String zybj;    //专业班级
    private String Rs;      //人数
    private String Syxs;    //实验学时
    private String Zdjs;    //指导教师
    private String Jsh;     //教师号
    private String Kcxq;    //课程学期
    private String Jc;      //节次
    private String Kssj;    //开始时间
    private String Jssj;    //结束时间
    private int Ksjc;       //开始节次
    private int Jsjc;       //结束节次
    private String IsDel;
    public SysSykb(){
        this.IsDel="false";
    }
}
