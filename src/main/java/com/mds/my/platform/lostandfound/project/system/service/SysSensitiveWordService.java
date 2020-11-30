package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysSensitiveWord;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysSensitiveWordService extends IService<SysSensitiveWord>{


    Result saveWord(SysSensitiveWord sysSensitiveWord);

    Result updateSensitiveWord(SysSensitiveWord sysSensitiveWord);

    Result removeSensitiveWord(Integer id);

    Result echo(Integer id);
}
