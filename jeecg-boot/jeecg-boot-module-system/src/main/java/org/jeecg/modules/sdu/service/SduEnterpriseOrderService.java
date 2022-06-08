package org.jeecg.modules.sdu.service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduEnterpriseOrder;
import org.jeecg.modules.sdu.entity.SduMessageNotice;

import java.util.List;
/**
 * @description:
 * @projectName:jeecg-boot-parent
 * @see:org.jeecg.modules.sdu.service
 * @author:SDU_LLC
 * @createTime:2022/4/10 16:49
 * @version:1.0
 */
public interface SduEnterpriseOrderService extends IService<SduEnterpriseOrder>{


    Integer insert(SduEnterpriseOrder sduEnterpriseOrder);



    List<SduEnterpriseOrder> queryEnterpriseOrderByUser(String duserName);

    Integer deleteByOrderId(SduEnterpriseOrder sduEnterpriseOrder);

    List<SduEnterpriseOrder> queryOrderByServiceUser(String suserName);

    List<SduEnterpriseOrder> queryOrderByThreeName(String suserName, String duserName, String solutionName);
}
