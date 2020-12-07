package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMessage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.vo.GoodsMessageVO;
import com.mds.my.platform.lostandfound.project.system.mapper.SysGoodsMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysMessageMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
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
        goodsMessage.setUserId(user.getId());
        goodsMessage.setIsDelete(0);
        goodsMessage.setStatus(1);
        goodsMessage.setAgree(0);
        if (Objects.isNull(goodsMessage.getMessageId())){
            goodsMessage.setMessageId(0);
        }
        int i = goodsMessageMapper.insert(goodsMessage);
        if (i>0){
            if (goodsMessage.getMessageId() == 0){
                String con = "你的发布的物品【"+sysGoods.getGoodsTitle()+"】被["+user.getUsername()+"]留言，点击["+goodsMessage.getGoodsId()+"]可以查看";
                //物品留言信息
                //消息类型 1 系统消息 2 留言消息
                goodsMessage(user,sysGoods,con,2);
            }else {
                String con = "你的留言的物品【"+sysGoods.getGoodsTitle()+"】被["+user.getUsername()+"]回复，点击["+goodsMessage.getGoodsId()+"]可以查看";
                //
                //消息类型 1 系统消息 2 留言消息
                goodsMessage(user,sysGoods,con,2);
            }
            return Result.success("留言成功！");
        }
        return Result.fail("留言失败！");
    }

    @Override
    public PageResult messageList(Map<String, Object> params) {
        String page = (String)params.get("page");
        String size = (String)params.get("size");
        Integer p = 1;
        Integer s = 10;
        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)){
            p = Integer.parseInt(page);
            s = Integer.parseInt(size);
        }
        PageHelper.startPage(p,s);
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

    private void getParentMessage(List<GoodsMessageVO> vo, int i) {
        //父
        List<GoodsMessageVO> tem = new ArrayList<>();
        if (Objects.isNull(vo) || vo.isEmpty()){
            return;
        }
        vo.forEach(val ->{
            if (val.getMessageId() == i){
                tem.add(val);
            }
        });
        // 子
        if (!Objects.isNull(tem) && tem.isEmpty()){
            tem.forEach(val ->{
                getParentMessage(vo,val);
            });
        }
    }
    private void getParentMessage(List<GoodsMessageVO> vo,GoodsMessageVO goodsMessageVO){
        List<GoodsMessageVO> ch = new ArrayList<>();
        vo.forEach(val ->{
            if (val.getMessageId().equals(goodsMessageVO.getId())){
                ch.add(val);
            }
        });
        goodsMessageVO.setCh(ch);
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
}

