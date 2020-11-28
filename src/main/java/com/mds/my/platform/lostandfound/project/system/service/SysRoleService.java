package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

public interface SysRoleService extends IService<SysRole>{


    Collection<? extends String> selectRolePermissionByUserId(Integer id);
}
