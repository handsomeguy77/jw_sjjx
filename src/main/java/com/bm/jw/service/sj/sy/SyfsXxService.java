package com.bm.jw.service.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SyfsXxDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.List;
import java.util.Map;

public interface SyfsXxService {
    //翻页查询
    Map<String, Object> Find(SyfsXxDto params);

    //查询出所有数据，用于获取所有的实验室分室名
    List<SyfsXx> selectAll();

    //通过实验室名查找出该实验室管理的所有实验分室
    List<SyfsXx> selectSyfsmBySysm(SyfsXx syfsXx);
  //添加
    Result<SyfsXx> Create(SyfsXx syfsXx);

    //更新
    Result<SyfsXx> Update(SyfsXx syfsXx);

    //删除
    Result<SyfsXx> Delete(SyfsXx syfsXx);

    //批量删除
    Result<SyfsXx> DeleteBatch(DeleteBatchParam deleteBatchParam);

    //翻页查询--实验室管理员
    Map<String, Object> FindByDirector(SyfsXxDto params);

    //为实验分室添加负责人
    Result<SyfsXx> AddFzr(SyfsXx syfsXx);
}
