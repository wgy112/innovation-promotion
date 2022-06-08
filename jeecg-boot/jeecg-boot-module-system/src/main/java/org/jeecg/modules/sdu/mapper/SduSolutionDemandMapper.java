package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduSolutionDemand;

import java.util.List;

public interface SduSolutionDemandMapper extends BaseMapper<SduSolutionDemand> {
    int searchNumberById(Integer id);

    List<SduSolutionDemand> getSolutionByUser(String user);

    List<SduSolutionDemand> findSolutionById(@Param("demandid")Integer demandId);

    List<SduSolutionDemand> findSolutionByName(@Param("name") String name);

    List<SduSolutionDemand> findById(@Param("id")Integer id);

    List<SduSolutionDemand> findSolutionByStatus(Integer demandid);
}
