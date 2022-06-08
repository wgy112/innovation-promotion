package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduCreative;
import org.jeecg.modules.sdu.mapper.SduCreativeMapper;
import org.jeecg.modules.sdu.service.SduCreativeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SduCreativeServiceImpl extends ServiceImpl<SduCreativeMapper, SduCreative> implements SduCreativeService {

    @Resource
    private SduCreativeMapper sduCreativeMapper;

    @Override
    public Integer insert(SduCreative sduCreative) {
        return sduCreativeMapper.insert(sduCreative);
    }

    @Override
    public List<SduCreative> findAll() {
        return sduCreativeMapper.selectList(null);
    }

    @Override
    public List<SduCreative> findByUser(String user,Integer status) {
        return sduCreativeMapper.findByUser(user,status);
    }

    @Override
    public List<SduCreative> findByCid(Integer cid) {
        return sduCreativeMapper.findByCid(cid);
    }

    @Override
    public List<SduCreative> findById(Integer id) {
        return sduCreativeMapper.findById(id);
    }

    @Override
    public Integer demandChangeById(SduCreative sduCreative) {
        return sduCreativeMapper.updateById(sduCreative);
    }

    @Override
    public Integer demandDelete(Integer id) {
        return sduCreativeMapper.demandDelete(id);
    }

    @Override
    public List<SduCreative> findBySingleUser(String user) {
        return sduCreativeMapper.findBySingleUser(user);
    }

    @Override
    public List<Map<String, Object>> findLatestCreative() {
        return sduCreativeMapper.findLatestCreative();
    }
}
