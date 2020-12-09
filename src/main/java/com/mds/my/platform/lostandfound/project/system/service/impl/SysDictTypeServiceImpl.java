package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.vo.DictTypeVO;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
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
       List<DictTypeVO> list = sysDictTypeMapper.findAll(params);
        if (!Objects.isNull(list) && !list.isEmpty()){
            list.forEach(val ->{
                if (new Integer(1).equals(val.getStatus())){
                    val.setStatusName("启用");
                }else if (new Integer(0).equals(val.getStatus())){
                    val.setStatusName("禁用");
                }else {
                    val.setStatusName("其他");
                }
            });
        }
        PageInfo<DictTypeVO> info = new PageInfo<>(list);
        return PageResult.<DictTypeVO>builder().code(200).msg("获取字典类型成功!").total(info.getTotal()).data(info.getList()).build();
    }

    @Override
    public Result updateDictType(SysDictType sysDictType) {
        if (Objects.isNull(sysDictType)){
            return Result.fail("提交数据不能为空！");
        }
        if (Objects.isNull(sysDictType.getId())){
            return Result.fail("提交参数错误！");
        }
        SysDictType one = sysDictTypeMapper.selectById(sysDictType.getId());
        if (Objects.isNull(one)){
            return Result.fail("修改数据不存在！");
        }
        int i = sysDictTypeMapper.updateById(sysDictType);
        if (i>0){
            return Result.success("修改成功！");
        }
        return Result.fail("修改失败!");
    }

    @Override
    public Result removeDictType(Integer id) {
        SysDictType one = sysDictTypeMapper.selectById(id);
        if (Objects.isNull(one)){
            return Result.fail("删除失败，数据不存在！");
        }
        int i = sysDictTypeMapper.deleteById(id);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }
}
