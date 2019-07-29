package com.bm.jw.web.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.TtkDto;
import com.bm.jw.service.sj.sy.TtkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TtkController {
    @Autowired
    TtkService ttkService;
}
