package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.models.auth.In;
import org.jeecg.modules.sdu.entity.SduSolution;

import java.util.List;

public interface SduSolutionService extends IService<SduSolution> {
    public Integer insert(SduSolution sduSolution);
    public List<SduSolution> findSolutionByClassify(Integer classify);

    List<SduSolution> findSolutionByUser(String user);

    int editById(SduSolution solution);

    int deleteById(SduSolution solution);

    List<SduSolution> findSolutionListLike(String searchName, Integer classify);

    List<SduSolution> getLatestSolution();

    SduSolution findById(Integer id);
}
