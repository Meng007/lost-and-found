package com.mds.my.platform.lostandfound.project.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysNotice;
import com.mds.my.platform.lostandfound.project.system.mapper.SysNoticeMapper;
import com.mds.my.platform.lostandfound.project.system.service.SysNoticeService;
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService{

}
