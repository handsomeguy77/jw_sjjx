package com.bm.jw.web.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SysRyDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysRy;
import com.bm.jw.service.sj.sy.SysRyService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SysRyController {
    @Autowired
    SysRyService sysRyService;
    @GetMapping("/q/syssj/sysry/get")
    public Map<String,Object> Find(SysRyDto params){
        return sysRyService.Find(params);
    }
    @PostMapping("/m/syssj/sysry/add")
    public Result<SysRy> Create(@RequestBody SysRy sysRy){
        return sysRyService.Create(sysRy);
    }
    @PutMapping("/m/syssj/sysry/update")
    public Result<SysRy> Update(@RequestBody SysRy sysRy){
        return sysRyService.Update(sysRy);
    }
    @PostMapping("/m/syssj/sysry/del")
    public Result<SysRy> Delete(@RequestBody SysRy sysRy){
        return sysRyService.Delete(sysRy);
    }
    @PostMapping("/m/syssj/sysry/dels")
    public Result<SysRy> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return sysRyService.DeleteBatch(deleteBatchParam);
    }
    @GetMapping("/q/cygl/get")
    public Map<String,Object> FindByDirector(SysRyDto params){
        return sysRyService.FindByDirector(params);
    }
//    @GetMapping("/q/cygl/syfs/get")
//    public List<SyfsXx> SelectSyfs(){
//        return sysRyService.SelectSyfsm();
//    }
//    @PostMapping("/m/cygl/add")
//    public Result<SysRy> CreateByDirector(@RequestBody SysRy sysRy){
//        return sysRyService.CreateByDirector(sysRy);
//    }
//    @PostMapping("/m/cygl/del")
//    public Result<SysRy> DeleteByDirector(@RequestBody SysRy sysRy){
//        return sysRyService.DeleteByDirector(sysRy);
//    }
//    @PostMapping("/m/cygl/dels")
//    public Result<SysRy> DeleteBatchByDirector(@RequestBody DeleteBatchParam deleteBatchParam){
//        return sysRyService.DeleteBatchByDirector(deleteBatchParam);
//    }
}

