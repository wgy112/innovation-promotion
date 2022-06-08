package org.jeecg.modules.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.area.entity.SduAreaD;
import org.jeecg.modules.area.mapper.SduAreaDMapper;
import org.jeecg.modules.area.service.SduAreaDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SduAreaDServiceImpl extends ServiceImpl<SduAreaDMapper, SduAreaD> implements SduAreaDService {
    @Resource
    private SduAreaDMapper sduAreaDMapper;

    @Override
    public SduAreaD findAreaDBydid(Integer dId) {
        return sduAreaDMapper.selectById(dId);
    }
}
