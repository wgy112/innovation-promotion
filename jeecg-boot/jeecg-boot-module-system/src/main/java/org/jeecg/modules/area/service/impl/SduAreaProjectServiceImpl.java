package org.jeecg.modules.area.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.area.entity.SduAreaProject;
import org.jeecg.modules.area.mapper.SduAreaProjectMapper;
import org.jeecg.modules.area.service.SduAreaProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduAreaProjectServiceImpl extends ServiceImpl<SduAreaProjectMapper, SduAreaProject> implements SduAreaProjectService {

    @Resource
    private SduAreaProjectMapper sduAreaProjectMapper;

    @Override
    public List<SduAreaProject> findAll() {
        return sduAreaProjectMapper.selectList(null);
    }

    @Override
    public List<SduAreaProject> findProjectListLike(String keyword, String startDate, String endDate, List<String> checkedList) {
        return sduAreaProjectMapper.findProjectListLike(keyword, startDate, endDate, checkedList);
    }

    @Override
    public List<String> findAllCompany() {
        return sduAreaProjectMapper.findAllCompany();
    }

    @Override
    public List<SduAreaProject> findCompanyListLike(String keyword, String startDate, String endDate, List<String> checkedList) {
        return sduAreaProjectMapper.findCompanyListLike(keyword, startDate, endDate, checkedList);
    }
}
