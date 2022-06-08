package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.sdu.entity.SduSolutionDemand;
import org.jeecg.modules.sdu.mapper.SduSolutionDemandMapper;
import org.jeecg.modules.sdu.mapper.SduSolutionMapper;
import org.jeecg.modules.sdu.service.SduSolutionDemandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduSolutionDemandServiceImpl extends ServiceImpl<SduSolutionDemandMapper,SduSolutionDemand> implements SduSolutionDemandService{
    @Resource
    private SduSolutionDemandMapper sduSolutionDemandMapper;

    @Override
    public List<SduSolutionDemand> getSolutionByUser(String user) {
        return sduSolutionDemandMapper.getSolutionByUser(user);
    }

    @Override
    public Integer insert(SduSolutionDemand sduSolutionDemand) {
        return sduSolutionDemandMapper.insert(sduSolutionDemand);
    }

    @Override
    public int searchNumberById(Integer id) {
        return sduSolutionDemandMapper.searchNumberById(id);
    }

    @Override
    public int editById(SduSolutionDemand solution) {
        return sduSolutionDemandMapper.updateById(solution);
    }

    @Override
    public List<SduSolutionDemand> findSolutionById(Integer demandId) {
        return sduSolutionDemandMapper.findSolutionById(demandId);
    }

    @Override
    public int deleteById(SduSolutionDemand solution) {
        return sduSolutionDemandMapper.updateById(solution);
    }

    @Override
    public List<SduSolutionDemand> findSolutionByName(String name) {
        return sduSolutionDemandMapper.findSolutionByName(name);
    }

    @Override
    public List<SduSolutionDemand> findById(Integer id) {
        return sduSolutionDemandMapper.findById(id);
    }
    @Override
    public List<SduSolutionDemand> findSolutionByStatus(Integer demandid) {
        return sduSolutionDemandMapper.findSolutionByStatus(demandid);
    }
}
