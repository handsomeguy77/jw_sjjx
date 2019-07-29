package com.bm.jw.entity.com.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DmJsbDto extends BaseParams {
    private Integer Id;
    private String Jsh;     //教师号
    private String Jsm;     //教师名
    private String Xb;      //性别
    private String Zc;      //职称
    private String Xyh;     //学院号
    private String Xym;     //学院名
    public DmJsbDto(Long page,Long limit){
        super(page,limit);
    }
}