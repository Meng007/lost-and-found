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
import com.mds.my.platform.lostandfound.project.system.mapper.SysSensitiveWordMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysSensitiveWord;
import com.mds.my.platform.lostandfound.project.system.service.SysSensitiveWordService;
/**
 * @author 13557
 */
@Service
public class SysSensitiveWordServiceImpl extends ServiceImpl<SysSensitiveWordMapper, SysSensitiveWord> implements SysSensitiveWordService{

    @Autowired
    private SysSensitiveWordMapper sysSensitiveWordMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Result saveWord(SysSensitiveWord sysSensitiveWord) {
        if (Objects.isNull(sysSensitiveWord)){
            return Result.fail("提交数据不能为空！");
        }
        sysSensitiveWord.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysSensitiveWord.setCreateUser(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        sysSensitiveWord.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int i = sysSensitiveWordMapper.insert(sysSensitiveWord);
        if (i>0){
            return Result.success("保存敏感词成功!");
        }
        return Result.fail("保存敏感词失败!");
    }

    @Override
    public Result updateSensitiveWord(SysSensitiveWord sysSensitiveWord) {
        if (Objects.isNull(sysSensitiveWord)){
            return Result.fail("修改数据不能为空！");
        }
        if (Objects.isNull(sysSensitiveWord.getId())){
            return Result.fail("id不能为空!");
        }
        sysSensitiveWord.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        sysSensitiveWord.setUpdateUser(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        int i = sysSensitiveWordMapper.updateById(sysSensitiveWord);
        if (i>0){
            return Result.success("修改成功！");
        }
        return Result.fail("修改失败！");
    }

    @Override
    public Result removeSensitiveWord(Integer id) {
        SysSensitiveWord one = sysSensitiveWordMapper.selectById(id);
        if (Objects.isNull(one)){
            return Result.fail("删除失败，数据不存在！");
        }
        int i = sysSensitiveWordMapper.deleteById(id);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }

    @Override
    public Result echo(Integer id) {
        SysSensitiveWord sysSensitiveWord = sysSensitiveWordMapper.selectById(id);
        return Result.success("铭感词数据回显成功！",sysSensitiveWord);
    }
}
