package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoods;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysGoodsImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 13557
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO extends SysGoods implements Serializable {
    private String nickName;
    private String avatar;
    private List<SysGoodsImage> images;

}
