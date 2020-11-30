package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictData;
import com.mds.my.platform.lostandfound.project.system.domain.vo.DictDataVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 13557
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    List<DictDataVO> findAll(Map<String, Object> params);
}