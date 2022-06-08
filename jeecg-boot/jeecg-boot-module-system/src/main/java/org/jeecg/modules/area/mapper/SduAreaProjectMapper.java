package org.jeecg.modules.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.area.entity.SduAreaProject;

import java.util.List;

public interface SduAreaProjectMapper extends BaseMapper<SduAreaProject> {
    public List<SduAreaProject> findProjectListLike(@Param("keyword") String keyword,
                                                    @Param("startDate") String startDate,
                                                    @Param("endDate") String endDate,
                                                    @Param("checkedList") List<String> checkedList);

    List<String> findAllCompany();

    List<SduAreaProject> findCompanyListLike(@Param("keyword") String keyword,
                                             @Param("startDate") String startDate,
                                             @Param("endDate") String endDate,
                                             @Param("checkedList") List<String> checkedList);
}
