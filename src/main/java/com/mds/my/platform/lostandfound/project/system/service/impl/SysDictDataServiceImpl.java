package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.enums.DictStatus;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.vo.DictDataVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictData;
import com.mds.my.platform.lostandfound.project.system.mapper.SysDictDataMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysDictDataService;
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService{
    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Result saveDictData(SysDictData sysDictData) {
        if (Objects.isNull(sysDictData)){
            return Result.fail("提交字典数据不能为空！");
        }
        sysDictData.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysDictData.setCreateUser(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        int i = sysDictDataMapper.insert(sysDictData);
        if (i>0){
            return Result.success("保存字典成功！");
        }
        return Result.fail("保存字典失败！");
    }

    @Override
    public PageResult findAll(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        List<DictDataVO> data = sysDictDataMapper.findAll(params);
        if (!Objects.isNull(data) && !data.isEmpty()){
            data.forEach(val ->{
                if (new Integer(1).equals(val.getStatus())){
                    val.setStatusName("禁用");
                }else if (new Integer(0).equals(val.getStatus())){
                    val.setStatusName("启用");
                }else {
                    val.setStatusName("其他");
                }
            });
        }
        PageInfo<DictDataVO> info = new PageInfo<>(data);
        return PageResult.<DictDataVO>builder().code(200).total(info.getTotal()).data(info.getList()).build();
    }

    @Override
    public Result removeDictData(Integer id) {
        SysDictData one = sysDictDataMapper.selectById(id);
        if (Objects.isNull(one)){
            return Result.fail("删除失败，数据不存在！");
        }
        int i = sysDictDataMapper.deleteById(id);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }

    @Override
    public Result updateDictData(SysDictData sysDictData) {
        if (Objects.isNull(sysDictData)){
            return Result.fail("修改数据不能为空！");
        }
        if (Objects.isNull(sysDictData.getId())){
            return Result.fail("修改数据id不能为空！");
        }
        SysDictData one = sysDictDataMapper.selectById(sysDictData.getId());
        if (Objects.isNull(one)){
            return Result.fail("修改数据不存在！");
        }
        sysDictData.setUpdateUser(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        int i = sysDictDataMapper.updateById(sysDictData);
        if (i>0){
            return Result.success("修改成功！");
        }
        return Result.fail("修改失败！");
    }

    @Override
    public Result getDictDataByType(Integer type) {
        LambdaQueryWrapper<SysDictData> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SysDictData::getDictType,type);
        List<SysDictData> list = sysDictDataMapper.selectList(lqw);
        if (!Objects.isNull(list) && !list.isEmpty()){
            list.stream().filter(val ->DictStatus.OPEN.getCode().equals(val.getStatus()));
            return Result.success("获取字典数据成功！",list);
        }
        return Result.success("没有数据！");
    }
}
