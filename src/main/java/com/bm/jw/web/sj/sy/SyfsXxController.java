package com.bm.jw.web.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SyfsXxDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.service.sj.sy.SyfsXxService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SyfsXxController {
    @Autowired
    SyfsXxService syfsXxService;
    @GetMapping("/q/syssj/sysfs/get")
    public Map<String,Object> Find(SyfsXxDto params){
        return syfsXxService.Find(params);
    }
    @GetMapping("/q/syssj/sysfs/getAll")
    public List<SyfsXx> SelectAll(){
        return syfsXxService.selectAll();
    }
    @PostMapping("/q/syssj/sysfs/getBySysm")
    public List<SyfsXx> SelectSyfsmBySysm(@RequestBody SyfsXx syfsXx){
        return syfsXxService.selectSyfsmBySysm(syfsXx);
    }
    @PostMapping("/m/syssj/sysfs/add")
    public Result<SyfsXx> Create(@RequestBody SyfsXx syfsXx){
        return syfsXxService.Create(syfsXx);
    }
    @PutMapping("/m/syssj/sysfs/update")
    public Result<SyfsXx> Update(@RequestBody SyfsXx syfsXx){
        return syfsXxService.Update(syfsXx);
    }
    @PostMapping("/m/syssj/sysfs/del")
    public Result<SyfsXx> Delete(@RequestBody SyfsXx syfsXx){
        return syfsXxService.Delete(syfsXx);
    }
    @PostMapping("/m/syssj/sysfs/dels")
    public Result<SyfsXx> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return syfsXxService.DeleteBatch(deleteBatchParam);
    }
    @GetMapping("/q/syfs/get")
    public Map<String,Object> FindByDirector(SyfsXxDto params){
        return syfsXxService.FindByDirector(params);
    }
    @PostMapping("/q/syfs/addFzr")
    public Result<SyfsXx> AddFzr(@RequestBody SyfsXx syfsXx){
        return syfsXxService.AddFzr(syfsXx);
    }
}
