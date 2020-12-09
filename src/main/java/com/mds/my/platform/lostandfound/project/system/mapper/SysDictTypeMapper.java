package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictType;
import com.mds.my.platform.lostandfound.project.system.domain.vo.DictTypeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 13557
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
    /**
     *  获取字典列表
     * @param params
     * @return
     */
    List<DictTypeVO> findAll(Map params);
}