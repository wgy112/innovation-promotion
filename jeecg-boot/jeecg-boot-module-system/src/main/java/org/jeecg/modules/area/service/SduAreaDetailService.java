package org.jeecg.modules.area.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.area.entity.SduAreaDetail;

import java.util.List;

public interface SduAreaDetailService extends IService<SduAreaDetail> {

    public List<SduAreaDetail> findDetailByPid (Integer pId);

}
