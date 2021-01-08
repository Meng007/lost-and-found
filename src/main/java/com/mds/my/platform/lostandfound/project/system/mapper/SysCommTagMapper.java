package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.dto.MessageTagDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCommTag;

import java.util.List;

/**
 * @author 13557
 */
public interface SysCommTagMapper extends BaseMapper<SysCommTag> {
    List<MessageTagDTO> getMessageTags(Integer id);
}