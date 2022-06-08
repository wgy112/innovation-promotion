package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduPatentInfo;

import java.util.List;

public interface SduPatentInfoService extends IService<SduPatentInfo> {

    public List<SduPatentInfo> findByKeyword(String keyword);

}
