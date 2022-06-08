package org.jeecg.modules.sdu.entity;

/**
 * @description:
 * @projectName:jeecg-boot
 * @see:org.jeecg.modules.sdu.entity
 * @author:SDU_LLC
 * @createTime:2022/3/25 10:23
 * @version:1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sdu_message_notice")
public class SduMessageNotice implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer messagetype;


//    private Integer id;

    //   方案的名字
    private String name;

    //   发布方案的企业或者创新工程师
    private String user;


    //   发布日期
    private Date date;

    //   需求名字
    private String demandname;

    //   发布需求的企业名字
    private String demanduser;

    //    阅读状态
    private Integer readstate;

    private Integer servicestate;

    private Integer orderstate;





}
