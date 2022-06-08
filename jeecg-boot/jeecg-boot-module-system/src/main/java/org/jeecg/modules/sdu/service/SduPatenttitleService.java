package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduPatenttitle;

import java.util.List;

public interface SduPatenttitleService extends IService<SduPatenttitle> {

    public List<SduPatenttitle> findByPid(Integer pid);

}
