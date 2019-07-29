package com.bm.jw.service.sj.sy.Impl;

import com.bm.jw.dao.TtkDao;
import com.bm.jw.entity.sj.sy.Dto.TtkDto;
import com.bm.jw.entity.sj.sy.SyXm;
import com.bm.jw.entity.sj.sy.Ttk;
import com.bm.jw.service.sj.sy.TtkService;
import com.bm.jw.utils.LayuiUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class TtkServiceImpl implements TtkService {
    @Autowired
    SQLManager sqlManager;
    @Autowired TtkDao ttkDao;
}
