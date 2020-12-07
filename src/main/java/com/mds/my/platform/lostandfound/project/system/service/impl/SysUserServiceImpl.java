package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.enums.UserStatus;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUserInfo;
import com.mds.my.platform.lostandfound.project.system.domain.vo.UserVO;
import com.mds.my.platform.lostandfound.project.system.mapper.SysUserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysUserMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.service.SysUserService;
/**
 * @author 13557
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Result regUser(SysUser sysUser) {
        if (Objects.isNull(sysUser)){
            return Result.fail("提交数据不能为空！");
        }
        if (StringUtils.isEmpty(sysUser.getUsername())){
            return Result.fail("用户名不能为空！");
        }
        LambdaQueryWrapper<SysUser> lqm = new LambdaQueryWrapper<>();
        lqm.eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserMapper.selectOne(lqm);
        if (Objects.isNull(one)){
            return Result.fail("用户已存在！");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())){
            return Result.fail("密码不能为空！");
        }
        //加密
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        int i = sysUserMapper.insert(sysUser);
        if (i>0){
            //创建详情表
            SysUserInfo info = new SysUserInfo();
            info.setUserId(sysUser.getId());
            int j = sysUserInfoMapper.insert(info);
            if (j>0){
                return Result.success("注册成功！");
            }
        }
        return Result.fail("注册失败！");
    }

    @Override
    public Result updatePassword(SysUser sysUser) {
        if (Objects.isNull(sysUser)){
            return Result.fail("提交数据不能为空！");
        }
        if (Objects.isNull(sysUser.getId())){
            return Result.fail("用户id不能为空！");
        }
        SysUser one = sysUserMapper.selectById(sysUser.getId());
        if (Objects.isNull(one)){
            return Result.fail("用户不存在！");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())){
            return Result.fail("新密码不能为空！");
        }
        one.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        int i = sysUserMapper.updateById(one);
        if (i>0){
            return Result.success("修改密码成功！");
        }
        return Result.fail("修改密码失败！");
    }

    @Override
    public PageResult getUserList(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        params.put("isDelete",new Integer(0));
        List<UserVO> vo = sysUserMapper.getUserList(params);
        if (!Objects.isNull(vo) && !vo.isEmpty()){
            vo.forEach(val ->{
                if (UserStatus.OK.getCode().equals(val.getStatus())){
                    val.setStatusName("启用");
                }else {
                    val.setStatusName("禁用");
                }
                if (UserStatus.USER_TYPE.getCode().equals(val.getUserType())){
                    val.setTypeName("管理员");
                }else {
                    val.setTypeName("普通用户");
                }
            });

        }
        PageInfo<UserVO> info = new PageInfo<>(vo);
        return PageResult.<UserVO>builder().total(info.getTotal()).msg("获取用户列表成功！").code(200).data(vo).build();
    }

    @Override
    public Result removeUser(Integer id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (Objects.isNull(sysUser)){
            return Result.fail("删除失败，用户不存在！");
        }
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        if (Objects.isNull(user)){
            return Result.fail("删除失败，用户未登录！");
        }
        if (!UserStatus.USER_TYPE.getCode().equals(user.getUserType())){
            return Result.fail("删除失败，你不是管理员！");
        }
        sysUser.setIsDelete(1);
        int i = sysUserMapper.updateById(sysUser);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }
}
