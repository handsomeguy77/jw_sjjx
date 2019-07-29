package com.bm.jw.dao;

import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.sj.sy.SysSykb;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("sj.sy.sysSykb")
public interface SysSykbDao extends BaseMapper<SysSykb> {
    public int deleteById(SysSykb sysSykb);
    public int deleteBatchById(@Param("Id") int Id);
    public List<DmKcb> selectDmKcbByKcm(SysSykb sysSykb);
    public List<DmJsb> selectDmJsbByZdjs(SysSykb sysSykb);
    public List<SysSykb> selectKcm();
}
