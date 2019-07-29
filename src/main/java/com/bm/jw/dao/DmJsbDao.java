package com.bm.jw.dao;

import com.bm.jw.entity.SysUser;
import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.entity.sj.sy.SysSz;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("com.dmJsb")
public interface DmJsbDao extends BaseMapper<DmJsb> {
    public List<DmJsb> selectByJsh(DmJsb dmJsb);
    public List<DmJsb> selectByXym(DmJsb dmJsb);
    public int deleteById(DmJsb dmJsb);
    public List<DmJsb> selectById(DmJsb dmJsb);
    public List<SysSz> selectSysSzByJsm(DmJsb dmJsb);
    public int updateSysSzByJsm(DmJsb dmJsb);
    public int deleteSysUserByJsm(DmJsb dmJsb);
    public int deleteSysRyByJsm(DmJsb dmJsb);
    public List<SysSz> selectSysSzIdByJsm(@Param("Jsm") String Jsm);
    public List<SysRy> selectSysRyIdByJsm(@Param("Jsm") String Jsm);
    public List<SysUser> selectSysUserIdByJsm(@Param("Jsm") String Jsm);
    public int updateSysRyById(DmJsb dmJsb);
    public int updateSysUserById(DmJsb dmJsb);
}
