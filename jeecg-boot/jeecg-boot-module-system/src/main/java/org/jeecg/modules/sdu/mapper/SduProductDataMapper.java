package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduProductData;

import java.util.List;

public interface SduProductDataMapper extends BaseMapper<SduProductData> {

    public Integer findIdByName(@Param("name") String name);

    public List<SduProductData> findNext(@Param("pid") String pid);

    public List<SduProductData> findByKeyword(@Param("keyword") String keyword);

}
