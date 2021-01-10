package com.mds.my.platform.lostandfound.project.system.service;

import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 13557
 */
public interface SysNoticeService extends IService<SysNotice>{

    /**
     *  发布公告
     * @param sysNotice
     * @return
     */
    Result saveNotice(SysNotice sysNotice);

    /**
     * 更新公告
     * @param sysNotice
     * @return
     */
    Result updateNotice(SysNotice sysNotice);

    /**
     * 删除公告
     * @param id
     * @return
     */
    Result removeNotice(Integer id);

    /**
     *  获取公告详情
     * @param id
     * @return
     */
    Result getNoticeInfo(Integer id);

    /**
     *  后台获取公告
     * @param params
     * @return
     */
    PageResult noticeList(Map<String, Object> params);

    /**
     *  获取后也轮播
     * @param params
     * @return
     */
    Result getLb(Map<String, Object> params);
}
