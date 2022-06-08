package org.jeecg.modules.area.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sdu_area_detail")
public class SduAreaD implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer dId;
    private Integer pId;

    private String pName;

    private String cName;
    private String dStatus;
    private String dProgress;
    private String dTijiaotime;

    private String dYear;
    private String dMonth;
    private String dTarget;

    private String dProgressSupplement;

    private String dBuild;
    private String dPeople;
    private String dContacts;
}
