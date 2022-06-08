package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduScheme;
import org.jeecg.modules.sdu.mapper.SduSchemeMapper;
import org.jeecg.modules.sdu.service.SduSchemeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduSchemeServiceImpl extends ServiceImpl<SduSchemeMapper, SduScheme> implements SduSchemeService {

    @Resource
    private SduSchemeMapper sduSchemeMapper;

    @Override
    public Integer insert(SduScheme sduScheme) {
        return sduSchemeMapper.insert(sduScheme);
    }

    @Override
    public List<SduScheme> findAll() {
        return sduSchemeMapper.selectList(null);
    }

    @Override
    public List<SduScheme> findByUser(String user) {

        return sduSchemeMapper.findByUser(user) ;
    }

    @Override
    public int searchNumberById(Integer id) {
        return sduSchemeMapper.searchNumberById(id);
    }

}
