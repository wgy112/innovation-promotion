package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.sdu.entity.SduEnterpriseOrder;
import org.jeecg.modules.sdu.entity.SduMessageNotice;

import java.util.List;

/**
 * @description:
 * @projectName:jeecg-boot-parent
 * @see:org.jeecg.modules.sdu.mapper
 * @author:SDU_LLC
 * @createTime:2022/4/10 16:58
 * @version:1.0
 */
public interface SduEnterpriseOrderMapper extends BaseMapper<SduEnterpriseOrder> {

    List<SduEnterpriseOrder> queryEnterpriseOrderByUser(@Param("duserName") String duserName);

    List<SduEnterpriseOrder> queryOrderByServiceUser(@Param("suserName") String suserName);

    List<SduEnterpriseOrder> queryOrderByThreeName(@Param("suserName") String suserName, @Param("duserName") String duserName, @Param("solutionName") String solutionName);
}
