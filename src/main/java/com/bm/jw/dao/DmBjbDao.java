package com.bm.jw.dao;

import com.bm.jw.entity.com.DmBjb;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("com.dmBjb")
public interface DmBjbDao extends BaseMapper<DmBjb> {
    public int updateByID(DmBjb dmBjb);
    public List<DmBjb> selectByNjAndBm(DmBjb dmBjb);
    public int deleteById(DmBjb dmBjb);
    public int deleteBatchById(@Param("Id") int Id);
}
