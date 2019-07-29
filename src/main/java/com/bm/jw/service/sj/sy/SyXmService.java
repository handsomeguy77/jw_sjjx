package com.bm.jw.service.sj.sy;

import com.bm.jw.entity.sj.sy.Dto.SyXmDto;
import com.bm.jw.entity.sj.sy.SyXm;
import com.bm.jw.utils.DeleteBatchParam;
import com.bm.jw.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SyXmService {
    //翻页查询
    Map<String, Object> Find(SyXmDto params);

    //添加
    Result<SyXm> Create(SyXm syXm);

    //更新
    Result<SyXm> Update(SyXm syXm);

    //删除
    Result<SyXm> Delete(SyXm syXm);

    //批量删除
    Result<SyXm> DeleteBatch(DeleteBatchParam deleteBatchParam);

    //导入Excel
    Result<SyXm> ImportExcel(MultipartFile file);

    //翻页查询--实验室管理员
    Map<String, Object> FindByDirector(SyXmDto params);
}
