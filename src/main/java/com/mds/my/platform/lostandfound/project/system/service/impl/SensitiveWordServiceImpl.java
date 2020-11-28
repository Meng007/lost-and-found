package com.mds.my.platform.lostandfound.project.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mds.my.platform.lostandfound.common.constant.Constants;
import com.mds.my.platform.lostandfound.common.filter.SensitiveWordFilter;
import com.mds.my.platform.lostandfound.common.web.Result;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysSensitiveWord;
import com.mds.my.platform.lostandfound.project.system.mapper.SysSensitiveWordMapper;
import com.mds.my.platform.lostandfound.project.system.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@SuppressWarnings("all")
public class SensitiveWordServiceImpl implements SensitiveWordService {
    //调用数据库查询敏感词库
    @Autowired(required = false)
    private SysSensitiveWordMapper sysSensitiveWordMapper;
    //将查询的敏感词库存储到redis中
    @Autowired
    private RedisTemplate redisTemplate;

    @SuppressWarnings("rawtypes")
    public Map sensitiveWordMap;
    private String ENCODING = "UTF-8";    //字符编码
    public static int minMatchTYpe = 1;      //最小匹配规则
    public static int maxMatchType = 2;      //最大匹配规则


    /*
    * 敏感词过滤
    * */
    @Override
    public Result SensitiveWord(String screeningWord) {
        Set<String> set = null;
        String text = "";
        Map map = new HashMap();
        try {
            sensitiveWordMap = this.initKeyWord();
            //
            set = getSensitiveWord(screeningWord, 1);
            System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
             text = replaceSensitiveWord(screeningWord, 1, "*");
             map.put("text",text);
             map.put("size",set.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("敏感词筛选成功",map);
    }



    /**
     * @author chenming
     * @date 2014年4月20日 下午2:28:32
     * @version 1.0
     */
    @SuppressWarnings("rawtypes")
    public Map initKeyWord(){
        try {
            //读取敏感词库
            Set<String> keyWordSet = readSensitiveWordFile();
            //将敏感词库加入到HashMap中
            addSensitiveWordToHashMap(keyWordSet);
            //spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();    //关键字
            nowMap = sensitiveWordMap;
            //遍历每个词语
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                }
                else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    /**
     * 读取敏感词库中的内容，将内容添加到set集合中
     * @author chenming
     * @date 2014年4月20日 下午2:31:18
     * @return
     * @version 1.0
     * @throws Exception
     */
    @SuppressWarnings("resource")
    private Set<String> readSensitiveWordFile() throws Exception {
        Set<String> set = null;
        try {
            //从redis中读取敏感词库
            System.out.println("开始初始化存储redis");
            //List<SensitiveWord> list = (List<SensitiveWord>) redisTemplate.opsForList().leftPop(Constant.SENSITIVE_WORD_CONSTANT);
            //redisTemplate.opsForList().range(Constant.SENSITIVE_WORD_CONSTANT,0,1);
            List<SysSensitiveWord> list  = redisTemplate.opsForList().range(Constants.SENSITIVE_WORD_CONSTANT, 0, -1);
            if (list==null || list.size() == 0){
            //查询敏感词库
            List<SysSensitiveWord> sensitiveWordList = sysSensitiveWordMapper.selectList(new LambdaQueryWrapper<>());
            //存入redis
            redisTemplate.opsForList().leftPushAll(Constants.SENSITIVE_WORD_CONSTANT,sensitiveWordList);
            for (SysSensitiveWord sensitiveWord : sensitiveWordList) {
                set.add(sensitiveWord.getSensitiveWords());
            }
            }else {
                set = new HashSet<String>();
                for (SysSensitiveWord sensitiveWord : list) {
                    set.add(sensitiveWord.getSensitiveWords());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }


    /**
     *
     * @param txt
     * @param matchType
     * @return
     */
    public boolean isContaintSensitiveWord(String txt,int matchType){
        boolean flag = false;
        for(int i = 0 ; i < txt.length() ; i++){
            int matchFlag = this.CheckSensitiveWord(txt, i, matchType); //判断是否包含敏感字符
            if(matchFlag > 0){    //大于0存在，返回true
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取文字中的敏感词
     * @author chenming
     * @date 2014年4月20日 下午5:10:52
     * @param txt 文字
     * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return
     * @version 1.0
     */
    public Set<String> getSensitiveWord(String txt , int matchType){
        Set<String> sensitiveWordList = new HashSet<String>();

        for(int i = 0 ; i < txt.length() ; i++){
            int length = CheckSensitiveWord(txt, i, matchType);    //判断是否包含敏感字符
            if(length > 0){    //存在,加入list中
                sensitiveWordList.add(txt.substring(i, i+length));
                i = i + length - 1;    //减1的原因，是因为for会自增
            }
        }

        return sensitiveWordList;
    }

    /**
     * 替换敏感字字符
     * @author chenming
     * @date 2014年4月20日 下午5:12:07
     * @param txt
     * @param matchType
     * @param replaceChar 替换字符，默认*
     * @version 1.0
     */
    public String replaceSensitiveWord(String txt,int matchType,String replaceChar){
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);     //获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        return resultTxt;
    }

    /**
     * 获取替换字符串
     * @author chenming
     * @date 2014年4月20日 下午5:21:19
     * @param replaceChar
     * @param length
     * @return
     * @version 1.0
     */
    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     * @author chenming
     * @date 2014年4月20日 下午4:31:03
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     * @version 1.0
     */
    @SuppressWarnings({ "rawtypes"})
    public int CheckSensitiveWord(String txt,int beginIndex,int matchType){
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        int matchFlag = 0;     //匹配标识数默认为0
        char word = 0;
        Map nowMap = sensitiveWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;     //找到相应key，匹配标识+1
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                    if(SensitiveWordFilter.minMatchTYpe == matchType){    //最小规则，直接返回,最大规则还需继续查找
                        break;
                    }
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }
}
