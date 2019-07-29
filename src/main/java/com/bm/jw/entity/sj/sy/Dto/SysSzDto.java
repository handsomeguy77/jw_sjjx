package com.bm.jw.entity.sj.sy.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysSzDto extends BaseParams {
    private Integer Id;
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Syszr;   //实验室主任
    public SysSzDto(Long page,Long limit ){
        super(page,limit);
    }
}
