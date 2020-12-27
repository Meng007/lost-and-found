package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.dto.MessageDTO;
import com.mds.my.platform.lostandfound.project.system.domain.dto.MessageTagDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCate;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCommImage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCommTag;
import com.mds.my.platform.lostandfound.project.system.domain.vo.CommentVO;
import com.mds.my.platform.lostandfound.project.system.mapper.SysCateMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysCommImageMapper;
import com.mds.my.platform.lostandfound.project.system.mapper.SysCommTagMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.ObjectStreamClass;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysComment;
import com.mds.my.platform.lostandfound.project.system.mapper.SysCommentMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysCommentService;
import sun.awt.AWTAccessor;

/**
 * @author 13557
 */
@Service
public class SysCommentServiceImpl extends ServiceImpl<SysCommentMapper, SysComment> implements SysCommentService{

    @Autowired
    private SysCommentMapper sysCommentMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysCateMapper sysCateMapper;
    @Autowired
    private SysCommTagMapper sysCommTagMapper;
    @Autowired
    private SysCommImageMapper sysCommImageMapper;
    @Override
    public Result saveMessage(MessageDTO messageDTO) {
        if (Objects.isNull(messageDTO)){
            return Result.fail("提交数据为空！");
        }
        messageDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        messageDTO.setAgreeNum(0);
        messageDTO.setIsDelete(0);
        messageDTO.setStatus(1);
        messageDTO.setViews(0);
        messageDTO.setMessageNum(0);
        messageDTO.setUserId(tokenService
                .getLoginUser(ServletUtils.getRequest())
                .getUser().getId());
        int i = sysCommentMapper.insert(messageDTO);
        if (i>0){
            if (!Objects.isNull(messageDTO.getTags()) && !messageDTO.getTags().isEmpty()){
                //保存话题标签
                List<MessageTagDTO> tags = messageDTO.getTags().stream().filter(val -> StringUtils.isNotEmpty(val.getTagName())).collect(Collectors.toList());
                if (!Objects.isNull(tags) && !tags.isEmpty()){
                    tags.forEach(val ->{
                        //查询分类表里是否存在该标签名称
                        LambdaQueryWrapper<SysCate> w = new LambdaQueryWrapper<>();
                        w.eq(SysCate::getCateName,val.getTagName().trim());
                        SysCate cate = sysCateMapper.selectOne(w);
                        if (!Objects.isNull(cate)){
                            //存在则不添加
                            SysCommTag sysCommTag = new SysCommTag();
                            sysCommTag.setCommentId(messageDTO.getId());
                            sysCommTag.setTagId(cate.getId());
                            //保存关系
                            sysCommTagMapper.insert(sysCommTag);
                        }else {
                            SysCate sysCate = new SysCate();
                            sysCate.setCateName(val.getTagName());
                            sysCate.setCreateUser(tokenService
                                    .getLoginUser(ServletUtils.getRequest())
                                    .getUser().getId());
                            sysCate.setPid(16);
                            sysCate.setCreateTime(new Timestamp(System.currentTimeMillis()));
                            sysCate.setSort(0);
                            //保存标签
                            sysCateMapper.insert(sysCate);
                            SysCommTag sysCommTag = new SysCommTag();
                            sysCommTag.setCommentId(messageDTO.getId());
                            sysCommTag.setTagId(sysCate.getId());
                            //保存关系
                            sysCommTagMapper.insert(sysCommTag);
                        }
                    });
                }
            }
            if (!Objects.isNull(messageDTO.getImages()) && !messageDTO.getImages().isEmpty()){
                //保存话题图片
                List<SysCommImage> collect = messageDTO.getImages().stream().filter(val -> StringUtils.isNotEmpty(val.getImagePath())).collect(Collectors.toList());
                if (!Objects.isNull(collect) && !collect.isEmpty()){
                    collect.forEach(val ->{
                        val.setCommentId(messageDTO.getId());
                        sysCommImageMapper.insert(val);
                    });
                }
            }
            return Result.success("留言成功！");
        }

         return Result.fail("留言失败！");
    }

    @Override
    public PageResult findAll(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        List<CommentVO> vo = sysCommentMapper.findAll(params);
        if (!Objects.isNull(vo) && !vo.isEmpty()){
            vo.forEach(val ->{
                //标签
                List<MessageTagDTO> tags = sysCommTagMapper.getMessageTags(val.getId());
                val.setTags(tags);
                //图片
                LambdaQueryWrapper<SysCommImage> lqb = new LambdaQueryWrapper<>();
                lqb.eq(SysCommImage::getCommentId,val.getId());
                List<SysCommImage> images = sysCommImageMapper.selectList(lqb);
                List<String> preview = new ArrayList<>();
                if (!Objects.isNull(images) && !images.isEmpty()){
                    images.forEach(img ->{
                        preview.add(img.getImagePath());
                    });
                    val.setPreview(preview);
                }
                val.setImages(images);
            });
        }
        PageInfo<CommentVO> info = new PageInfo<>(vo);
        return PageResult.<CommentVO>builder().code(200).total(info.getTotal()).data(info.getList()).msg("获取留言超过！").build();
    }

    @Override
    public PageResult findMyAll(Map<String, Object> params) {
        StartPageUtils.startPage(params);
        params.put("userId",tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        List<CommentVO> vo = sysCommentMapper.findMyAll(params);
        if (!Objects.isNull(vo) && !vo.isEmpty()){
            vo.forEach(val ->{
                //标签
                List<MessageTagDTO> tags = sysCommTagMapper.getMessageTags(val.getId());
                val.setTags(tags);
                //图片
                LambdaQueryWrapper<SysCommImage> lqb = new LambdaQueryWrapper<>();
                lqb.eq(SysCommImage::getCommentId,val.getId());
                List<SysCommImage> images = sysCommImageMapper.selectList(lqb);
                List<String> preview = new ArrayList<>();
                if (!Objects.isNull(images) && !images.isEmpty()){
                    images.forEach(img ->{
                        preview.add(img.getImagePath());
                    });
                    val.setPreview(preview);
                }
                val.setImages(images);
            });
        }
        PageInfo<CommentVO> info = new PageInfo<>(vo);
        return PageResult.<CommentVO>builder().code(200).total(info.getTotal()).data(info.getList()).msg("获取我的留言成功！").build();
    }

    @Override
    public Result removeMessage(Integer id) {
        SysComment sysComment = sysCommentMapper.selectById(id);
        if (Objects.isNull(sysComment)){
            return Result.fail("删除失败，留言不存在！");
        }
        SysComment comm = new SysComment();
        comm.setId(sysComment.getId());
        comm.setIsDelete(1);
        int i = sysCommentMapper.updateById(comm);
        if (i>0){
            Result.fail("删除留言成功！");
        }
        return Result.fail("删除留言失败！");
    }
}
