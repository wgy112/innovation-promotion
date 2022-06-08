package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sdu_patenttitle")
public class SduPatenttitle implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String category;

    private Integer pid;
}
