package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.dto.GoodsDTO;
import com.mds.my.platform.lostandfound.project.system.domain.vo.GoodsVO;
import com.mds.my.platform.lostandfound.project.system.service.SensitiveWordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mds.my.platform.lostandfound.project.system.mapper.SysGoodsMapper;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.service.SysGoodsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 13557
 */
@Service
public class SysGoodsServiceImpl extends ServiceImpl<SysGoodsMapper, SysGoods> implements SysGoodsService{

    @Autowired
    private SensitiveWordService sensitiveWordService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysGoodsMapper sysGoodsMapper;

    @Override
    @Transactional
    public Result saveGoods(GoodsDTO goodsDTO) {
        if (Objects.isNull(goodsDTO)){
            return Result.fail("提交物品数据不能为空！");
        }
        goodsDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        goodsDTO.setUserId(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        goodsDTO.setIsDelete(0);
        //敏感词检测
        //sensitiveWord(goodsDTO);
        int i = sysGoodsMapper.insert(goodsDTO);
        System.out.println("返回id ->"+goodsDTO.getId());
        if (!Objects.isNull(goodsDTO.getImages()) && !goodsDTO.getImages().isEmpty()){
            //保存详情图片
            sysGoodsMapper.saveImages(goodsDTO);
        }
        if (i>0){
            return Result.success("发布成功！");
        }
        return Result.fail("发布失败！");
    }

    @Override
    public PageResult getGoodsList(Map params) {
        String page = (String)params.get("page");
        String size = (String)params.get("size");
        Integer p = 1;
        Integer s = 10;
        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)){
            p = Integer.parseInt(page);
            s = Integer.parseInt(size);
        }
        PageHelper.startPage(p,s);
        System.out.println("--------------:"+params.get("goodsStatus"));
        List<GoodsVO> gs = sysGoodsMapper.findAll(params);
        PageInfo<GoodsVO> pageInfo = new PageInfo(gs);
        return PageResult.<GoodsVO>builder().data(gs).total(pageInfo.getTotal()).msg("获取物品列表成功！").build();
    }

    /**
     * 铭感词检测
     * @param goodsDTO 物品详情
     */
    private void sensitiveWord(GoodsDTO goodsDTO) {
        Long size = 0L;
        //物品描述
        Result result = sensitiveWordService.SensitiveWord(goodsDTO.getGoodsContent());
        if (!Objects.isNull(result)){
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(result));
            //对象
            String data = jsonObject.getString("data");
            JSONObject dataJsonObject = JSONObject.parseObject(data);
            //内容
            String text = dataJsonObject.getString("text");
            if (StringUtils.isNotBlank(text)){
                goodsDTO.setGoodsContent(text);
            }
            //敏感词数量
             size += dataJsonObject.getLong("size");
        }
        //物品标题
        Result result2 = sensitiveWordService.SensitiveWord(goodsDTO.getGoodsTitle());
        if (!Objects.isNull(result)){
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(result2));
            //对象
            String data = jsonObject.getString("data");
            JSONObject dataJsonObject = JSONObject.parseObject(data);
            //内容
            String text = dataJsonObject.getString("text");
            if (StringUtils.isNotBlank(text)){
                goodsDTO.setGoodsTitle(text);
            }
            //敏感词数量
            size += dataJsonObject.getLong("size");
        }
        if (size>0){
            //待人工审核
            goodsDTO.setStatus(0);
        }
    }
}
