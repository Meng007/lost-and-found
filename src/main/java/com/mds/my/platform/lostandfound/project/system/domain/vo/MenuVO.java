package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVO extends SysMenu {
    private String nickName;
    List<MenuVO> children;
}
