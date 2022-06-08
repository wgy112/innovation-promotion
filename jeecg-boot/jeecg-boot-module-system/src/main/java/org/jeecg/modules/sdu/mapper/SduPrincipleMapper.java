package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduPrinciple;

import java.util.List;

public interface SduPrincipleMapper extends BaseMapper<SduPrinciple> {

    public List<SduPrinciple> findByCategory(@Param("category") Integer category);

    public List<SduPrinciple> findPrincipleTraditional(@Param("enhancePara")String enhancePara,@Param("aggravatePara")String aggravatePara);

}
