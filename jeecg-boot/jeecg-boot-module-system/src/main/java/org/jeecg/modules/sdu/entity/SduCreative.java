package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sdu_creative")
public class SduCreative implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private Integer status;

    private String user;

    private Date date;

    private Integer classify;

    private Integer deletestate;

}
