package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO extends SysUserInfo {
    SysUser user;
}
