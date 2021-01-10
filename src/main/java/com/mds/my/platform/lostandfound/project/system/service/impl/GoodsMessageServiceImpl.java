package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.*;
import com.mds.my.platform.lostandfound.project.system.domain.vo.GoodsMessageVO;
import com.mds.my.platform.lostandfound.project.system.mapper.SysCommentMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysGoodsMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysMessageMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.GoodsMessageMapper;
import com.mds.my.platform.lostandfound.project.system.service.GoodsMessageService;

@Service
public class GoodsMessageServiceImpl extends ServiceImpl<GoodsMessageMapper, GoodsMessage> implements GoodsMessageService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private GoodsMessageMapper goodsMessageMapper;
    @Autowired
    private SysMessageMapper sysMessageMapper;
    @Autowired
    private SysGoodsMapper sysGoodsMapper;
    @Autowired
    private SysCommentMapper sysCommentMapper;
    @Override
    public Result saveMessage(GoodsMessage goodsMessage) {
        if (Objects.isNull(goodsMessage)){
            return Result.fail("留言数据不能为空！");
        }
        if (StringUtils.isEmpty(goodsMessage.getMessageContent())){
            return Result.fail("留言内容不能为空！");
        }
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        if (Objects.isNull(user)){
            return Result.fail("用户未登录！");
        }
        if (Objects.isNull(goodsMessage.getGoodsId())){
            return Result.fail("物品id不能为空！");
        }
        SysGoods sysGoods = sysGoodsMapper.selectById(goodsMessage.getGoodsId());
        if (Objects.isNull(sysGoods)){
            return Result.fail("物品不存在");
        }
        goodsMessage.setMessageType("goods");
        goodsMessage.setCreateTime(new Timestamp(System.currentTimeMillis()));
        goodsMessage.setUserId(user.getId());
        goodsMessage.setIsDelete(0);
        goodsMessage.setStatus(1);
        goodsMessage.setAgree(0);
        if (Objects.isNull(goodsMessage.getMessageId())){
            goodsMessage.setMessageId(0);
        }
        if (Objects.isNull(goodsMessage.getRoot())){
            goodsMessage.setRoot(0);
        }
        int i = goodsMessageMapper.insert(goodsMessage);
        if(i>0){
            return Result.success("留言成功！");
        }
        return Result.fail("留言失败！");
    }

    @Override
    public PageResult messageList(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        List<GoodsMessageVO> vo  = goodsMessageMapper.messageList(params);
        //父级
        //getParentMessage(vo,0);
        PageInfo<GoodsMessageVO> info = new PageInfo(vo);
        return PageResult.<GoodsMessageVO>builder().data(vo).msg("获取物品留言成功！").build();
    }

    @Override
    public Result removeMessage(Integer messageId, Integer userId) {
        GoodsMessage goodsMessage = goodsMessageMapper.selectById(messageId);
        if (Objects.isNull(goodsMessage)){
            return Result.fail("留言不存在！");
        }
        if (! goodsMessage.getUserId().equals(userId)){
            return Result.fail("没有权限删除该留言！");
        }
        goodsMessage.setIsDelete(1);
        int update = goodsMessageMapper.updateById(goodsMessage);
        if (update>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败！");
    }

    /**
     * 保存留言信息
     * @param goodsMessage
     * @return
     */
    @Override
    public Result saveComm(GoodsMessage goodsMessage) {
        SysComment sysComment = sysCommentMapper.selectById(goodsMessage.getGoodsId());
        if (Objects.isNull(sysComment)){
            return Result.fail("留言不存在！");
        }
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        goodsMessage.setCreateTime(new Timestamp(System.currentTimeMillis()));
        goodsMessage.setIsDelete(0);
        goodsMessage.setAgree(0);
        goodsMessage.setStatus(0);
        goodsMessage.setMessageType("comment");
        goodsMessage.setUserId(user.getId());
        if (Objects.isNull(goodsMessage.getMessageId())){
            goodsMessage.setMessageId(0);
        }
        if (Objects.isNull(goodsMessage.getRoot())){
            goodsMessage.setRoot(0);
        }
        int i = goodsMessageMapper.insert(goodsMessage);
        if (i>0){
            //留言数加
            SysComment comm = new SysComment();
            comm.setId(sysComment.getId());
            comm.setMessageNum(sysComment.getMessageNum() + 1);
            int j = sysCommentMapper.updateById(comm);
            return Result.success("留言成功！",sysComment.getMessageNum() + 1);
        }
        return Result.fail("留言失败！");
    }

    @Override
    public PageResult getGoodsComment(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        params.put("type","goods");
        params.put("root",0);
        List<GoodsMessageVO> vo =  goodsMessageMapper.findGoodsComment(params);
        if (!Objects.isNull(vo) && !vo.isEmpty()){
            vo.forEach(val ->{
                Map<String,Object> par = new HashMap<>();
                par.put("goodsId",params.get("goodsId"));
                par.put("type","goods");
                par.put("root",val.getId());
                //子留言
                List<GoodsMessageVO> ch =  goodsMessageMapper.findGoodsComment(par);
                if (!Objects.isNull(ch) && !ch.isEmpty()){
                    ch.forEach(chVal ->{
                        resetComment(ch,chVal);
                    });
                }
                PageInfo<GoodsMessageVO> chInfo = new PageInfo<>(ch);
                PageResult chP= PageResult.<GoodsMessageVO>builder()
                        .total(chInfo.getTotal())
                        .data(chInfo.getList())
                        .build();
                val.setCh(chP);
            });
        }
        PageInfo<GoodsMessageVO> info = new PageInfo<>(vo);
        return PageResult.<GoodsMessageVO>builder()
                .total(info.getTotal())
                .data(info.getList())
                .build();
    }

    private void resetComment(List<GoodsMessageVO> ch, GoodsMessageVO chVal) {
        ch.forEach(val ->{
            if (val.getMessageId().equals(chVal.getId())){
                String s = " 回复@"+chVal.getNickName() +" : ";
                val.setMessageContent(s+val.getMessageContent());
            }
        });
    }

    /**
     *
     * @param user
     * @param sysGoods
     * @param con
     * @param type
     */
    private void goodsMessage(SysUser user,SysGoods sysGoods,String con,Integer type){

        //发送消息
        SysMessage sysMessage = new SysMessage();
        //发消息人
        sysMessage.setCreateUser(user.getId());
        sysMessage.setMessage(con);
        sysMessage.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sysMessage.setIsDelete(0);
        // 0 未读 1 已读
        sysMessage.setStatus(0);
        //接收消息人
        sysMessage.setUserId(sysGoods.getUserId());
        //消息类型 1 系统消息 2 留言消息
        sysMessage.setType("弃用");
        int m = sysMessageMapper.insert(sysMessage);
        if (m>0){
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 8000 ; i++) {
            System.out.println(i);
        }
        Long end = System.currentTimeMillis();
        System.out.println("8000需要时间:"+ (end-start));
    }
}

