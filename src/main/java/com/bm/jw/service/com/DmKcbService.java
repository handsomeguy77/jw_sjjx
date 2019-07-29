package com.bm.jw.service.com;

import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.com.Dto.DmKcbDto;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.List;
import java.util.Map;

public interface DmKcbService {
    //翻页查询
    Map<String, Object> Find(DmKcbDto params);

    //查询所有课程名
    List<DmKcb> SelectKcm();

    //添加
    Result<DmKcb> Create(DmKcb dmKcb);

    //更新
    Result<DmKcb> Update(DmKcb dmKcb);

    //删除
    Result<DmKcb> Delete(DmKcb dmKcb);

    //批量删除
    Result<DmKcb> DeleteBatch(DeleteBatchParam deleteBatchParam);

}
