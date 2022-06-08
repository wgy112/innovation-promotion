package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduPatentInfo;

import java.util.List;

public interface SduPatentInfoMapper extends BaseMapper<SduPatentInfo> {

    public List<SduPatentInfo> findByKeyword(@Param("keyword") String keyword);

}
