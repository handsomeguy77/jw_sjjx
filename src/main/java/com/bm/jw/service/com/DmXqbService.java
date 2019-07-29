package com.bm.jw.service.com;

import com.bm.jw.entity.com.DmXqb;
import com.bm.jw.entity.com.Dto.DmXqbDto;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.Map;

public interface DmXqbService {
    //翻页查询
    Map<String, Object> Find(DmXqbDto params);

    //添加
    Result<DmXqb> Create(DmXqb dmXqb);

    //更新
    Result<DmXqb> Update(DmXqb dmXqb);

    //删除
    Result<DmXqb> Delete(DmXqb dmXqb);

}
