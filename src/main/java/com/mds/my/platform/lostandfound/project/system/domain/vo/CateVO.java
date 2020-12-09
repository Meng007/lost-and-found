package com.mds.my.platform.lostandfound.project.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mds.my.platform.lostandfound.project.system.domain.entity.SysCate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CateVO extends SysCate {
    private String nickName;
    private List<CateVO> children;
}
