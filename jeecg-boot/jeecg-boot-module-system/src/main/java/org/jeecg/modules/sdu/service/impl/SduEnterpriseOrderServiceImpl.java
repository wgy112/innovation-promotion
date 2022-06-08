package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;

import org.jeecg.modules.sdu.mapper.SduEnterpriseOrderMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import org.jeecg.modules.sdu.entity.SduEnterpriseOrder;
import org.jeecg.modules.sdu.service.SduEnterpriseOrderService;

/**
 * @description:
 * @projectName:jeecg-boot-parent
 * @see:org.jeecg.modules.sdu.service.impl
 * @author:SDU_LLC
 * @createTime:2022/4/10 16:53
 * @version:1.0
 */

@Slf4j
@Service
public class SduEnterpriseOrderServiceImpl extends ServiceImpl<SduEnterpriseOrderMapper,SduEnterpriseOrder>implements SduEnterpriseOrderService {

    @Resource
    private SduEnterpriseOrderMapper sduEnterpriseOrderMapper;

    @Override
    public Integer insert(SduEnterpriseOrder sduEnterpriseOrder) {
        return sduEnterpriseOrderMapper.insert(sduEnterpriseOrder);
    }

    @Override
    public List<SduEnterpriseOrder> queryEnterpriseOrderByUser(String duserName) {
        return sduEnterpriseOrderMapper.queryEnterpriseOrderByUser(duserName);
    }

    @Override
    public Integer deleteByOrderId(SduEnterpriseOrder sduEnterpriseOrder) {
        return sduEnterpriseOrderMapper.updateById(sduEnterpriseOrder);
    }

    @Override
    public List<SduEnterpriseOrder> queryOrderByServiceUser(String suserName) {
        return sduEnterpriseOrderMapper.queryOrderByServiceUser(suserName);
    }

    @Override
    public List<SduEnterpriseOrder> queryOrderByThreeName(String suserName, String duserName, String solutionName) {
        return sduEnterpriseOrderMapper.queryOrderByThreeName(suserName,duserName,solutionName);
    }


}
