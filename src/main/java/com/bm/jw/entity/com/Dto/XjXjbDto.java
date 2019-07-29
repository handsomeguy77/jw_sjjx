package com.bm.jw.entity.com.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class XjXjbDto extends BaseParams {
    private Integer Id;
    private String Xh;      //学号
    private String Xm;      //姓名
    private String Xb;      //性别
    private String Xyh;     //学院号
    private String Xym;     //学院名
    private String Bm;      //班名
    private String Ssnj;    //所属年级
    public XjXjbDto(Long page,Long limit){
        super(page,limit);
    }
}