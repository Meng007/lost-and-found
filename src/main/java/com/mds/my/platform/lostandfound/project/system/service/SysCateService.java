package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 13557
 */
public interface SysCateService extends IService<SysCate> {

    /**
     * 保存系统分类
     *
     * @param sysCate
     * @return
     */
    Result saveCate(SysCate sysCate);

    /**
     * 获取分类列表
     *
     * @param params
     * @return
     */
    PageResult findAll(Map<String, Object> params);

    /**
     * 获取物品分类
     * @return pid
     */
    Result getGoodsCateList(Integer pid);
}

