package com.mds.my.platform.lostandfound.project.system.service;


import com.mds.my.platform.lostandfound.common.web.Result;

/**
 * @author 13557
 */
public interface SensitiveWordService {
    /**
     *  敏感词检测
     * @param screeningWord
     * @return
     */
    Result SensitiveWord(String screeningWord);
}
