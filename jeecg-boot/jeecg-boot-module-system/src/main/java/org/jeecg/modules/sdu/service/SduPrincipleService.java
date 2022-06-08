package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduPrinciple;

import java.util.List;

public interface SduPrincipleService extends IService<SduPrinciple> {

    //      根据分类查询原理
    public List<SduPrinciple> findByCategory(Integer category);
    //传统版发明原理匹配
    public List<SduPrinciple> findPrincipleTraditional(String enhancePara,String aggravatePara);
}
