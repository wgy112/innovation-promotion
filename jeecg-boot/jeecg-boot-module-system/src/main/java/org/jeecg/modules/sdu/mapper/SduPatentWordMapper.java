package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduPatentWord;

import java.util.List;

public interface SduPatentWordMapper extends BaseMapper<SduPatentWord> {

    public List<SduPatentWord> findByKeyword(@Param("keyword") String keyword);

}
