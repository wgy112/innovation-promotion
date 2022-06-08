package org.jeecg.modules.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.area.entity.SduAreaProject;

import java.util.Date;
import java.util.List;

public interface SduAreaProjectService extends IService<SduAreaProject> {

    //查所有
    public List<SduAreaProject> findAll();

    //查询
    public List<SduAreaProject> findProjectListLike(String keyword, String startDate, String endDate, List<String> checkedList);

    List<String> findAllCompany();

    List<SduAreaProject> findCompanyListLike(String keyword, String startDate, String endDate, List<String> checkedList);
}
