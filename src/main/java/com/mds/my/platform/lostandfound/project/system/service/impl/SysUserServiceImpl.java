package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.constant.Constants;
import com.mds.my.platform.lostandfound.common.enums.RoleEnum;
import com.mds.my.platform.lostandfound.common.enums.UserStatus;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.utils.file.FileUploadUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.config.ServerConfig;
import com.mds.my.platform.lostandfound.framework.config.SystemParamsConfig;
import com.mds.my.platform.lostandfound.framework.redis.RedisService;
import com.mds.my.platform.lostandfound.framework.security.LoginUser;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.dto.UserDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUserInfo;
import com.mds.my.platform.lostandfound.project.system.domain.vo.UserVO;
import com.mds.my.platform.lostandfound.project.system.mapper.SysRoleMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysUserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysUserMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.service.SysUserService;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ServerConfig serverConfig;
    

    @Override
    public Result regUser(UserDTO userDTO) {
        if (Objects.isNull(userDTO)){
            return Result.fail("提交数据不能为空！");
        }
        //系统用户信息赋值
        SysUser sysUser = new SysUser();
        sysUser.setPassword(userDTO.getPassword());
        sysUser.setUsername(userDTO.getUsername());
        sysUser.setSex(userDTO.getSex());
        sysUser.setNickName(userDTO.getNickName());

        if (StringUtils.isEmpty(sysUser.getUsername())){
            return Result.fail("用户名不能为空！");
        }
        LambdaQueryWrapper<SysUser> lqm = new LambdaQueryWrapper<>();
        lqm.eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserMapper.selectOne(lqm);
        if (!Objects.isNull(one)){
            return Result.fail("用户已存在！");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())){
            return Result.fail("密码不能为空！");
        }
        //加密
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        sysUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysUser.setIsDelete(0);
        sysUser.setStatus(1);
        sysUser.setUserType(2);
        int i = sysUserMapper.insert(sysUser);
        //给用户默认角色
        Integer r = sysRoleMapper.setUserRole(sysUser.getId(), RoleEnum.STUDENT.getCode());
        if (i>0){
            //创建系统用户详情数据赋值
            SysUserInfo info = new SysUserInfo();
            info.setPhone(userDTO.getPhone());
            info.setEmail(userDTO.getEmail());
            info.setDormitory(userDTO.getDormitory());
            info.setQq(userDTO.getQq());
            info.setWeixin(userDTO.getWeixin());
            info.setIntroduce(userDTO.getIntroduce());
            info.setSchool(userDTO.getSchool());
            info.setRealName(userDTO.getRealName());
            info.setUserId(sysUser.getId());
            int j = sysUserInfoMapper.insert(info);
            if (j>0){
                return Result.success("注册成功！");
            }
        }
        return Result.fail("注册失败！");
    }

    @Override
    public Result updatePassword(UserDTO userDTO) {
        if (Objects.isNull(userDTO)){
            return Result.fail("提交数据不能为空！");
        }
        SysUser one = sysUserMapper.selectById(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        if (Objects.isNull(one)){
            return Result.fail("用户不存在！");
        }
        if (StringUtils.isNotEmpty(userDTO.getOldPassword())){
            return Result.fail("旧密码不能为空！");
        }
        if (StringUtils.isEmpty(userDTO.getPassword())){
            return Result.fail("新密码不能为空！");
        }
        if (!one.getPassword().equals(bCryptPasswordEncoder.encode(userDTO.getOldPassword()))) {
            return Result.fail("旧密码和原密码不一致！");
        }
        SysUser user = new SysUser();
        user.setId(one.getId());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        int i = sysUserMapper.updateById(user);
        if (i>0){
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            loginUser.getUser().setPassword(user.getPassword());
            tokenService.delLoginUser(loginUser.getToken());
            tokenService.setLoginUser(loginUser);
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
        return PageResult.<UserVO>builder().total(info.getTotal()).msg("获取用户列表成功！").code(200).data(info.getList()).build();
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

    @Override
    public PageResult getLoginUserList(Map<String, Object> params) {
        List<LoginUser> list = new ArrayList<>();
        Set<String> keys = redisService.keys(Constants.LOGIN_TOKEN_KEY + "*");
        for (String key : keys){
            if (StringUtils.isNotEmpty(key)){
                String loginUser = (String)redisService.getCacheObject(key);
                LoginUser user = JSONObject.parseObject(loginUser, LoginUser.class);
                list.add(user);
            }
        }
        PageInfo<LoginUser> info = new PageInfo<>(list);
        return PageResult.<LoginUser>builder().code(200).data(info.getList()).msg("无数据！").total(info.getTotal()).build();
    }

    @Override
    public Result updateAvatar(MultipartFile multipartFile) {
        String filePath = SystemParamsConfig.getUploadPath();
        try {
            Map<String,Object> result = new HashMap<>(2);
            String fileName = FileUploadUtils.upload(filePath,multipartFile);
            String fileUrl = serverConfig.getUrl() + fileName;
            result.put("fileName",fileName);
            result.put("fileUrl",fileUrl);
            SysUser sysUser = new SysUser();
            sysUser.setId(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
            sysUser.setAvatar(fileUrl);
            //
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            loginUser.getUser().setAvatar(fileUrl);
            tokenService.delLoginUser(loginUser.getToken());
            tokenService.setLoginUser(loginUser);
            int i = sysUserMapper.updateById(sysUser);
            if (i>0){
                return Result.success("头像修改成功！",result);
            }
            return Result.fail("头像修改失败！",result);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("文件上传失败！");
        }
    }

    @Override
    public Result updateUserInfo(UserDTO userDTO) {

        //系统用户信息赋值
        SysUser sysUser = new SysUser();
        sysUser.setId(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        sysUser.setSex(userDTO.getSex());
        sysUser.setNickName(userDTO.getNickName());
        if (!Objects.isNull(sysUser.getSex()) || StringUtils.isNotEmpty(sysUser.getNickName())){
            int i = sysUserMapper.updateById(sysUser);
        }

        SysUserInfo info = new SysUserInfo();
        info.setPhone(userDTO.getPhone());
        info.setEmail(userDTO.getEmail());
        info.setDormitory(userDTO.getDormitory());
        info.setQq(userDTO.getQq());
        info.setWeixin(userDTO.getWeixin());
        info.setIntroduce(userDTO.getIntroduce());
        info.setSchool(userDTO.getSchool());
        info.setRealName(userDTO.getRealName());
        info.setUserId(sysUser.getId());
        int i1 = sysUserInfoMapper.updateById(info);

        return Result.success("修改用户信息成功！");
    }
}
