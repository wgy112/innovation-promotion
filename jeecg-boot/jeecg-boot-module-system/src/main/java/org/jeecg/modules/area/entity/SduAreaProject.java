package org.jeecg.modules.area.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sdu_area_project")
public class SduAreaProject implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer pId;

    private Integer cId;

    private String pName;

    private String cName;

    private String pStatus;

    private String pYear;

    private String pMonth;

    private String pArea;

    private String pType;

    private Double pProvinceF;

    private Double pCityF;

    private Double pAreaF;

    private Double pComF;

    private String pStarttime;

    private String pEndtime;

    private String pCreatetime;
}
