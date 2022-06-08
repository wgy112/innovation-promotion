package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduScheme;

import java.util.List;

public interface SduSchemeMapper extends BaseMapper<SduScheme> {

    public List<SduScheme> findByUser(@Param("user") String user);

    int searchNumberById(Integer id);
}
