package com.bm.jw.utils;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Format {
    public static List StringToArray(String string){
        string.replace("[","");//删除左中括号
        string.replace("]","");//删除右中括号
        String [] arr=string.split(",");
        List list = java.util.Arrays.asList(arr);//字符数组转list
        return list;
    }
}
