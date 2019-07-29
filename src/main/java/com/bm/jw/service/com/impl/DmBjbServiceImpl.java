package com.bm.jw.service.com.impl;

import com.bm.jw.dao.DmBjbDao;
import com.bm.jw.dao.DmXyDao;
import com.bm.jw.entity.com.DmBjb;
import com.bm.jw.entity.com.DmXy;
import com.bm.jw.entity.com.Dto.DmBjbDto;
import com.bm.jw.service.com.DmBjbService;
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
public class DmBjbServiceImpl implements DmBjbService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private DmBjbDao dmBjbDao;
    @Autowired private DmXyDao dmXyDao;
    @Autowired private Result<DmBjb> result;
    @Override
    public Map<String, Object> Find(DmBjbDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("com.dmBjb.pageQuery", DmBjb.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Result<DmBjb> Create(DmBjb dmBjb) {
        DmXy dmXy=new DmXy();
        dmXy.setXym(dmBjb.getXym());
        if(dmBjb.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
        }else if(dmBjbDao.selectByNjAndBm(dmBjb).size()!=0){
            result.setStatus(0);
            result.setMsg("该班级已存在");
        }else if(dmXyDao.selectByXym(dmXy).size()==0){
            result.setStatus(0);
            result.setMsg("该学院不存在");
        }else{
            dmBjb.setXyh(dmXyDao.selectByXym(dmXy).get(0).getXyh());        //学院名转换为学院号
            dmBjbDao.insert(dmBjb);
            result.setStatus(200);
            result.setMsg("成功");
        }
        return result;
    }

    @Override
    public Result<DmBjb> Update(DmBjb dmBjb) {
        DmXy dmXy=new DmXy();
        dmXy.setXym(dmBjb.getXym());
        if(dmBjb.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
        }else{
            Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();       //设置事务回滚点
            if(dmBjb.getXym()!=null){
                dmBjb.setXyh(dmXyDao.selectByXym(dmXy).get(0).getXyh());        //学院名转换为学院号
            }
            dmBjbDao.updateByID(dmBjb);
            if(dmBjbDao.selectByNjAndBm(dmBjb).size()==2){      //判断更改后是否出现不能重复的数据
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                result.setStatus(0);
                result.setMsg("该班级已存在");
            }else{
                result.setStatus(200);
                result.setMsg("成功");
            }
        }
        return result;
    }

    @Override
    public Result<DmBjb> Delete(DmBjb dmBjb) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=dmBjbDao.deleteById(dmBjb);           //设置判断，查看是否更新成功
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
    public Result<DmBjb> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        for(int i=0;i<arrayId.length;i++){
            dmBjbDao.deleteBatchById(arrayId[i]);
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }
}
