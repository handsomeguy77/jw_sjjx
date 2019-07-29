package com.bm.jw.service.sj.sy.Impl;

import com.bm.jw.dao.SysRyDao;
import com.bm.jw.entity.SysUser;
import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.sj.sy.Dto.SysRyDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.service.sj.sy.SysRyService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.LayuiUtils;
import com.bm.jw.utils.Result;
import com.bm.jw.utils.UserParams;
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
public class SysRyServiceImpl implements SysRyService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private SysRyDao sysRyDao;
    @Autowired Result<SysRy> result;
    @Autowired private UserParams userParams;

    @Override
    public Map<String, Object> Find(SysRyDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.sysRy.pageQuery", SysRy.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Result<SysRy> Create(SysRy sysRy) {
        if(sysRy.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
            return result;
        }else if(sysRyDao.selectDmJsbByJsm(sysRy).size()==0){
            result.setStatus(0);
            result.setMsg("该教师不存在");
            return result;
        }else if(sysRyDao.selectByJsm(sysRy).size()!=0){
            result.setStatus(0);
            result.setMsg("该学院下已存在该教师");
            return result;
        }else{
            if(sysRy.getZw()==null){    //验证职务是否为实验室主任
                sysRyDao.insert(sysRy);
            }else{      //若是实验室主任，则验证该实验室是否已经存在实验室主任
                if(sysRyDao.selectSysSzBySysm(sysRy).get(0).getSyszr()!=null){
                    result.setStatus(0);
                    result.setMsg("该实验室已经有实验室主任了");
                    return result;
                }else{
                    sysRyDao.updateSysSzByJsm(sysRy);   //给实验室表对应的实验室加上实验室主任
                    sysRyDao.insert(sysRy);     //给实验室人员表格加上该人员
                }
            }
            SysUser sysUser=new SysUser();
            sysUser.setSysm(sysRy.getSysm());       //编辑用户信息的实验室名
            sysUser.setXym(sysRy.getXym());         //编辑用户信息的学院名
            DmJsb dmJsb=sysRyDao.selectDmJsbByJsm(sysRy).get(0);        //通过教师名查询到该教师的详细信息
            sysUser.setJsh(dmJsb.getJsh());         //编辑用户信息的教师号
            sysUser.setUsername(dmJsb.getJsh());    //编辑用户信息的用户名名，即为教师号
            sysUser.setXm(dmJsb.getJsm());          //编辑用户信息的姓名
            sysRyDao.insertIntoSysUser(sysUser);      //往用户表（SYS_USER）中添加该用户
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<SysRy> Update(SysRy sysRy) {
        if(sysRy.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
            return result;
        }else if(sysRyDao.selectDmJsbByJsm(sysRy).size()==0){
            result.setStatus(0);
            result.setMsg("该教师不存在");
            return result;
        } else{
            Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();       //设置事务回滚点
            sysRyDao.updateById(sysRy);
            if(sysRyDao.selectByJsm(sysRy).size()==2){     //判断更改后是否出现不能重复的数据,若出现则回滚
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                result.setStatus(0);
                result.setMsg("该学院下已存在该教师");
                return result;
            }else{
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
                if(sysRy.getZw()==null){        //判断职务字段是否为空
                    //判断更改的数据，教师名是否与实验室表中，对应的实验室的实验室主任名字相同
                    if(sysRy.getJsm().equals(sysRyDao.selectSysSzBySysm(sysRy).get(0).getSyszr())){
                        sysRy.setJsm(null);
                        sysRyDao.updateSysSzByJsm(sysRy);       //将实验室表中该实验室的实验室主任设置为null
                    }
                }else{          //若职务字段选中为实验室主任
                    sysRyDao.updateSysSzByJsm(sysRy);   //给实验室表对应的实验室加上实验室主任
                }
                sysRyDao.deleteSysUserByJsm(sysRyDao.selectById(sysRy).get(0));     //通过教师名把用户表中该用户删去
                SysUser sysUser=new SysUser();
                sysUser.setSysm(sysRy.getSysm());       //编辑用户信息的实验室名
                sysUser.setXym(sysRy.getXym());         //编辑用户信息的学院名
                DmJsb dmJsb=sysRyDao.selectDmJsbByJsm(sysRy).get(0);        //通过教师名查询到该教师的详细信息
                sysUser.setJsh(dmJsb.getJsh());         //编辑用户信息的教师号
                sysUser.setUsername(dmJsb.getJsh());    //编辑用户信息的用户名名，即为教师号
                sysUser.setXm(dmJsb.getJsm());          //编辑用户信息的姓名
                sysRyDao.insertIntoSysUser(sysUser);      //往用户表（SYS_USER）中添加实验室主任
                sysRyDao.updateById(sysRy);             //更新实验室人员表
            }
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<SysRy> Delete(SysRy sysRy) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=sysRyDao.deleteById(sysRy);           //设置判断，查看是否更新成功
        if(i==0){
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            result.setStatus(0);
            result.setMsg("失败，这条数据不存在");
            return result;
        }else{
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            List<SysRy> sysRyList=sysRyDao.selectById(sysRy);
            if(sysRyList.size()==0){
                result.setStatus(0);
                result.setMsg("失败，这条数据不存在");
                return result;
            }else{
                SysRy list=sysRyDao.selectById(sysRy).get(0);
                if(list.getZw()!=null){         //判断该实验室人员是否是实验室主任
                    sysRyDao.updateSysSzBySysmAndXym(list);     //把该实验室主任从实验室表中删除
                }
                sysRyDao.deleteSysUserByJsm(list);          //删除用户表中（SYS_USER）该成员的信息
                sysRyDao.deleteById(sysRy);
                result.setMsg("成功");
                result.setStatus(200);
                return result;
            }
        }
    }

    @Override
    public Result<SysRy> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        SysRy sysRy=new SysRy();
        for(int i=0;i<arrayId.length;i++){
            sysRy.setId(arrayId[i]);
            List<SysRy> sysRyList=sysRyDao.selectById(sysRy);
            if(sysRyList.size()==0){
                result.setStatus(0);
                result.setMsg("失败，这些数据不存在");
                return result;
            }else{
                SysRy list=sysRyList.get(0);
                if(list.getZw()!=null){         //判断该实验室人员是否是实验室主任
                    sysRyDao.updateSysSzBySysmAndXym(list);     //把该实验室主任从实验室表中删除
                }
                sysRyDao.deleteSysUserByJsm(list);          //删除用户表中（SYS_USER）成员的信息
                sysRyDao.deleteById(sysRy);
            }
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }

    @Override
    public Map<String, Object> FindByDirector(SysRyDto params) {
        List<SysUser> sysUserList=sysRyDao.selectSysUserByUsername(userParams.getUsername());
        String Jsm=sysUserList.get(0).getXm();      //获取该登陆用户对应的教师名
        SysRy sysRy=new SysRy();
        sysRy.setJsm(Jsm);
        params.setSysm(sysRyDao.selectByJsm(sysRy).get(0).getSysm());       //设置该用户对应的实验室名
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.sysRy.pageQuery", SysRy.class, query);
        return LayuiUtils.getPage(query);
    }
//
//    @Override
//    public List<SyfsXx> SelectSyfsm() {
//        List<SysUser> sysUserList=sysRyDao.selectSysUserByUsername(userParams.getUsername());
//        String Jsm=sysUserList.get(0).getXm();      //获取该登陆用户对应的教师名
//        System.out.println(Jsm);
//        SysRy sysRy=new SysRy();
//        sysRy.setJsm(Jsm);
//        return sysRyDao.selectSyfsXxBySysm(sysRyDao.selectByJsm(sysRy).get(0).getSysm());       //获取该实验室对应的实验分室的信息
//    }
//
//    @Override
//    public Result<SysRy> CreateByDirector(SysRy sysRy) {
//        if(sysRy.getId()!=null){
//            result.setStatus(0);
//            result.setMsg("不需要添加用户ID");
//            return result;
//        }else if(sysRyDao.selectDmJsbByJsm(sysRy).size()==0){
//            result.setStatus(0);
//            result.setMsg("该教师不存在");
//            return result;
//        }else if(sysRyDao.selectByJsm(sysRy).size()!=0){
//            result.setStatus(0);
//            result.setMsg("该学院下已存在该教师");
//            return result;
//        }else{
//            sysRyDao.insert(sysRy);
//            SysUser sysUser=new SysUser();
//            sysUser.setSysm(sysRy.getSysm());       //编辑用户信息的实验室名
//            sysUser.setXym(sysRy.getXym());         //编辑用户信息的学院名
//            DmJsb dmJsb=sysRyDao.selectDmJsbByJsm(sysRy).get(0);        //通过教师名查询到该教师的详细信息
//            sysUser.setJsh(dmJsb.getJsh());         //编辑用户信息的教师号
//            sysUser.setUsername(dmJsb.getJsh());    //编辑用户信息的用户名名，即为教师号
//            sysUser.setXm(dmJsb.getJsm());          //编辑用户信息的姓名
//            sysRyDao.insertIntoSysUser(sysUser);      //往用户表（SYS_USER）中添加该用户
//            result.setStatus(200);
//            result.setMsg("成功");
//            return result;
//        }
//    }
//
//    @Override
//    public Result<SysRy> DeleteByDirector(SysRy sysRy) {
//        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
//        int i=sysRyDao.deleteById(sysRy);           //设置判断，查看是否更新成功
//        if(i==0){
//            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
//            result.setStatus(0);
//            result.setMsg("失败，这条数据不存在");
//            return result;
//        }else{
//            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
//            List<SysRy> sysRyList=sysRyDao.selectById(sysRy);
//            if(sysRyList.size()==0){
//                result.setStatus(0);
//                result.setMsg("失败，这条数据不存在");
//                return result;
//            }else{
//                SysRy list=sysRyDao.selectById(sysRy).get(0);
//                if(list.getZw()!=null){
//                    result.setStatus(0);
//                    result.setMsg("不能删除实验室主任");
//                    return result;
//                }else{
//                    sysRyDao.deleteSysUserByJsm(list);          //删除用户表中（SYS_USER）该成员的信息
//                    sysRyDao.deleteById(sysRy);
//                    result.setStatus(200);
//                    result.setMsg("成功");
//                    return result;
//                }
//            }
//        }
//    }
//
//    @Override
//    public Result<SysRy> DeleteBatchByDirector(DeleteBatchParam deleteBatchParam) {
//        int [] arrayId=deleteBatchParam.getArrayId();
//        SysRy sysRy=new SysRy();
//        for(int i=0;i<arrayId.length;i++){
//            sysRy.setId(arrayId[i]);
//            List<SysRy> sysRyList=sysRyDao.selectById(sysRy);
//            if(sysRyList.size()==0){
//                result.setStatus(0);
//                result.setMsg("失败，这些数据不存在");
//                return result;
//            }else{
//                SysRy list=sysRyDao.selectById(sysRy).get(0);
//                if(list.getZw()!=null){
//                    result.setStatus(0);
//                    result.setMsg("不能删除实验室主任");
//                    return result;
//                }else{
//                    sysRyDao.deleteSysUserByJsm(list);          //删除用户表中（SYS_USER）该成员的信息
//                    sysRyDao.deleteById(sysRy);
//                }
//            }
//        }
//        result.setMsg("成功");
//        result.setStatus(200);
//        return result;
//    }
}
