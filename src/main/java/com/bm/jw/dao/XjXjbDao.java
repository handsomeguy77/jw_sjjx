package com.bm.jw.dao;

import com.bm.jw.entity.com.XjXjb;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("com.xjXjb")
public interface XjXjbDao extends BaseMapper<XjXjb> {
    public List<XjXjb> selectByXh(XjXjb xjXjb);
    public int deleteById(XjXjb xjXjb);
    public int deleteBatchById(@Param("Id") int Id);
}
