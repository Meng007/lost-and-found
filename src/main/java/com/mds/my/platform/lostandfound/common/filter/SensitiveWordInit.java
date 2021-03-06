package com.mds.my.platform.lostandfound.common.filter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mds.my.platform.lostandfound.common.constant.Constants;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysSensitiveWord;
import com.mds.my.platform.lostandfound.project.system.mapper.SysSensitiveWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @Project：test
 * @Author : chenming
 * @Date ： 2014年4月20日 下午2:27:06
 * @version 1.0
 */
public class SensitiveWordInit {
    private String ENCODING = "UTF-8";    //字符编码

    //调用数据库查询敏感词库
    @Autowired
    private SysSensitiveWordMapper sensitiveWordDao;
    //将查询的敏感词库存储到redis中
    @Autowired
    private RedisTemplate redisTemplate;
    @SuppressWarnings("rawtypes")
    public HashMap sensitiveWordMap;




    public SensitiveWordInit(){
        super();
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

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
     * 中 = {
     *      isEnd = 0
     *      国 = {<br>
     *      	 isEnd = 1
     *           人 = {isEnd = 0
     *                民 = {isEnd = 1}
     *                }
     *           男  = {
     *           	   isEnd = 0
     *           		人 = {
     *           			 isEnd = 1
     *           			}
     *           	}
     *           }
     *      }
     *  五 = {
     *      isEnd = 0
     *      星 = {
     *      	isEnd = 0
     *      	红 = {
     *              isEnd = 0
     *              旗 = {
     *                   isEnd = 1
     *                  }
     *              }
     *      	}
     *      }
     * @author chenming
     * @date 2014年4月20日 下午3:04:20
     * @param keyWordSet  敏感词库
     * @version 1.0
     */
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
            /*List<SensitiveWord> list  = redisTemplate.opsForList().range(Constant.SENSITIVE_WORD_CONSTANT, 0, 100000);
            for (SensitiveWord sensitiveWord : list) {
                System.out.println("存储redis："+sensitiveWord.getSensitiveWords());
            }
            if (list==null){*/
                //查询敏感词库
                List<SysSensitiveWord> sensitiveWordList = sensitiveWordDao.selectList(new LambdaQueryWrapper<>());
                //存入redis
                redisTemplate.opsForList().leftPushAll(Constants.SENSITIVE_WORD_CONSTANT,sensitiveWordList);
                for (SysSensitiveWord sensitiveWord : sensitiveWordList) {
                    set.add(sensitiveWord.getSensitiveWords());
                }
           /* }else {
                set = new HashSet<String>();
                for (SensitiveWord sensitiveWord : list) {
                    set.add(sensitiveWord.getSensitiveWords());
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
            return set;
    }
}
