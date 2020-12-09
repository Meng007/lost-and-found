package com.mds.my.platform.lostandfound.project.system.service;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 1355
 */
public interface SysUserService extends IService<SysUser>{

    /**
     * 用户注册
     * @param sysUser
     * @return
     */
    Result regUser(SysUser sysUser);

    /**
     *  修改密码
     * @param sysUser
     * @return
     */
    Result updatePassword(SysUser sysUser);

    /**
     * 用户列表
     * @param params
     * @return
     */
    PageResult getUserList(Map<String, Object> params);

    /**
     *  删除用户
     * @param id
     * @return
     */
    Result removeUser(Integer id);

    /**
     * 获取登录用户
     * @param params
     * @return
     */
    PageResult getLoginUserList(Map<String, Object> params);
}
