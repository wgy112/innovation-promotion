package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduSolutionDemand;

import java.util.List;

public interface SduSolutionDemandService extends IService<SduSolutionDemand> {
    public List<SduSolutionDemand> getSolutionByUser(String user);

    Integer insert(SduSolutionDemand sduSolutionDemand);

    int searchNumberById(Integer id);

    int editById( SduSolutionDemand solution);

    List<SduSolutionDemand> findSolutionById(Integer demandId);

    int deleteById(SduSolutionDemand solution);

    List<SduSolutionDemand> findSolutionByName(String name);

    List<SduSolutionDemand> findById(Integer id);

    List<SduSolutionDemand> findSolutionByStatus(Integer demandid);
}
