package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.vo.RouterVo;
import com.mds.my.platform.lostandfound.project.system.domain.vo.SysMenuVO;

import java.util.Collection;
import java.util.List;

public interface SysMenuService extends IService<SysMenu>{

    /**
     *  通过用户id查找权限
     * @param id
     * @return
     */
    Collection<? extends String> selectMenuPermsByUserId(Integer id);

    /**
     *  通过用户id查找树形菜单
     * @param user
     * @return
     */
    List<SysMenuVO> selectMenuTreeByUserId(SysUser user);

    /**
     * 根据菜单构建路由
     * @param menus
     * @return
     */
    List<RouterVo> buildMenus(List<SysMenuVO> menus);

    /**
     * 保存菜单
     * @param sysMenu
     * @return
     */
    Result saveMenu(SysMenu sysMenu);
}
