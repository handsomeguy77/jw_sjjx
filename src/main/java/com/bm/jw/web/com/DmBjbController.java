package com.bm.jw.web.com;

import com.bm.jw.entity.com.DmBjb;
import com.bm.jw.entity.com.Dto.DmBjbDto;
import com.bm.jw.service.com.DmBjbService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DmBjbController {
    @Autowired
    DmBjbService dmBjbService;
    @GetMapping("/q/jcsu/bj/get")
    public Map<String,Object> Find(DmBjbDto params){
        return dmBjbService.Find(params);
    }
    @PostMapping("/m/jcsu/bj/add")
    public Result<DmBjb> Create(@RequestBody DmBjb dmBjb){
        return dmBjbService.Create(dmBjb);
    }
    @PutMapping("/m/jcsu/bj/update")
    public Result<DmBjb> Update(@RequestBody DmBjb dmBjb){
        return dmBjbService.Update(dmBjb);
    }
    @PostMapping("/m/jcsu/bj/del")
    public Result<DmBjb> Delete(@RequestBody DmBjb dmBjb){
        return dmBjbService.Delete(dmBjb);
    }
    @PostMapping("/m/jcsu/bj/dels")
    public Result<DmBjb> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return dmBjbService.DeleteBatch(deleteBatchParam);
    }
}
