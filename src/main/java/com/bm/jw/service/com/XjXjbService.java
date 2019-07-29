package com.bm.jw.service.com;

import com.bm.jw.entity.com.DmBjb;
import com.bm.jw.entity.com.Dto.XjXjbDto;
import com.bm.jw.entity.com.XjXjb;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.Map;

public interface XjXjbService {
    //翻页查询
    Map<String, Object> Find(XjXjbDto params);

    //添加
    Result<XjXjb> Create(XjXjb xjXjb);

    //更新
    Result<XjXjb> Update(XjXjb xjXjb);

    //删除
    Result<XjXjb> Delete(XjXjb xjXjb);

    //批量删除
    Result<XjXjb> DeleteBatch(DeleteBatchParam deleteBatchParam);
}
