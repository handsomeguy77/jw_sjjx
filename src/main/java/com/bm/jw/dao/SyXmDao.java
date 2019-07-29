package com.bm.jw.dao;

import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.sj.sy.SyXm;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("sj.sy.syXm")
public interface SyXmDao extends BaseMapper<SyXm> {
    public List<DmKcb> selectDmKcbByKch(SyXm syXm);
    public int deleteById(SyXm syXm);
    public int deleteBatchById(@Param("Id") int Id);
}
