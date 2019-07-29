package com.bm.jw.entity.com.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DmKcbDto extends BaseParams {
    private Integer Id;
    private String Kch;     //课程号
    private String Kcm;     //课程名
    private String Kkxq;    //开课学期
    private Float Xs;       //学时
    private Float Xf;       //学分
    private String Kslx;    //考试类型
    public DmKcbDto(Long page,Long limit){
        super(page,limit);
    }
}