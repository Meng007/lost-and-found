package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysNotice;
import com.mds.my.platform.lostandfound.project.system.domain.vo.NoticeVO;

import java.util.List;
import java.util.Map;

/**
 * @author 13557
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice> {
    List<NoticeVO> findAll(Map<String, Object> params);

    List<SysNotice> findLb(Map<String, Object> params);
}