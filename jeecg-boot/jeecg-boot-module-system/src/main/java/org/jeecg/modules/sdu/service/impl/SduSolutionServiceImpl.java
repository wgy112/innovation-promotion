package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduSolution;
import org.jeecg.modules.sdu.mapper.SduSolutionMapper;
import org.jeecg.modules.sdu.service.SduSolutionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduSolutionServiceImpl extends ServiceImpl<SduSolutionMapper, SduSolution> implements SduSolutionService {
    @Resource
    private SduSolutionMapper sduSolutionMapper;
    @Override
    public Integer insert(SduSolution sduSolution) {
        return sduSolutionMapper.insert(sduSolution);
    }

    @Override
    public List<SduSolution> findSolutionByClassify(Integer classify) {
        return sduSolutionMapper.findSolutionByClassify(classify);
    }

    @Override
    public List<SduSolution> findSolutionByUser(String user) {
        return sduSolutionMapper.findSolutionByUser(user);
    }

    @Override
    public int editById(SduSolution solution) {
        return sduSolutionMapper.updateById(solution);
    }

    @Override
    public int deleteById(SduSolution solution) {
        return sduSolutionMapper.updateById(solution);
    }

    @Override
    public List<SduSolution> findSolutionListLike(String searchName,Integer classify) {
        return sduSolutionMapper.findSolutionListLike(searchName,classify);
    }

    @Override
    public List<SduSolution> getLatestSolution() {
        return sduSolutionMapper.getLatestSolution();
    }

    @Override
    public SduSolution findById(Integer id) {
        return sduSolutionMapper.selectById(id);
    }
}
