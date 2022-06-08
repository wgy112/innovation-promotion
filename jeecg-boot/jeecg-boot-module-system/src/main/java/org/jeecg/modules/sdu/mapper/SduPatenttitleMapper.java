package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduPatenttitle;

import java.util.List;

public interface SduPatenttitleMapper extends BaseMapper<SduPatenttitle> {

    public List<SduPatenttitle> findByPid(@Param("pid") Integer pid);

}
