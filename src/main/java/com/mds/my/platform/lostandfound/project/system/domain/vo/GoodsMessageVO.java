package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.GoodsMessage;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author 13557
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsMessageVO extends GoodsMessage implements Serializable {
    private String avatar;
    private String nickName;
    List<GoodsMessageVO> ch;

}
