package com.bm.jw.service.com;

import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.com.Dto.DmJsbDto;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.List;
import java.util.Map;

public interface DmJsbService {
    //翻页查询
    Map<String, Object> Find(DmJsbDto params);

    //通过学院名查找出该学院下的所有教师
    List<DmJsb> SelectJsmByXym(DmJsb dmJsb);

    //添加
    Result<DmJsb> Create(DmJsb dmJsb);

    //更新
    Result<DmJsb> Update(DmJsb dmJsb);

    //删除
    Result<DmJsb> Delete(DmJsb dmJsb);

    //批量删除
    Result<DmJsb> DeleteBatch(DeleteBatchParam deleteBatchParam);
}
