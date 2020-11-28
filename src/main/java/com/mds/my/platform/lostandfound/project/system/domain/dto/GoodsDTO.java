package com.mds.my.platform.lostandfound.project.system.domain.dto;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoodsImage;
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
public class GoodsDTO extends SysGoods {
    List<SysGoodsImage> images;
}
