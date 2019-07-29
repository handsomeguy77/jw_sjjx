package com.bm.jw.web.com;

import com.bm.jw.entity.com.DmJsb;
import com.bm.jw.entity.com.Dto.DmJsbDto;
import com.bm.jw.service.com.DmJsbService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DmJsbController {
    @Autowired
    DmJsbService dmJsbService;
    @GetMapping("/q/jcsu/js/get")
    public Map<String,Object> Find(DmJsbDto params){
        return dmJsbService.Find(params);
    }
    @PostMapping("/q/jcsu/js/getJsm")
    public List<DmJsb> SelectJsmByXym(@RequestBody DmJsb dmJsb){
        return dmJsbService.SelectJsmByXym(dmJsb);
    }
    @PostMapping("/m/jcsu/js/add")
    public Result<DmJsb> Create(@RequestBody DmJsb dmJsb){
        return dmJsbService.Create(dmJsb);
    }
    @PutMapping("/m/jcsu/js/update")
    public Result<DmJsb> Update(@RequestBody DmJsb dmJsb){
        return dmJsbService.Update(dmJsb);
    }
    @PostMapping("/m/jcsu/js/del")
    public Result<DmJsb> Delete(@RequestBody DmJsb dmJsb){
        return dmJsbService.Delete(dmJsb);
    }@PostMapping("/m/jcsu/js/dels")
    public Result<DmJsb> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return dmJsbService.DeleteBatch(deleteBatchParam);
    }
}
