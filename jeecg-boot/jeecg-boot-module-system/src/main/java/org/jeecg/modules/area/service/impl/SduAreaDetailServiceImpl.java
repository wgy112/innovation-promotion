package org.jeecg.modules.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.area.entity.SduAreaDetail;
import org.jeecg.modules.area.mapper.SduAreaDetailMapper;
import org.jeecg.modules.area.service.SduAreaDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduAreaDetailServiceImpl extends ServiceImpl<SduAreaDetailMapper, SduAreaDetail> implements SduAreaDetailService {

    @Resource
    private SduAreaDetailMapper sduAreaDetailMapper;

    @Override
    public List<SduAreaDetail> findDetailByPid(Integer pId) {
        return sduAreaDetailMapper.findDetailByPid(pId);
    }
}
