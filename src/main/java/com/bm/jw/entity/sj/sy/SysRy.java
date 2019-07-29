package com.bm.jw.entity.sj.sy;

import lombok.Data;

@Data
public class SysRy {
    private Integer Id;
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Jsm;     //教师名
    private String Zw;      //职位
    private String Glfsmc;  //管理分室名称
    private String Jsh;     //教师号(该实验室人员表格中没有该字段)
    private String IsDel;
    public SysRy(){
        this.IsDel="false";
    }
}
