package com.bm.jw.web.com;

import com.bm.jw.entity.com.DmXqb;
import com.bm.jw.entity.com.Dto.DmXqbDto;
import com.bm.jw.service.com.DmXqbService;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DmXqbController {
    @Autowired
    DmXqbService dmXqbService;
    @GetMapping("/q/jcsu/xqsj/get")
    public Map<String,Object> Find(DmXqbDto params){
        return dmXqbService.Find(params);
    }
    @PostMapping("/m/jcsu/xqsj/add")
    public Result<DmXqb> Create(@RequestBody DmXqb dmXqb){
        return dmXqbService.Create(dmXqb);
    }
    @PutMapping("/m/jcsu/xqsj/update")
    public Result<DmXqb> Update(@RequestBody DmXqb dmXqb){
        return dmXqbService.Update(dmXqb);
    }
    @PostMapping("/m/jcsu/xqsj/del")
    public Result<DmXqb> Delete(@RequestBody DmXqb dmXqb){
        return dmXqbService.Delete(dmXqb);
    }
}
