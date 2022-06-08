package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduPatenttitle;
import org.jeecg.modules.sdu.mapper.SduPatenttitleMapper;
import org.jeecg.modules.sdu.service.SduPatenttitleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduPatenttitleServiceImpl extends ServiceImpl<SduPatenttitleMapper, SduPatenttitle> implements SduPatenttitleService {

    @Resource
    private SduPatenttitleMapper sduPatenttitleMapper;

    @Override
    public List<SduPatenttitle> findByPid(Integer pid) {
        return sduPatenttitleMapper.findByPid(pid);
    }
}
