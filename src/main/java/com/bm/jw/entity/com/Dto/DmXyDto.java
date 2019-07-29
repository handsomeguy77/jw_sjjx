package com.bm.jw.entity.com.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DmXyDto extends BaseParams {
    private Integer Id;
    private String Xyh;     //学院号
    private String Xym;     //学院名
    public DmXyDto(Long page,Long limit){
        super(page,limit);
    }
}
