package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sdu_solution_no")
public class SduSolution {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String user;//登录账号

    private String name;//方案名称

    private String description;

    private Date date;

    private String companyname;

    private String solution;

    private Integer classify;

    private String companyprofile;

    private String applyeffect;

    private Integer status;
}
