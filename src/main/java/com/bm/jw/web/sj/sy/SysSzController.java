package com.bm.jw.web.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SysSzDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysSz;
import com.bm.jw.service.sj.sy.SysSzService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SysSzController {
    @Autowired
    SysSzService sysSzService;
    @GetMapping("/q/syssj/sys/get")
    public Map<String,Object> Find(SysSzDto params){
        return sysSzService.Find(params);
    }
    @GetMapping("/q/syssj/sys/getAll")
    public List<SysSz> selectAll(){
        return sysSzService.selectAll();
    }
    @PostMapping("/q/syssj/sys/getByXym")
    public List<SysSz> selectSysmByXym(@RequestBody SysSz sysSz){
        return sysSzService.selectSysmByXym(sysSz);
    }
    @PostMapping("/m/syssj/sys/add")
    public Result<SysSz> Create(@RequestBody SysSz sysSz){
        return sysSzService.Create(sysSz);
    }
    @PutMapping("/m/syssj/sys/update")
    public Result<SysSz> Update(@RequestBody SysSz sysSz){
        return sysSzService.Update(sysSz);
    }
    @PostMapping("/m/syssj/sys/del")
    public Result<SysSz> Delete(@RequestBody SysSz sysSz){
        return sysSzService.Delete(sysSz);
    }
    @PostMapping("/m/syssj/sys/dels")
    public Result<SysSz> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return sysSzService.DeleteBatch(deleteBatchParam);
    }
}
