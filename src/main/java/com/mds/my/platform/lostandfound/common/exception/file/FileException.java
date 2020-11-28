package com.mds.my.platform.lostandfound.common.exception.file;

import com.mds.my.platform.lostandfound.common.exception.BaseException;

/**
 *  文件异常
 * @author mds
 */
public class FileException extends BaseException {
    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }
}
