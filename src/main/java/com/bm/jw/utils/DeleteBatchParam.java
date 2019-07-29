package com.bm.jw.utils;

import lombok.Data;

/**
 * 该类用于批量删除，用来接收传过来的数据
 * */
@Data
public class DeleteBatchParam {
    private int [] ArrayId;
}
