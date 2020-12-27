package com.mds.my.platform.lostandfound.project.system.domain.dto;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCommImage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysComment;
import lombok.Data;

import java.util.List;

/**
 * @author 13557
 */
@Data
public class MessageDTO extends SysComment {

    List<MessageTagDTO> tags;
    List<SysCommImage> images;
}
