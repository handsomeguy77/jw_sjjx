package com.bm.jw.dao;

import com.bm.jw.entity.com.DmXqb;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("com.dmXqb")
public interface DmXqbDao extends BaseMapper<DmXqb> {
    List<DmXqb> selectByXqmc(DmXqb dmXqb);
    public int deleteById(DmXqb dmXqb);
}
