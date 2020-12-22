package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysDictDataService extends IService<SysDictData>{


    Result saveDictData(SysDictData sysDictData);

    PageResult findAll(Map<String, Object> params);

    Result removeDictData(Integer id);

    Result updateDictData(SysDictData sysDictData);

    Result getDictDataByType(Integer type);

    Result getDictDataBytype(String dictType);
}
