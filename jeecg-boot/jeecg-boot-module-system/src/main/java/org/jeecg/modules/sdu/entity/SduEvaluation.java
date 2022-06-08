package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sdu_evaluation")
public class SduEvaluation implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String text;

    private Integer score;

    private Integer sid;

    private Date date;

    private String user;

}
