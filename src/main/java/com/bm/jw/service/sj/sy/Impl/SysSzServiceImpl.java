package com.bm.jw.service.sj.sy.Impl;

import com.bm.jw.dao.DmXyDao;
import com.bm.jw.dao.SysSzDao;
import com.bm.jw.entity.com.DmXy;
import com.bm.jw.entity.sj.sy.Dto.SysSzDto;
import com.bm.jw.entity.sj.sy.SysSz;
import com.bm.jw.service.sj.sy.SysSzService;
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
public class SysSzServiceImpl implements SysSzService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private SysSzDao sysSzDao;
    @Autowired
    private Result<SysSz> result;
    @Override
    public Map<String, Object> Find(SysSzDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.sysSz.pageQuery", SysSz.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public List<SysSz> selectAll() {
        return sysSzDao.selectAll();
    }

    @Override
    public List<SysSz> selectSysmByXym(SysSz sysSz) {
        return sysSzDao.selectByXym(sysSz);
    }

    @Override
    public Result<SysSz> Create(SysSz sysSz) {
        if(sysSz.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
            return result;
        }else if(sysSzDao.selectBySysm(sysSz).size()!=0){
            result.setStatus(0);
            result.setMsg("该实验室已存在");
            return result;
        }else{
            sysSzDao.insert(sysSz);
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<SysSz> Update(SysSz sysSz) {
        if(sysSz.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
            return result;
        }else{
            Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();       //设置事务回滚点
            sysSzDao.updateById(sysSz);
            if(sysSzDao.selectBySysm(sysSz).size()==2){     //判断更改后是否出现不能重复的数据
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                result.setStatus(0);
                result.setMsg("该实验室已存在");
                return result;
            }else{
                result.setStatus(200);
                result.setMsg("成功");
                return result;
            }
        }
    }

    @Override
    public Result<SysSz> Delete(SysSz sysSz) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=sysSzDao.deleteById(sysSz);       //设置判断，查看是否更新成功
        if(i==0){
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            result.setStatus(0);
            result.setMsg("失败，这条数据不存在");
            return result;
        }else{
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            List<SysSz> sysSzList=sysSzDao.selectById(sysSz);
            if(sysSzList.size()==0){
                result.setStatus(0);
                result.setMsg("失败，这条数据不存在");
                return result;
            }else{
                String syszr=sysSzList.get(0).getSyszr();      //获得实验室主任名
                if(syszr!=null){
                    sysSz.setSyszr(syszr);
                    sysSzDao.deleteSysUserBySyszr(sysSz);
                }
                sysSzDao.deleteById(sysSz);
                result.setMsg("成功");
                result.setStatus(200);
                return result;
            }
        }
    }

    @Override
    public Result<SysSz> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        SysSz sysSz=new SysSz();
        for(int i=0;i<arrayId.length;i++){
            sysSz.setId(arrayId[i]);
            List<SysSz> sysSzList=sysSzDao.selectById(sysSz);
            if(sysSzList.size()==0){
                result.setStatus(0);
                result.setMsg("失败，这些数据不存在");
                return result;
            }else{
                String syszr=sysSzList.get(0).getSyszr();      //获得实验室主任名
                if(syszr!=null){
                    sysSz.setSyszr(syszr);
                    sysSzDao.deleteSysUserBySyszr(sysSz);
                }
                sysSzDao.deleteById(sysSz);
            }
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }
}
