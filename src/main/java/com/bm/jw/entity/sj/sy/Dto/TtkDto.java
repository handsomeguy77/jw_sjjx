package com.bm.jw.entity.sj.sy.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TtkDto extends BaseParams {
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Syszr;   //实验室主任
    private String Syxmmc;  //实验项目名称
    private String kcm;     //课程名
    public TtkDto(Long page,Long limit){
        super(page,limit);
    }
}
