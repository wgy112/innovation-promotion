package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduPatentWord;

import java.util.List;

public interface SduPatentWordService extends IService<SduPatentWord> {

    public List<SduPatentWord> findByKeyword(String keyword);

}
