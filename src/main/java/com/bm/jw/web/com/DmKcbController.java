package com.bm.jw.web.com;

import com.bm.jw.entity.com.DmKcb;
import com.bm.jw.entity.com.Dto.DmKcbDto;
import com.bm.jw.service.com.DmKcbService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DmKcbController {
    @Autowired
    DmKcbService dmKcbService;
    @GetMapping("/q/jcsu/kc/get")
    public Map<String,Object> Find(DmKcbDto params){
        return dmKcbService.Find(params);
    }
    @GetMapping("/q/jcsu/kc/getAll")
    public List<DmKcb> SelectAll(){
        return dmKcbService.SelectKcm();
    }
    @PostMapping("/m/jcsu/kc/add")
    public Result<DmKcb> Create(@RequestBody DmKcb dmKcb){
        return dmKcbService.Create(dmKcb);
    }
    @PutMapping("/m/jcsu/kc/update")
    public Result<DmKcb> Update(@RequestBody DmKcb dmKcb){
        return dmKcbService.Update(dmKcb);
    }
    @PostMapping("/m/jcsu/kc/del")
    public Result<DmKcb> Delete(@RequestBody DmKcb dmKcb){
        return dmKcbService.Delete(dmKcb);
    }
    @PostMapping("/m/jcsu/kc/dels")
    public Result<DmKcb> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return dmKcbService.DeleteBatch(deleteBatchParam);
    }
}
