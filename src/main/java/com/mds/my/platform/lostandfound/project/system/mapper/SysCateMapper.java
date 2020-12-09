package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCate;import com.mds.my.platform.lostandfound.project.system.domain.vo.CateVO;import java.util.List;import java.util.Map;

public interface SysCateMapper extends BaseMapper<SysCate> {
    /**
     * 获取系统分类
     *
     * @param params
     * @return
     */
    List<CateVO> findAll(Map<String, Object> params);

    /**
     * 获取分类
     * @param pid
     * @return
     */
    List<SysCate> getCateByPid(int pid);
}