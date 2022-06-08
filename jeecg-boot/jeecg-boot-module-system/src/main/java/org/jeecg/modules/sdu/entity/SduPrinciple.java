package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sdu_principle")
public class SduPrinciple implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String example;

    private Integer category;

}
