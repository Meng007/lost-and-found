package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.dto.MessageTagDTO;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCommImage;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysComment;
import lombok.Data;
import org.elasticsearch.index.reindex.ClientScrollableHitSource;

import java.util.List;

/**
 * @author 13557
 */
@Data
public class CommentVO extends SysComment {
    private List<MessageTagDTO> tags;
    private List<SysCommImage> images;
    private String nickName;
    private String avatar;
    private List<String> preview;
    private Boolean isHasImage;
    private Boolean isAgree;
    private List<GoodsMessageVO> messages;
}
