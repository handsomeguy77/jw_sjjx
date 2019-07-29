package com.bm.jw.service.sj.sy.Impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.bm.jw.dao.SyXmDao;
import com.bm.jw.dao.SysRyDao;
import com.bm.jw.entity.SysUser;
import com.bm.jw.entity.sj.sy.Dto.SyXmDto;
import com.bm.jw.entity.sj.sy.Excel.SyXmExcel;
import com.bm.jw.entity.sj.sy.SyXm;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.service.sj.sy.SyXmService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.LayuiUtils;
import com.bm.jw.utils.Result;
import com.bm.jw.utils.UserParams;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SyXmServiceImpl implements SyXmService {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private SyXmDao syXmDao;
    @Autowired private UserParams userParams;
    @Autowired
    private SysRyDao sysRyDao;
    @Autowired private Result<SyXm> result;
    @Override
    public Map<String, Object> Find(SyXmDto params) {
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.syXm.pageQuery", SyXm.class, query);
        return LayuiUtils.getPage(query);
    }

    @Override
    public Result<SyXm> Create(SyXm syXm) {
        if(syXm.getId()!=null){
            result.setStatus(0);
            result.setMsg("不需要添加用户ID");
        }else if(syXmDao.selectDmKcbByKch(syXm).size()==0){
            result.setStatus(0);
            result.setMsg("该课程不存在");
        }else{
            syXmDao.insert(syXm);
            result.setStatus(200);
            result.setMsg("成功");
        }
        return result;
    }

    @Override
    public Result<SyXm> Update(SyXm syXm) {
        if(syXm.getId()==null){
            result.setStatus(0);
            result.setMsg("用户ID不能为空");
        }else if(syXmDao.selectDmKcbByKch(syXm).size()==0){
                result.setStatus(0);
                result.setMsg("该课程不存在");
        }else{
            syXmDao.updateById(syXm);
            result.setStatus(200);
            result.setMsg("成功");
            }
        return result;
    }

    @Override
    public Result<SyXm> Delete(SyXm syXm) {
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        int i=syXmDao.deleteById(syXm);           //设置判断，查看是否更新成功
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
    public Result<SyXm> DeleteBatch(DeleteBatchParam deleteBatchParam) {
        int [] arrayId=deleteBatchParam.getArrayId();
        for(int i=0;i<arrayId.length;i++){
            syXmDao.deleteBatchById(arrayId[i]);
        }
        result.setMsg("成功");
        result.setStatus(200);
        return result;
    }


    @Override
    public Result<SyXm> ImportExcel(MultipartFile file) {
        try{
            ImportParams params = new ImportParams();
            params.setNeedVerify(true);     //设置开启校验上传
            ExcelImportResult<SyXmExcel> importResult= ExcelImportUtil.importExcelMore(file.getInputStream(),SyXmExcel.class,params);
            if(importResult.isVerfiyFail()){        //判断校验是否存在错误
                File dir=new File("D:/excel");
                if(!dir.exists()){
                    dir.mkdir();    //在D盘下面创建excel文件夹
                }
                FileOutputStream fos = new FileOutputStream("D:/excel/failMsg.xlsx");      //创建failMsg.xlsx文件
                importResult.getWorkbook().write(fos);      //写文件
                fos.close();
                result.setStatus(0);
                result.setMsg("Excel文件上传的格式错误，具体请查看本地D：/excel/baseModetest.xlsx");
            }else{
                List<SyXmExcel> list=importResult.getList();        //获取Excel的数据转化为bean之后的数据
                List<SyXm> target=new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    SyXm syXm=new SyXm();
                    BeanUtils.copyProperties(list.get(i),syXm);     //copy bean to bean
                    if(syXmDao.selectDmKcbByKch(syXm).size()!=0){       //查找课程表，判断该课程号是否已经存在
                        target.add(syXm);
                    }
                }
                if(target.size()!=list.size()){     //判断是否拥有不能导入的数据
                    result.setStatus(0);
                    result.setMsg("导入的Excel中有些数据的课程号不存在，因此导入失败");
                    result.setData(target);
                }else{
                    result.setStatus(200);
                    result.setMsg("成功");
                    syXmDao.insertBatch(target);        //批量插入
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Object> FindByDirector(SyXmDto params) {
        List<SysUser> sysUserList=sysRyDao.selectSysUserByUsername(userParams.getUsername());
        String Jsm=sysUserList.get(0).getXm();      //获取该登陆用户对应的教师名
        SysRy sysRy=new SysRy();
        sysRy.setJsm(Jsm);
        params.setSysm(sysRyDao.selectByJsm(sysRy).get(0).getSysm());       //设置该用户对应的实验室名
        PageQuery query = new PageQuery();
        query.setParas(params);
        query.setPageNumber(params.page);
        query.setPageSize(params.limit);
        sqlManager.pageQuery("sj.sy.syXm.pageQuery", SyXm.class, query);
        return LayuiUtils.getPage(query);
    }
}
