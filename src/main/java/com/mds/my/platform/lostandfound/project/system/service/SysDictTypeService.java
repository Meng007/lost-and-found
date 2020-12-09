package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysDictTypeService extends IService<SysDictType>{


    Result saveDictType(SysDictType sysDictType);

    PageResult findAll(Map params);

    Result updateDictType(SysDictType sysDictType);

    Result removeDictType(Integer id);
}
