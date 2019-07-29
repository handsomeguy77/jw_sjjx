package com.bm.jw.utils;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 这是用于返回数据格式为{status:**,msg:**,count:**,data:[{},{},{}...]}的工具类
 *
 * */
@Component
public class LayuiUtils {

    public static Map<String, Object> getPage(PageQuery query) {
        return formatResponse(query.getList(), query.getTotalRow());
    }

    public static <T> Map<String, Object> getPage(List<T> list, Long totalRow) {
        return formatResponse(list, totalRow);
    }

    private static Map<String, Object> formatResponse(List<?> list, Long totalRow) {
        return new HashMap<String, Object>() {{
            put("status", 200);
            put("data", list);
            put("count", totalRow);
            put("message","");
        }};
    }

}