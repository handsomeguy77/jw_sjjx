package com.bm.jw.service.sj.sy;

import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.sj.sy.Dto.SysSykbDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysSykb;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;

import java.util.List;
import java.util.Map;

public interface SysSykbService {
    //翻页查询
    Map<String, Object> Find(SysSykbDto params);

    //添加
    Result<SysSykb> Create(SysSykb sysSykb);

    //更新
    Result<SysSykb> Update(SysSykb sysSykb);

    //删除
    Result<SysSykb> Delete(SysSykb sysSykb);

    //批量删除
    Result<SysSykb> DeleteBatch(DeleteBatchParam deleteBatchParam);

    //通过课程名查询课程表获取对应课程的课程号
    List<DmKcb> SelectKch(SysSykb sysSykb);

    //通过指导教师名查询教师表获取对应教师的教师号
    List<DmJsb> SelectJsh(SysSykb sysSykb);

    //翻页查询--实验室管理员
    Map<String, Object> FindByDirector(SysSykbDto params);


    //调停课中，查询课表中的课程
    Map<String, Object> SelectKcm(SysSykbDto params);

    //调停课中，通过课程名查询该课程的所有周次
    Map<String, Object> SelectByKcm(SysSykbDto params);
}
