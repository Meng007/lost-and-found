package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysNotice;
import lombok.Data;

/**
 * @author 13557
 */
@Data
public class NoticeVO extends SysNotice {
    private String nickName;
    private String avatar;
}
