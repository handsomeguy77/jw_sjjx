package com.bm.jw.service.com.impl;

import com.bm.jw.dao.DmJsbDao;
import com.bm.jw.dao.DmXyDao;
import com.bm.jw.entity.SysUser;
import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.com.DmXy;
import com.bm.jw.entity.com.Dto.DmJsbDto;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.entity.sj.sy.SysSz;
import com.bm.jw.service.com.DmJsbService;
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
public class DmJsbServiceImpl implements DmJsbService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private DmJsbDao dmJsbDao;
    @Autowired private DmXyDao dmXyDao;
    @Autowired private Result<DmJsb> result;
    @Override
    public Map<String, Object> Find(DmJsbDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("com.dmJsb.pageQuery", DmJsb.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public List<DmJsb> SelectJsmByXym(DmJsb dmJsb) {
        return dmJsbDao.selectByXym(dmJsb);
    }

    @Override
    public Result<DmJsb> Create(DmJsb dmJsb) {
        DmXy dmXy=new DmXy();
        dmXy.setXym(dmJsb.getXym());
        if(dmJsb.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
        }else if(dmJsbDao.selectByJsh(dmJsb).size()!=0){
            result.setStatus(0);
            result.setMsg("该教师已存在");
        }else if(dmXyDao.selectByXym(dmXy).size()==0){
            result.setStatus(0);
            result.setMsg("该学院不存在");
        }else{
            dmJsb.setXyh(dmXyDao.selectByXym(dmXy).get(0).getXyh());        //学院名转换为学院号
            dmJsbDao.insert(dmJsb);
            result.setStatus(200);
            result.setMsg("成功");
        }
        return result;
    }

    @Override
    public Result<DmJsb> Update(DmJsb dmJsb) {
        DmXy dmXy=new DmXy();
        dmXy.setXym(dmJsb.getXym());
        if(dmJsb.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
        }else{
            DmJsb oldDmJsb=dmJsbDao.selectById(dmJsb).get(0);       //查询并保留编辑前的原数据
            dmJsb.setXyh(dmXyDao.selectByXym(dmXy).get(0).getXyh());        //学院名转换为学院号
            Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();       //设置事务回滚点
            dmJsbDao.updateById(dmJsb);
            if(dmJsbDao.selectByJsh(dmJsb).size()==2){      //判断更改后是否出现不能重复的数据
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                result.setStatus(0);
                result.setMsg("该教师已存在");
            }else{
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                if(oldDmJsb.getJsm()!=dmJsb.getJsm()){                  //比较是否更改了教师名
                    String Jsm=oldDmJsb.getJsm();            //保留原来的教师名
                    oldDmJsb.setJsm(dmJsb.getJsm());        //将oldDmJsb设置为新的教师名
                    //更改实验室人员表
                    List<SysRy> sysRyList= dmJsbDao.selectSysRyIdByJsm(Jsm);
                    for(int i=0;i<sysRyList.size();i++){
                        oldDmJsb.setId(sysRyList.get(i).getId());
                        dmJsbDao.updateSysRyById(oldDmJsb);
                    }
                    //更改实验室表
                    List<SysSz> sysSzList=dmJsbDao.selectSysSzIdByJsm(Jsm);
                    for(int i=0;i<sysSzList.size();i++){
                        oldDmJsb.setId(sysSzList.get(i).getId());
                        dmJsbDao.updateSysSzByJsm(oldDmJsb);
                    }
                    //更改用户表
                    List<SysUser> sysUserList=dmJsbDao.selectSysUserIdByJsm(Jsm);
                    for(int i=0;i<sysUserList.size();i++){
                        oldDmJsb.setId(sysUserList.get(i).getId());
                        dmJsbDao.updateSysUserById(oldDmJsb);
                    }
                }
                dmJsbDao.updateById(dmJsb);
                result.setStatus(200);
                result.setMsg("成功");
            }
        }
        return result;
    }

    @Override
    public Result<DmJsb> Delete(DmJsb dmJsb) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=dmJsbDao.deleteById(dmJsb);           //设置判断，查看是否更新成功
        if(i==0){
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            result.setStatus(0);
            result.setMsg("失败，这条数据不存在");
        }else{
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            List<DmJsb> dmJsbList=dmJsbDao.selectById(dmJsb);
            if(dmJsbList.size()==0){                //判断通过id查询该用户是否存在
                result.setStatus(0);
                result.setMsg("失败，这条数据不存在");
            }else{
                dmJsb=dmJsbDao.selectById(dmJsb).get(0);            //查找出该id号对应的教师详细信息
                String jsm=dmJsb.getJsm();              //保存该用户的教师名
                dmJsb.setJsm(null);
                dmJsbDao.updateSysSzByJsm(dmJsb);        //把该实验室对应的实验室主任置位null
                dmJsb.setJsm(jsm);              //还原教师名
                dmJsbDao.deleteSysUserByJsm(dmJsb);     //同时把该教师从用户表中删除
                dmJsbDao.deleteSysRyByJsm(dmJsb);       //把该教师从实验室人员表中删除
                dmJsbDao.deleteById(dmJsb);         //把该教师从教师表中删除出
                result.setMsg("成功");
                result.setStatus(200);
            }
        }
        return result;
    }

    @Override
    public Result<DmJsb> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        for(int i=0;i<arrayId.length;i++){
            DmJsb dmJsb=new DmJsb();
            dmJsb.setId(arrayId[i]);
            List<DmJsb> dmJsbList=dmJsbDao.selectById(dmJsb);
            if(dmJsbList.size()==0){
                result.setStatus(0);
                result.setMsg("失败，这些数据不存在");
                return result;
            }else{
                dmJsb=dmJsbList.get(0);            //查找出该id号对应的教师详细信息
                String jsm=dmJsb.getJsm();              //保存该用户的教师名
                dmJsb.setJsm(null);
                dmJsbDao.updateSysSzByJsm(dmJsb);        //把该实验室对应的实验室主任置位null
                dmJsb.setJsm(jsm);              //还原教师名
                dmJsbDao.deleteSysUserByJsm(dmJsb);     //同时把该教师从用户表中删除
                dmJsbDao.deleteSysRyByJsm(dmJsb);       //把该教师从实验室人员表中删除
                dmJsbDao.deleteById(dmJsb);         //把该教师从教师表中删除出
            }
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }
}
