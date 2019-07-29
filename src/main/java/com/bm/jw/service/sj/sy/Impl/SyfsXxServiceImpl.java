package com.bm.jw.service.sj.sy.Impl;

import com.bm.jw.dao.DmXyDao;
import com.bm.jw.dao.SyfsXxDao;
import com.bm.jw.dao.SysRyDao;
import com.bm.jw.entity.SysUser;
import com.bm.jw.entity.sj.sy.Dto.SyfsXxDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.service.sj.sy.SyfsXxService;
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
public class SyfsXxServiceImpl implements SyfsXxService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private SyfsXxDao syfsXxDao;
    @Autowired private UserParams userParams;
    @Autowired
    private SysRyDao sysRyDao;
    @Autowired
    private Result<SyfsXx> result;
    @Override
    public Map<String, Object> Find(SyfsXxDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.syfsXx.pageQuery", SyfsXx.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public List<SyfsXx> selectAll() {
        return syfsXxDao.selectAll();
    }

    @Override
    public List<SyfsXx> selectSyfsmBySysm(SyfsXx syfsXx) {
        return syfsXxDao.selectBySysm(syfsXx);
    }

    @Override
    public Result<SyfsXx> Create(SyfsXx syfsXx) {
        if(syfsXx.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
            return result;
        }else if(syfsXxDao.selectBySysmAndSyfsm(syfsXx).size()!=0){
            result.setStatus(0);
            result.setMsg("该实验室已存在该实验室分室");
            return result;
        }else{
            syfsXxDao.insert(syfsXx);
//            if(syfsXx.getFzr()!=null){
//                SysRy sysRy=new SysRy();
//                sysRy.setJsm(syfsXx.getFjh());
//                String Glfsmc=null;
//                List<SyfsXx> syfsXxLists=syfsXxDao.selectByFzr(syfsXx);
//                for(int i=0;i<syfsXxLists.size();i++){
//                    Glfsmc+=syfsXxLists.get(i).getSyfsm()+",";      //一个负责人可能管理多个实验室分室，所以用逗号进行拼接
//                }
//                Glfsmc.substring(0,Glfsmc.length()-1);
//                sysRy.setGlfsmc(Glfsmc);
//                syfsXxDao.updateSysRyByGlfsmc(sysRy);       //若负责人不为空，给该教师对应实验室人员表中的管理分室名称加上对应的实验分室名称
//            }
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<SyfsXx> Update(SyfsXx syfsXx) {
        if(syfsXx.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
            return result;
        }else{
            Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();       //设置事务回滚点
            syfsXxDao.updateById(syfsXx);
            if(syfsXxDao.selectBySysmAndSyfsm(syfsXx).size()==2){     //判断更改后是否出现不能重复的数据
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);         //事务回滚到回滚点
                result.setStatus(0);
                result.setMsg("该实验室已存在该实验室分室");
                return result;
            }else{
                result.setStatus(200);
                result.setMsg("成功");
                return result;
            }
        }
    }

    @Override
    public Result<SyfsXx> Delete(SyfsXx syfsXx) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=syfsXxDao.deleteById(syfsXx);           //设置判断，查看是否更新成功
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
    public Result<SyfsXx> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        for(int i=0;i<arrayId.length;i++){
            syfsXxDao.deleteBatchById(arrayId[i]);
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }

    @Override
    public Map<String, Object> FindByDirector(SyfsXxDto params) {
        List<SysUser> sysUserList=sysRyDao.selectSysUserByUsername(userParams.getUsername());
        String Jsm=sysUserList.get(0).getXm();      //获取该登陆用户对应的教师名
        SysRy sysRy=new SysRy();
        sysRy.setJsm(Jsm);
        params.setSysm(sysRyDao.selectByJsm(sysRy).get(0).getSysm());       //设置该用户对应的实验室名
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.syfsXx.pageQuery", SyfsXx.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Result<SyfsXx> AddFzr(SyfsXx syfsXx) {
        syfsXxDao.updateById(syfsXx);
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }

}
