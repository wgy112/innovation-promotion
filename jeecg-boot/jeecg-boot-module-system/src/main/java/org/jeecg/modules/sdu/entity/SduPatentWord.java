package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sdu_patent_word")
public class SduPatentWord implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String word;

    private String keyword;
}
