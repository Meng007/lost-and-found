package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysComment;
import com.mds.my.platform.lostandfound.project.system.domain.vo.CommentVO;

import java.util.List;
import java.util.Map;

public interface SysCommentMapper extends BaseMapper<SysComment> {
    List<CommentVO> findAll(Map<String, Object> params);

    List<CommentVO> findMyAll(Map<String, Object> params);
}