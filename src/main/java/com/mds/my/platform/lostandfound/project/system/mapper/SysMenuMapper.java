package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.mds.my.platform.lostandfound.project.system.domain.vo.SysMenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 菜单 mapper
 * @author 13557
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectMenuMapperPermsByUserId(Integer id);

    List<SysMenuVO> selectMenuTreeByUserId(Integer id);
}