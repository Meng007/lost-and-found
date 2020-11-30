package com.mds.my.platform.lostandfound.project.system.service;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
