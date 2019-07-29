package com.bm.jw.dao;

import com.bm.jw.entity.com.DmKcb;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("com.dmKcb")
public interface DmKcbDao extends BaseMapper<DmKcb> {
    public List<DmKcb> selectByKch(DmKcb dmKcb);
    public int deleteById(DmKcb dmKcb);
    public int deleteBatchById(@Param("Id") int Id);
    public List<DmKcb> selectAll();
}
