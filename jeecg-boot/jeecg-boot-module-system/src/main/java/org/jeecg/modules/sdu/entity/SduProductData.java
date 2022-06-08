package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sdu_product_data")
public class SduProductData implements Serializable {

    private Integer id;

    private String name;

    //父节点
    private Integer pid;

    //关键字
    private String keyword;

}
