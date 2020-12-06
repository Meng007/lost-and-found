package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.vo.RouterVo;
import com.mds.my.platform.lostandfound.project.system.domain.vo.SysMenuVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    /**
     *  修改菜单
     * @param sysMenu
     * @return
     */
    Result updateMenu(SysMenu sysMenu);

    /**
     *  删除菜单
     * @param id
     * @return
     */
    Result removeMenu(Integer id);

    /**
     *  获取菜单详情
     * @param id
     * @return
     */
    Result getMenuInfo(Integer id);

    /**
     *  获取菜单列表
     * @param params
     * @return
     */
    Result findAll(Map<String, Object> params);
}
