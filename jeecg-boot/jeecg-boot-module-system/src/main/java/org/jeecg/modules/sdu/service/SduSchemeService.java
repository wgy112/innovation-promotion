package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduScheme;

import java.util.List;

public interface SduSchemeService extends IService<SduScheme> {

    public Integer insert(SduScheme sduScheme);

    //    查询所有方案
    public List<SduScheme> findAll();

    //    根据用户名查询所有方案
    public List<SduScheme> findByUser(String user);

    int searchNumberById(Integer id);
}
