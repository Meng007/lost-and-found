package com.mds.my.platform.lostandfound.common.exception.user;

import com.mds.my.platform.lostandfound.common.exception.BaseException;

/**
 *  用户异常
 * @author sdz
 */
public class UserException extends BaseException {
    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
