package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduPatentInfo;
import org.jeecg.modules.sdu.mapper.SduPatentInfoMapper;
import org.jeecg.modules.sdu.service.SduPatentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduPatentInfoServiceImpl extends ServiceImpl<SduPatentInfoMapper, SduPatentInfo> implements SduPatentInfoService {

    @Resource
    private SduPatentInfoMapper sduPatentInfoMapper;

    @Override
    public List<SduPatentInfo> findByKeyword(String keyword) {
        return sduPatentInfoMapper.findByKeyword(keyword);
    }
}
