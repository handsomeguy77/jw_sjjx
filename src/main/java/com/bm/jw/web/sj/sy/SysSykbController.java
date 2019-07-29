package com.bm.jw.web.sj.sy;

import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.sj.sy.Dto.SysSykbDto;
import com.bm.jw.entity.sj.sy.SyfsXx;
import com.bm.jw.entity.sj.sy.SysSykb;
import com.bm.jw.service.sj.sy.SysSykbService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SysSykbController {
    @Autowired
    private SysSykbService sysSykbService;
    @GetMapping("/q/syssj/sykb/get")
    public Map<String,Object> Find(SysSykbDto params){
        return sysSykbService.Find(params);
    }
    @PostMapping("/q/syssj/sykb/add")
    public Result<SysSykb> Create(@RequestBody SysSykb sysSykb){
        return sysSykbService.Create(sysSykb);
    }
    @PutMapping("/q/syssj/sykb/update")
    public Result<SysSykb> Update(@RequestBody SysSykb sysSykb){
        return sysSykbService.Update(sysSykb);
    }
    @PostMapping("/q/syssj/sykb/del")
    public Result<SysSykb> Delete(@RequestBody SysSykb sysSykb){
        return sysSykbService.Delete(sysSykb);
    }
    @PostMapping("/q/syssj/sykb/dels")
    public Result<SysSykb> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return sysSykbService.DeleteBatch(deleteBatchParam);
    }
    @GetMapping("/q/syssj/ttk/get")
    public Map<String,Object> SelectKcm(SysSykbDto params){
        return sysSykbService.SelectKcm(params);
    }
    @GetMapping("/q/sykb/ttk/getZc")
    public Map<String,Object> SelectByKcm(SysSykbDto params){
        return sysSykbService.SelectByKcm(params);
    }
    @GetMapping("/q/syssj/sykb/getKch")
    public List<DmKcb> SelectKch(@RequestBody SysSykb sysSykb){
        return sysSykbService.SelectKch(sysSykb);
    }
    @GetMapping("/q/syssj/sykb/getJsh")
    public List<DmJsb> SelectJsh(@RequestBody SysSykb sysSykb){
        return sysSykbService.SelectJsh(sysSykb);
    }
    @GetMapping("/q/sykb/get")
    public Map<String,Object> FindByDirector(SysSykbDto params){
        return sysSykbService.FindByDirector(params);
    }
}
