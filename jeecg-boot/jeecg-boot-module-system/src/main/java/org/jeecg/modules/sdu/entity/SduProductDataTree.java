package org.jeecg.modules.sdu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "搜索信息树")
public class SduProductDataTree implements Serializable {

    @ApiModelProperty(value = "信息名称")
    private String name;

    private List<SduProductDataTree> children = new ArrayList<>();
}
