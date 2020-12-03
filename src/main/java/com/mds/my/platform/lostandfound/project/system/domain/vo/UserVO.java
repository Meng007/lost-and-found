package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysUser;
import lombok.*;

import java.io.Serializable;

/**
 * @author 13557
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends SysUser implements Serializable {
    /**
     * 电话
     */
    private String phone;
    /**
     * 状态
     */
    private String statusName;
    /**
     * 管理员状态
     */
    private String typeName;
}
