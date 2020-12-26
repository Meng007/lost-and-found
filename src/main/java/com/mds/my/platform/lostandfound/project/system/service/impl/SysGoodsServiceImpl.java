package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mds.my.platform.lostandfound.common.utils.ServletUtils;
import com.mds.my.platform.lostandfound.common.utils.StartPageUtils;
import com.mds.my.platform.lostandfound.common.web.PageResult;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.framework.security.service.TokenService;
import com.mds.my.platform.lostandfound.project.system.domain.dto.GoodsDTO;
import com.mds.my.platform.lostandfound.project.system.domain.vo.GoodsVO;
import com.mds.my.platform.lostandfound.project.system.service.SensitiveWordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.mds.my.platform.lostandfound.common.enums.ResultCode;

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
    private static final String USER_ID = "userId";

    @Override
    @Transactional
    public Result saveGoods(GoodsDTO goodsDTO) {
        if (Objects.isNull(goodsDTO)){
            return Result.fail("提交物品数据不能为空！");
        }
        goodsDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        goodsDTO.setUserId(tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        goodsDTO.setIsDelete(0);
        goodsDTO.setTopping(false);
        goodsDTO.setStatus(1);
        //敏感词检测
        //sensitiveWord(goodsDTO);
        int i = sysGoodsMapper.insert(goodsDTO);
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
        StartPageUtils.startPage(params);
        List<GoodsVO> gs = sysGoodsMapper.findAll(params);
        PageInfo<GoodsVO> pageInfo = new PageInfo(gs);
        return PageResult.<GoodsVO>builder().code(200).data(pageInfo.getList()).total(pageInfo.getTotal()).msg("获取物品列表成功！").build();
    }

    /**
     * 我的物品列表
     * @param params
     * @return
     */
    @Override
    public PageResult getGoodsListByUserId(Map params) {
        params.put("userId",tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getId());
        List<GoodsVO> vo =  sysGoodsMapper.getMyGoodsList(params);
        return PageResult.<GoodsVO>builder().code(200).msg("获取的的物品列表成功！").data(vo).build();
    }

    /**
     * 修改物品信息
     * @param goodsDTO
     * @return
     */
    @Override
    public Result updateGoods(GoodsDTO goodsDTO) {
        if (Objects.isNull(goodsDTO)){
            return Result.fail("提交数据不能为空！");
        }
        if (!Objects.isNull(goodsDTO.getImages()) && !goodsDTO.getImages().isEmpty()){
            //修改图片
            sysGoodsMapper.deleteImage(goodsDTO.getId());
            sysGoodsMapper.saveImages(goodsDTO);
        }
        int i = sysGoodsMapper.updateById(goodsDTO);
        if (i>0){
            return Result.success("修改成功！");
        }
        return Result.fail("修改失败！");
    }

    @Override
    public Result removeGoods(Integer id) {
        SysGoods goods = sysGoodsMapper.selectById(id);
        if (Objects.isNull(goods)){
            return Result.fail("物品不存在，删除失败！");
        }
        int i = sysGoodsMapper.deleteById(id);
        if (i>0){
            return Result.success("删除成功！");
        }
        return Result.fail("删除失败!");
    }

    @Override
    public Result getGoodsInfo(Integer id) {
        SysGoods sysGoods = sysGoodsMapper.selectById(id);
        if (Objects.isNull(sysGoods)){
            return Result.fail("物品不存在！");
        }

        return Result.success("获取物品详情成功！",sysGoods);
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
