package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysComment;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.mapper.GoodsMessageMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysCommentMapper;
import org.apache.lucene.search.similarities.Lambda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessageAgree;
import com.mds.my.platform.lostandfound.project.system.mapper.GoodsMessageAgreeMapper;
import com.mds.my.platform.lostandfound.project.system.service.GoodsMessageAgreeService;
/**
 * @author 13557
 */
@Service
public class GoodsMessageAgreeServiceImpl extends ServiceImpl<GoodsMessageAgreeMapper, GoodsMessageAgree> implements GoodsMessageAgreeService{

    @Autowired
    private GoodsMessageMapper goodsMessageMapper;
    @Autowired
    private GoodsMessageAgreeMapper goodsMessageAgreeMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysCommentMapper sysCommentMapper;
    @Override
    public Result saveAgree(GoodsMessageAgree goodsMessageAgree) {

        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        SysComment sysComment = sysCommentMapper.selectById(goodsMessageAgree.getMessageId());
        if (Objects.isNull(sysComment)){
            return Result.fail("点赞失败，留言不存在！");
        }
        goodsMessageAgree.setUserId(user.getId());
        LambdaQueryWrapper<GoodsMessageAgree> lqb = new LambdaQueryWrapper<>();
        lqb.eq(GoodsMessageAgree::getMessageId,goodsMessageAgree.getMessageId())
                .and(val -> val.eq(GoodsMessageAgree::getUserId,goodsMessageAgree.getUserId()));
        GoodsMessageAgree gAgree = goodsMessageAgreeMapper.selectOne(lqb);
        if (Objects.isNull(gAgree)){
            //点赞
            int i = goodsMessageAgreeMapper.insert(goodsMessageAgree);
            SysComment comm = new SysComment();
            comm.setAgreeNum(sysComment.getAgreeNum() + 1);
            comm.setId(sysComment.getId());
            int j = sysCommentMapper.updateById(comm);
            if (i+j>1){
                return Result.success("点赞成功",sysComment.getAgreeNum() + 1);
            }
            return Result.fail("点赞失败！");
        }
        //取消点赞
        int i = goodsMessageAgreeMapper.delete(lqb);
        SysComment comm = new SysComment();
        comm.setAgreeNum(sysComment.getAgreeNum() - 1);
        comm.setId(sysComment.getId());
        int j = sysCommentMapper.updateById(comm);
        if (i+j>1){
            return Result.success("取消点赞成功",sysComment.getAgreeNum() - 1);
        }
        return Result.fail("取消点赞失败！");
    }
}
