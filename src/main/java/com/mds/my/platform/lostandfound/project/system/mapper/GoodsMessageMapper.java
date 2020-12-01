package com.mds.my.platform.lostandfound.project.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.mds.my.platform.lostandfound.project.system.domain.vo.GoodsMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 1355
 */
@Mapper
public interface GoodsMessageMapper extends BaseMapper<GoodsMessage> {
    /**
     * 获取物品留言
     * @param params
     * @return
     */
    List<GoodsMessageVO> messageList(Map<String, Object> params);
}