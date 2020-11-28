package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysDictDataService extends IService<SysDictData>{


    Result saveDictData(SysDictData sysDictData);
}
