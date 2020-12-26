package com.mds.my.platform.lostandfound.project.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String phone;
    private Integer sex;
    private String school;
    private String email;
    private String qq;
    private String weixin;
    private String dormitory;
    private String introduce;
    private String nickName;
    private String realName;
    private String confirmPassword;

    private String oldPassword;
}



