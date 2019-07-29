package com.bm.jw.dao;

import com.bm.jw.entity.sj.sy.SysSz;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("sj.sy.sysSz")
public interface SysSzDao extends BaseMapper<SysSz> {
    public List<SysSz> selectBySysm(SysSz sysSz);
    public List<SysSz> selectByXym(SysSz sysSz);
    public List<SysSz> selectById(SysSz sysSz);
    public int deleteById(SysSz sysSz);
    public int deleteSysUserBySyszr(SysSz sysSz);
    public List<SysSz> selectAll();
}
