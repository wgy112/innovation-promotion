package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sdu_solution_demand")
public class SduSolutionDemand {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private Date date;

    private String user;

    private String solution;

    private Integer classify;

    private Integer demandid;

    private String applyeffect;

    private Integer status;

    private String companyname;
}
