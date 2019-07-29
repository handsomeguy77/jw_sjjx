package com.bm.jw.dao;

import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysRy;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("sj.sy.syfsXx")
public interface SyfsXxDao extends BaseMapper<SyfsXx> {
    public List<SyfsXx> selectBySysmAndSyfsm(SyfsXx syfsXx);
    public int deleteById(SyfsXx syfsXx);
    public int deleteBatchById(@Param("Id") int Id);
    public List<SyfsXx> selectAll();
    public List<SyfsXx> selectBySysm(SyfsXx syfsXx);
    public List<SyfsXx> selectByFzr(SyfsXx syfsXx);
    public int updateSysRyByGlfsmc(SysRy sysRy);
    public int updateByFzr(SysRy sysRy);
}
