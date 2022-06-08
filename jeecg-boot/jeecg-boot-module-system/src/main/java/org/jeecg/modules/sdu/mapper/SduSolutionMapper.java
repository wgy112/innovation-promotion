package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduSolution;

import java.util.List;

public interface SduSolutionMapper extends BaseMapper<SduSolution> {
    public List<SduSolution> findSolutionByClassify(@Param("classify") Integer classify);

    List<SduSolution> findSolutionByUser(@Param("user") String user);

    List<SduSolution> findSolutionListLike(@Param("searchName") String searchName,@Param("classify") Integer classify);

    List<SduSolution> getLatestSolution();
}
