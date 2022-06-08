package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduPrinciple;
import org.jeecg.modules.sdu.mapper.SduPrincipleMapper;
import org.jeecg.modules.sdu.service.SduPrincipleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduPrincipleServiceImpl extends ServiceImpl<SduPrincipleMapper, SduPrinciple> implements SduPrincipleService {

    @Resource
    private SduPrincipleMapper sduPrincipleMapper;

    @Override
    public List<SduPrinciple> findByCategory(Integer category) {
        return sduPrincipleMapper.findByCategory(category);
    }

    @Override
    public List<SduPrinciple> findPrincipleTraditional(String enhancePara, String aggravatePara) {
        return sduPrincipleMapper.findPrincipleTraditional(enhancePara,aggravatePara);
    }
}
