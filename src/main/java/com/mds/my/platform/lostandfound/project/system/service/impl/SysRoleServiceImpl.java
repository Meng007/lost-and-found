package com.mds.my.platform.lostandfound.project.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysRoleMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import com.mds.my.platform.lostandfound.project.system.service.SysRoleService;
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     *  获取用户角色
     * @param id
     * @return
     */
    @Override
    public Collection<? extends String> selectRolePermissionByUserId(Integer id) {
        List<SysRole> roles = sysRoleMapper.selectRolePermissionByUserId(id);
        Set<String> roleSet = new HashSet<>();
        for (SysRole perm : roles)
        {
            if (!Objects.isNull(perm))
            {
                roleSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return roleSet;
    }
}
