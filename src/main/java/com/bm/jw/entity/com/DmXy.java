package com.bm.jw.entity.com;

import lombok.Data;

@Data
public class DmXy{
    private Integer Id;
    private String Xyh;     //学院号
    private String Xym;     //学院名
    private String IsDel;
    public DmXy(){
        this.IsDel="false";
    }
}
