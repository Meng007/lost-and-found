package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysDictTypeMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictType;
import com.mds.my.platform.lostandfound.project.system.service.SysDictTypeService;
import oshi.util.MapUtil;

@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService{

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Result saveDictType(SysDictType sysDictType) {
        if (Objects.isNull(sysDictType)){
            return Result.fail("提交字典类型不能为空！");
        }
        sysDictType.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysDictType.setCreateUser(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        int i = sysDictTypeMapper.insert(sysDictType);
        if (i>0){
            return Result.success("保存字典类型成功！");
        }
        return Result.fail("保存字典类型失败!");
    }

    @Override
    public PageResult findAll(Map params) {
        StartPageUtils.startPage(params);
        LambdaQueryWrapper<SysDictType> lqw = new LambdaQueryWrapper<>();
        List<SysDictType> list = sysDictTypeMapper.selectList(lqw);
        PageInfo<SysDictType> info = new PageInfo<>(list);
        return PageResult.<SysDictType>builder().msg("获取字典类型成功!").total(info.getTotal()).data(list).build();
    }
}
