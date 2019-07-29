package com.bm.jw.web.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SyXmDto;
import com.bm.jw.entity.sj.sy.SyXm;
import com.bm.jw.service.sj.sy.SyXmService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class SyXmComtroller {
    @Autowired
    SyXmService syXmService;
    @GetMapping("/q/syssj/syxm/get")
    public Map<String,Object> Find(SyXmDto params){
        return syXmService.Find(params);
    }
    @PostMapping("/m/syssj/syxm/add")
    public Result<SyXm> Create(@RequestBody SyXm syXm){
        return syXmService.Create(syXm);
    }
    @PutMapping("/m/syssj/syxm/update")
    public Result<SyXm> Update(@RequestBody SyXm syXm){
        return syXmService.Update(syXm);
    }
    @PostMapping("/m/syssj/syxm/del")
    public Result<SyXm> Delete(@RequestBody SyXm syXm){
        return syXmService.Delete(syXm);
    }
    @PostMapping("/m/syssj/syxm/dels")
    public Result<SyXm> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return syXmService.DeleteBatch(deleteBatchParam);
    }
    @PostMapping("/m/syssj/syxm/import")
    public Result<SyXm> Import(@RequestParam(value="file") MultipartFile file){
        return syXmService.ImportExcel(file);
    }
    @GetMapping("/q/sysxm/get")
    public Map<String,Object> FindByDirector(SyXmDto params){
        return syXmService.FindByDirector(params);
    }
}
