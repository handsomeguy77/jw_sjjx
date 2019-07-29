package com.bm.jw.entity.sj.sy.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysRyDto extends BaseParams {
    private Integer Id;
    private String Xym;     //学院名
    private String Sysm;    //实验室名
    private String Jsm;     //教师名
    private String Zw;      //职位
    private String Glfsmc;  //管理分室名称
    public SysRyDto(Long page, Long limit){
        super(page,limit);
    }
}