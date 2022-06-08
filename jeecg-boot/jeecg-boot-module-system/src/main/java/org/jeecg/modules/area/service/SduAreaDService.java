package org.jeecg.modules.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.area.entity.SduAreaD;

public interface SduAreaDService extends IService<SduAreaD> {
    SduAreaD findAreaDBydid(Integer dId);
}
