package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 13557
 */
@Data
public class SysMenuVO extends SysMenu implements Serializable {
    List<SysMenuVO> children;
}
