package com.bm.jw.entity.com;

import lombok.Data;

/**
 * 这对应数据库中的DM_XQB
 *
 * */

@Data
public class DmXqb{
    private Integer Id;
    private String Xqmc;    //学期名称
    private String IsDel;
    public DmXqb(){
        this.IsDel="false";
    }
}
