package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysRole;
import com.mds.my.platform.lostandfound.project.system.domain.vo.RoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    /**
     *  获取角色列表
     * @param params
     * @return
     */
    List<RoleVO> findAll(Map<String, Object> params);
}