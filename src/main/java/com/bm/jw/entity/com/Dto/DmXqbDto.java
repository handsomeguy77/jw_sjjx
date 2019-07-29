package com.bm.jw.entity.com.Dto;

import com.bm.jw.utils.BaseParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DmXqbDto extends BaseParams {
    private Integer Id;
    private String Xqmc;    //学期名称
    public DmXqbDto(Long page,Long limit){
        super(page,limit);
    }
}