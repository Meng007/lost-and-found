package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
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
}
