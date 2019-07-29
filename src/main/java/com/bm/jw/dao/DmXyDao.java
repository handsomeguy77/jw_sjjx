package com.bm.jw.dao;

import com.bm.jw.entity.com.DmXy;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("com.dmXy")
public interface DmXyDao extends BaseMapper<DmXy> {
    public int updateByID(DmXy dmXy);
    public List<DmXy> selectByXyh(DmXy dmXy);
    public List<DmXy> selectByXym(DmXy dmXy);
    public int deleteById(DmXy dmXy);
    public List<DmXy> selectAll();
}
