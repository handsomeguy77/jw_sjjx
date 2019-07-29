package com.bm.jw.web.com;

import com.bm.jw.entity.com.Dto.XjXjbDto;
import com.bm.jw.entity.com.XjXjb;
import com.bm.jw.service.com.XjXjbService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class XjXjbController {
    @Autowired
    XjXjbService xjXjbService;
    @GetMapping("/q/jcsu/xs/get")
    public Map<String,Object> Find(XjXjbDto params){
        return xjXjbService.Find(params);
    }
    @PostMapping("/m/jcsu/xs/add")
    public Result<XjXjb> Create(@RequestBody XjXjb xjXjb){
        return xjXjbService.Create(xjXjb);
    }
    @PutMapping("/m/jcsu/xs/update")
    public Result<XjXjb> Update(@RequestBody XjXjb xjXjb){
        return xjXjbService.Update(xjXjb);
    }
    @PostMapping("/m/jcsu/xs/del")
    public Result<XjXjb> Delete(@RequestBody XjXjb xjXjb){
        return xjXjbService.Delete(xjXjb);
    }
    @PostMapping("/m/jcsu/xs/dels")
    public Result<XjXjb> DeleteBatch(@RequestBody DeleteBatchParam deleteBatchParam){
        return xjXjbService.DeleteBatch(deleteBatchParam);
    }
}
