package com.bm.jw.dao;

import com.bm.jw.entity.SysUser;
import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.entity.sj.sy.SysSz;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("sj.sy.sysRy")
public interface SysRyDao extends BaseMapper<SysRy> {
    public List<DmJsb> selectDmJsbByJsm(SysRy sysRy);
    public int insertIntoSysUser(SysUser sysUser);
    public List<SysRy> selectByJsm(SysRy sysRy);
    public List<SysSz> selectSysSzBySysm(SysRy sysRy);
    public void updateSysSzByJsm(SysRy sysRy);
    public void deleteSysUserByJsm(SysRy sysRy);
    public int deleteById(SysRy sysRy);
    public List<SysRy> selectById(SysRy sysRy);
    public int updateSysSzBySysmAndXym(SysRy sysRy);
    public List<SysUser> selectSysUserByUsername(@Param("Username") String Username);
    public List<SyfsXx> selectSyfsXxBySysm(@Param("Sysm") String Sysm);
}
