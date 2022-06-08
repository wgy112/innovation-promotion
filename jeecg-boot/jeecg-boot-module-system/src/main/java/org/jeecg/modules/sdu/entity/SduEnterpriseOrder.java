package org.jeecg.modules.sdu.entity;

/**
 * @description:
 * @projectName:jeecg-boot-parent
 * @see:org.jeecg.modules.sdu.entity
 * @author:SDU_LLC
 * @createTime:2022/4/10 10:58
 * @version:1.0
 */
import com.alipay.api.domain.PricingVO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sdu_enterprise_order")
public class SduEnterpriseOrder implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    //   方案id
    private Integer demandId;

//   需求企业的真正姓名
    private String duserRealname;

//
    private Integer solutionId;
//     方案作者的真正名字
    private  String suserRealname;

//      方案作者的联系方式
    private  String suserPhone;

    private Integer orderCount;

    private Integer priceSingle;

    private Integer priceAll;

    private Integer orderDelete;

    private Integer confirmState;

    private String demandPhone;

    private String demandAddress;

    private Date orderCreate;

    private Date orderSuccess;

    private String demandName;

    private String solutionName;

    private String duserName;

    private String suserName;

    private String solutionClassify;//    方案订单分类




}

