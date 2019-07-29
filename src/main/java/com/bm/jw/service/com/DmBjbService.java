package com.bm.jw.service.com;

import com.bm.jw.entity.com.DmBjb;
import com.bm.jw.entity.com.Dto.DmBjbDto;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.Map;

public interface DmBjbService {
    //翻页查询
    Map<String, Object> Find(DmBjbDto params);

    //添加
    Result<DmBjb> Create(DmBjb dmBjb);

    //更新
    Result<DmBjb> Update(DmBjb dmBjb);

    //删除
    Result<DmBjb> Delete(DmBjb dmBjb);

    //批量删除
    Result<DmBjb> DeleteBatch(DeleteBatchParam deleteBatchParam);
}
