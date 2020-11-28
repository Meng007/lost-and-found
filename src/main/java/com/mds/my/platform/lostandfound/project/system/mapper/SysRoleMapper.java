package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 13557
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 获取用户角色
     * @param id id
     * @return
     */
    List<SysRole> selectRolePermissionByUserId(Integer id);
}