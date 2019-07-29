package com.bm.jw.web.com;

import com.bm.jw.entity.com.DmXy;
import com.bm.jw.entity.com.Dto.DmXyDto;
import com.bm.jw.service.com.DmXyService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DmXyController {
    @Autowired
    DmXyService dmXyService;
    @GetMapping("/q/jcsu/yx/get")
    public Map<String,Object> Find(DmXyDto params){
        return dmXyService.Find(params);
    }
    @GetMapping("/q/jcsu/yx/getAll")
    public List<DmXy> selectAll(){
        return dmXyService.selectAll();
    }
    @PostMapping("/m/jcsu/yx/add")
    public Result<DmXy> Create(@RequestBody DmXy dmXy){
        return dmXyService.Create(dmXy);
    }
    @PutMapping("/m/jcsu/yx/update")
    public Result<DmXy> Update(@RequestBody DmXy dmXy){
        return dmXyService.Update(dmXy);
    }
    @PostMapping("/m/jcsu/yx/del")
    public Result<DmXy> Delete(@RequestBody DmXy dmXy){
        return dmXyService.Delete(dmXy);
    }
}
