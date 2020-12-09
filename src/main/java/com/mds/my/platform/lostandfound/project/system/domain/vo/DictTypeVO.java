package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.mds.my.platform.lostandfound.project.system.domain.entity.SysDictType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictTypeVO extends SysDictType {
    private String statusName;
    private String nickName;
}
