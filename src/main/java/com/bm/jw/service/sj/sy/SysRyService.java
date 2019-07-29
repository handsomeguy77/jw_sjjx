package com.bm.jw.service.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SysRyDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.List;
import java.util.Map;

public interface SysRyService {
    //校级管理员--翻页查询
    Map<String, Object> Find(SysRyDto params);

    //校级管理员--添加实验室主任
    Result<SysRy> Create(SysRy sysRy);

    //校级管理员--更新
    Result<SysRy> Update(SysRy sysRy);

    //校级管理员--删除
    Result<SysRy> Delete(SysRy sysRy);

    //校级管理员--批量删除
    Result<SysRy> DeleteBatch(DeleteBatchParam deleteBatchParam);

    //实验室主任--翻页查询
    Map<String, Object> FindByDirector(SysRyDto params);

//    //查找出对应该用户所在实验室具体由哪些实验分室
//    List<SyfsXx> SelectSyfsm();
//
//    //实验室主任--添加实验室成员
//    Result<SysRy> CreateByDirector(SysRy sysRy);
//
//    //实验室主任--删除实验室成员
//    Result<SysRy> DeleteByDirector(SysRy sysRy);
//
//    //实验室主任--批量删除实验室成员
//    Result<SysRy> DeleteBatchByDirector(DeleteBatchParam deleteBatchParam);
}
