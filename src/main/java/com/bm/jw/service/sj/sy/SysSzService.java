package com.bm.jw.service.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SysSzDto;
import com.bm.jw.entity.sj.sy.SysSz;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.List;
import java.util.Map;

public interface SysSzService {
    //翻页查询
    Map<String, Object> Find(SysSzDto params);

    //查询出所有数据，用于获取所有的学院名
    List<SysSz> selectAll();

    //通过学院名查找出该学院下的所有实验室名
    List<SysSz> selectSysmByXym(SysSz sysSz);

    //添加
    Result<SysSz> Create(SysSz sysSz);

    //更新
    Result<SysSz> Update(SysSz sysSz);

    //删除
    Result<SysSz> Delete(SysSz sysSz);

    //批量删除
    Result<SysSz> DeleteBatch(DeleteBatchParam deleteBatchParam);
}
