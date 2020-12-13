package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.mds.my.platform.lostandfound.project.system.domain.vo.MenuVO;
import com.mds.my.platform.lostandfound.project.system.domain.vo.SysMenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/** 菜单 mapper
 * @author 13557
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     *  获取菜单权限
     * @param id
     * @return
     */
    List<String> selectMenuMapperPermsByUserId(Integer id);

    /**
     * 获取树形菜单
     * @param id
     * @return
     */
    List<SysMenuVO> selectMenuTreeByUserId(Integer id);

    /**
     * 后台获取菜单列表
     * @param params
     * @return
     */
    List<MenuVO> findAll(Map<String, Object> params);
}