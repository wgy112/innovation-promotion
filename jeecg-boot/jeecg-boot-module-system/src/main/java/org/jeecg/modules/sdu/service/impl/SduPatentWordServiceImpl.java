package org.jeecg.modules.sdu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduPatentWord;
import org.jeecg.modules.sdu.mapper.SduPatentWordMapper;
import org.jeecg.modules.sdu.service.SduPatentWordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduPatentWordServiceImpl extends ServiceImpl<SduPatentWordMapper, SduPatentWord> implements SduPatentWordService {

    @Resource
    private SduPatentWordMapper sduPatentWordMapper;

    @Override
    public List<SduPatentWord> findByKeyword(String keyword) {
        return sduPatentWordMapper.findByKeyword(keyword);
    }
}
