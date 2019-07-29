package com.bm.jw.service.sj.sy.Impl;

import com.bm.jw.dao.SysRyDao;
import com.bm.jw.dao.SysSykbDao;
import com.bm.jw.entity.SysUser;
import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.sj.sy.Dto.SysSykbDto;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.entity.sj.sy.SysSykb;
import com.bm.jw.service.sj.sy.SysSykbService;
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
public class SysSykbServiceImpl implements SysSykbService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private SysSykbDao sysSykbDao;
    @Autowired private UserParams userParams;
    @Autowired
    private SysRyDao sysRyDao;
    @Autowired Result<SysSykb> result;
    @Override
    public Map<String, Object> Find(SysSykbDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.sysSykb.pageQuery", SysSykb.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Result<SysSykb> Create(SysSykb sysSykb) {
        if(sysSykb.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
            return result;
        }else{
            sysSykbDao.insert(sysSykb);
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<SysSykb> Update(SysSykb sysSykb) {
        if(sysSykb.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
            return result;
        }else{
            sysSykbDao.updateById(sysSykb);
            result.setStatus(200);
            result.setMsg("成功");
            return result;
        }
    }

    @Override
    public Result<SysSykb> Delete(SysSykb sysSykb) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=sysSykbDao.deleteById(sysSykb);           //设置判断，查看是否更新成功
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
    public Result<SysSykb> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        for(int i=0;i<arrayId.length;i++){
            sysSykbDao.deleteBatchById(arrayId[i]);
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }

    @Override
    public List<DmKcb> SelectKch(SysSykb sysSykb) {
        return sysSykbDao.selectDmKcbByKcm(sysSykb);
    }

    @Override
    public List<DmJsb> SelectJsh(SysSykb sysSykb) {
        return sysSykbDao.selectDmJsbByZdjs(sysSykb);
    }

    @Override
    public Map<String, Object> FindByDirector(SysSykbDto params) {
        List<SysUser> sysUserList=sysRyDao.selectSysUserByUsername(userParams.getUsername());
        String Jsm=sysUserList.get(0).getXm();      //获取该登陆用户对应的教师名
        SysRy sysRy=new SysRy();
        sysRy.setJsm(Jsm);
        params.setSysm(sysRyDao.selectByJsm(sysRy).get(0).getSysm());       //设置该用户对应的实验室名
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.sysSykb.pageQuery", SysSykb.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Map<String, Object> SelectKcm(SysSykbDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.sysSykb.selectKcm", SysSykb.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Map<String, Object> SelectByKcm(SysSykbDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.sysSykb.selectByKcm", SysSykb.class, query);
        return LayuiUtils.getPage(query);
    }
}
