package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.Map;

public interface SysRoleService extends IService<SysRole>{

    /**
     *  获取用户角色
     * @param id
     * @return
     */
    Collection<? extends String> selectRolePermissionByUserId(Integer id);

    /**
     *  保存角色
     * @param role
     * @return
     */
    Result saveRole(SysRole role);

    /**
     * 查找角色
     * @param params
     * @return
     */
    PageResult findAll(Map<String, Object> params);
}