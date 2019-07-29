package com.bm.jw.service.com.impl;

import com.bm.jw.dao.DmXqbDao;
import com.bm.jw.entity.com.DmXqb;
import com.bm.jw.entity.com.Dto.DmXqbDto;
import com.bm.jw.service.com.DmXqbService;
import com.bm.jw.utils.LayuiUtils;
import com.bm.jw.utils.Result;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Map;

@Service
@Transactional
public class DmXqbServiceImpl implements DmXqbService {

    @Autowired
    SQLManager sqlManager;
    @Autowired
    private DmXqbDao dmXqbDao;
    @Autowired private Result<DmXqb> result;

    @Override
    public Map<String, Object> Find(DmXqbDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("com.dmXqb.pageQuery", DmXqb.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Result<DmXqb> Create(DmXqb dmXqb) {
        if(dmXqb.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
            return result;
        }else if(dmXqbDao.selectByXqmc(dmXqb).size()!=0){
            result.setStatus(0);
            result.setMsg("该学期名称已存在");
            return result;
        }else{
            dmXqbDao.insert(dmXqb);
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<DmXqb> Update(DmXqb dmXqb) {
        if(dmXqb.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
            return result;
        }else{
            Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();       //设置事务回滚点
            dmXqbDao.updateById(dmXqb);
            if(dmXqbDao.selectByXqmc(dmXqb).size()==2){     //判断更改后是否出现不能重复的数据
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                result.setStatus(0);
                result.setMsg("该学期名称已存在");
                return result;
            }else{
                result.setStatus(200);
                result.setMsg("成功");
                return result;
            }
        }
    }

    @Override
    public Result<DmXqb> Delete(DmXqb dmXqb) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=dmXqbDao.deleteById(dmXqb);           //设置判断，查看是否更新成功
        if(i==0){
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            result.setStatus(0);
            result.setMsg("失败，这条数据不存在");
        }else{
            result.setMsg("成功");
            result.setStatus(200);
        }
        return result;
    }
}
