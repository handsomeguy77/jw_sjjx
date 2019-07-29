package com.bm.jw.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 规范返回数据的格式
 *
 * */
@Component
@Data
public class Result<T> {
    private String msg;
    private Integer status;
    private List<T> data;
}
