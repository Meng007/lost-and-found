package com.mds.my.platform.lostandfound.project.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import com.mds.my.platform.lostandfound.project.system.mapper.GoodsMessageMapper;
import com.mds.my.platform.lostandfound.project.system.service.GoodsMessageService;
@Service
public class GoodsMessageServiceImpl extends ServiceImpl<GoodsMessageMapper, GoodsMessage> implements GoodsMessageService{

}
