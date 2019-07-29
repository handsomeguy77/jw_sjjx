package com.bm.jw.entity.sj.sy.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyfsXxDto extends BaseParams {
    private Integer Id;
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Syfsm;  //实验室分室名
    private String Fjh;     //房间号
    private String Fzr;     //负责人

    public SyfsXxDto(Long page,Long limit){
        super(page,limit);
    }
}