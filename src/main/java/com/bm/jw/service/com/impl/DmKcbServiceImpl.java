package com.bm.jw.service.com.impl;

import com.bm.jw.dao.DmKcbDao;
import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.com.Dto.DmKcbDto;
import com.bm.jw.service.com.DmKcbService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.LayuiUtils;
import com.bm.jw.utils.Result;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DmKcbServiceImpl implements DmKcbService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private DmKcbDao dmKcbDao;
    @Autowired private Result<DmKcb> result;
    @Override
    public Map<String, Object> Find(DmKcbDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("com.dmKcb.pageQuery", DmKcb.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public List<DmKcb> SelectKcm() {
        return dmKcbDao.selectAll();
    }

    @Override
    public Result<DmKcb> Create(DmKcb dmKcb) {
        if(dmKcb.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
            return result;
        }else if(dmKcbDao.selectByKch(dmKcb).size()!=0){
            result.setStatus(0);
            result.setMsg("该课程已存在");
            return result;
        }else{
            dmKcbDao.insert(dmKcb);
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<DmKcb> Update(DmKcb dmKcb) {
        if(dmKcb.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
            return result;
        }else{
            Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();       //设置事务回滚点
            dmKcbDao.updateById(dmKcb);
            if(dmKcbDao.selectByKch(dmKcb).size()==2){      //判断更改后是否出现不能重复的数据
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                result.setStatus(0);
                result.setMsg("该课程已存在");
                return result;
            }else{
                result.setStatus(200);
                result.setMsg("成功");
                return result;
            }
        }
    }

    @Override
    public Result<DmKcb> Delete(DmKcb dmKcb) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=dmKcbDao.deleteById(dmKcb);           //设置判断，查看是否更新成功
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

    @Override
    public Result<DmKcb> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        for(int i=0;i<arrayId.length;i++){
            dmKcbDao.deleteBatchById(arrayId[i]);
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }

}
