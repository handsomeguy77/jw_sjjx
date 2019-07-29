package com.bm.jw.service.com;

import com.bm.jw.entity.com.DmXy;
import com.bm.jw.entity.com.Dto.DmXyDto;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.List;
import java.util.Map;

public interface DmXyService {
    //翻页查询
    Map<String, Object> Find(DmXyDto params);

    //查询出所有数据，用于获取所有的学院名
    List<DmXy> selectAll();

    //添加
    Result<DmXy> Create(DmXy dmXy);

    //更新
    Result<DmXy> Update(DmXy dmXy);

    //删除
    Result<DmXy> Delete(DmXy dmXy);

}
