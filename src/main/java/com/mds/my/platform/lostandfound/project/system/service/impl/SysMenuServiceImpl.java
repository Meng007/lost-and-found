package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mds.my.platform.lostandfound.common.constant.Constants;
import com.mds.my.platform.lostandfound.common.enums.UserStatus;
import com.mds.my.platform.lostandfound.common.exception.BaseException;
import com.mds.my.platform.lostandfound.common.utils.SecurityUtils;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.vo.MenuVO;
import com.mds.my.platform.lostandfound.project.system.domain.vo.MetaVo;
import com.mds.my.platform.lostandfound.project.system.domain.vo.RouterVo;
import com.mds.my.platform.lostandfound.project.system.domain.vo.SysMenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysMenuMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import com.mds.my.platform.lostandfound.project.system.service.SysMenuService;

/**
 * @author 13557
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Collection<? extends String> selectMenuPermsByUserId(Integer id) {
        List<String> perms = sysMenuMapper.selectMenuMapperPermsByUserId(id);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 获取用户菜单
     *
     * @param user
     * @return
     */
    @Override
    public List<SysMenuVO> selectMenuTreeByUserId(SysUser user) {
        List<SysMenuVO> menus = null;
        menus = sysMenuMapper.selectMenuTreeByUserId(user.getId());
        return getChildPerms(menus, 0);
    }

    /**
     * 构建路由
     * @param menus
     * @return
     */
    @Override
    public List<RouterVo> buildMenus(List<SysMenuVO> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuVO menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<SysMenuVO> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && Constants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     *  保存菜单
     * @param sysMenu
     * @return
     */
    @Override
    public Result saveMenu(SysMenu sysMenu) {
        if (Objects.isNull(sysMenu)){
            throw new BaseException("提交菜单数据不能为空！");
        }
        if (StringUtils.isEmpty(sysMenu.getMenuName())){
            throw new BaseException("提交菜单名称不能为空！");
        }
        LambdaQueryWrapper<SysMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SysMenu::getMenuName,sysMenu.getMenuName());
        SysMenu one = sysMenuMapper.selectOne(lqw);
        if (!Objects.isNull(one)){
            return Result.fail("菜单名重复！");
        }
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        sysMenu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysMenu.setUserId(user.getId());
        int i = sysMenuMapper.insert(sysMenu);
        if (i>0){
            return Result.success("添加菜单成功");
        }
        return Result.fail("添加菜单失败");
    }

    /**
     *  修改菜单
     * @param sysMenu
     * @return
     */
    @Override
    public Result updateMenu(SysMenu sysMenu) {
        if (Objects.isNull(sysMenu)){
            return Result.fail("修改提交的数据不能为空！");
        }
        SysMenu one = sysMenuMapper.selectById(sysMenu.getId());
        if (Objects.isNull(one)){
            return Result.fail("修改的数据不存在！");
        }
        int i = sysMenuMapper.updateById(sysMenu);
        if (i>0){
            return Result.success("修改成功！");
        }
        return Result.fail("修改失败！");
    }

    /**
     *  删除菜单
     * @param id
     * @return
     */
    @Override
    public Result removeMenu(Integer id) {
        SysMenu one = sysMenuMapper.selectById(id);
        if (Objects.isNull(one)){
            return Result.fail("删除失败！数据不存在！");
        }
        int i = sysMenuMapper.deleteById(id);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }


    /**
     *  获取菜单详情
     * @param id
     * @return
     */
    @Override
    public Result getMenuInfo(Integer id) {
        SysMenu sysMenu = sysMenuMapper.selectById(id);
        return Result.success("菜单详情",sysMenu);
    }

    /**
     *  菜单列表
     * @param params
     * @return
     */
    @Override
    public Result  findAll(Map<String, Object> params) {
        List<MenuVO> list = sysMenuMapper.findAll(params);

        if (!Objects.isNull(list) && !list.isEmpty()){
            //顶级
            List<MenuVO> pList = list.stream().filter(val -> val.getPid() == null || val.getPid() == 0).collect(Collectors.toList());
            if (!Objects.isNull(pList) && !pList.isEmpty()){
                pList.forEach(val ->{
                    getChildren(list,val);
                });
            }
            return Result.success("获取菜单成功！",pList);
        }
        return Result.success("获取菜单成功！",list);
    }

    private void getChildren(List<MenuVO> list, MenuVO vo) {
        List<MenuVO> children = new ArrayList<>();
        list.forEach(val ->{
            if (val.getPid().equals(vo.getId())){
                children.add(val);
            }
        });
        if (children.size()>0){
            vo.setChildren(children);
            children.forEach(val ->{
                getChildren(list,val);
            });
        }
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenuVO menu) {
        String component = Constants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenuVO menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuVO menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getPid().intValue() && Constants.TYPE_DIR.equals(menu.getMenuType())
                && Constants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(SysMenuVO menu) {
        return menu.getPid().intValue() == 0 && Constants.MENU_TYPE.equals(menu.getMenuType())
                && menu.getIsFrame().equals(Constants.NO_FRAME);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenuVO> getChildPerms(List<SysMenuVO> list, int parentId) {
        List<SysMenuVO> returnList = new ArrayList<SysMenuVO>();
        for (Iterator<SysMenuVO> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenuVO t = (SysMenuVO) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenuVO> list, SysMenuVO t) {
        // 得到子节点列表
        List<SysMenuVO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuVO tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<SysMenuVO> it = childList.iterator();
                while (it.hasNext()) {
                    SysMenuVO n = (SysMenuVO) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuVO> getChildList(List<SysMenuVO> list, SysMenuVO t) {
        List<SysMenuVO> tlist = new ArrayList<SysMenuVO>();
        Iterator<SysMenuVO> it = list.iterator();
        while (it.hasNext()) {
            SysMenuVO n = (SysMenuVO) it.next();
            if (n.getPid().equals(t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuVO> list, SysMenuVO t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
