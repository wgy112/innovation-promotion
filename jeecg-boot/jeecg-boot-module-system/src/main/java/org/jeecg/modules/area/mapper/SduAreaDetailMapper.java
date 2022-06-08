package org.jeecg.modules.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.area.entity.SduAreaDetail;

import java.util.List;

public interface SduAreaDetailMapper extends BaseMapper<SduAreaDetail> {

    public List<SduAreaDetail> findDetailByPid (@Param("pid") Integer pId);

}
