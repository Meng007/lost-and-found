package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.exception.BaseException;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.vo.RoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysRoleMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import com.mds.my.platform.lostandfound.project.system.service.SysRoleService;
/**
 * @author 13557
 */
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

    @Override
    public Result saveRole(SysRole role) {
        checkField(role);
        LambdaQueryWrapper<SysRole> roleName = new LambdaQueryWrapper<>();
        roleName.eq(SysRole::getRoleName,role.getRoleName());
        SysRole sysRole = sysRoleMapper.selectOne(roleName);
        if (!Objects.isNull(sysRole)){
            return Result.fail("角色名称已存在！");
        }
        LambdaQueryWrapper<SysRole> roleKey = new LambdaQueryWrapper<>();
        roleKey.eq(SysRole::getRoleKey,role.getRoleKey());
        SysRole sysRole1 = sysRoleMapper.selectOne(roleKey);
        if (!Objects.isNull(sysRole1)){
            return Result.fail("角色key已存在！");
        }
        sysRole.setIsDelete(0);
        int i = sysRoleMapper.insert(role);
        if (i>0){
            return Result.success("添加成功！");
        }
        return Result.fail("添加失败！");
    }

    @Override
    public PageResult findAll(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        List<RoleVO> list = sysRoleMapper.findAll(params);
        if (!Objects.isNull(list) && !list.isEmpty()){
            list.forEach(val ->{
                if (new Integer(1).equals(val.getStatus())){
                    val.setStatusName("禁用");
                }else if (new Integer(0).equals(val.getStatus())){
                    val.setStatusName("启用");
                }else {
                    val.setStatusName("其他");
                }
            });
        }
        PageInfo<RoleVO> info = new PageInfo<>(list);
        return PageResult.<RoleVO>builder().msg("获取角色列表成功！").total(info.getTotal()).data(info.getList()).code(200).build();
    }

    private void checkField(SysRole role) {
        if (Objects.isNull(role)){
            throw new BaseException("");
        }
        if (StringUtils.isEmpty(role.getRoleName())){
            throw new BaseException("角色名称不能为空！");
        }
        if (StringUtils.isEmpty(role.getRoleKey())){
            throw new BaseException("角色key不能为空！");
        }
    }
}
