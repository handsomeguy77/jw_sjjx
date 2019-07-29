package com.bm.jw.entity.com;

import lombok.Data;

@Data
public class DmKcb{
    private Integer Id;
    private String Kch;     //课程号
    private String Kcm;     //课程名
    private String Kkxq;    //开课学期
    private Float Xs;       //学时
    private Float Xf;       //学分
    private String Kslx;    //考试类型
    private String IsDel;
    public DmKcb(){
        this.IsDel="false";
    }
}
