package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 13557
 */
@Mapper
public interface SysUserInfoService extends IService<SysUserInfo>{
    /**
     * 用户拓展信息
     * @return
     */
    Result getInfo();
}
