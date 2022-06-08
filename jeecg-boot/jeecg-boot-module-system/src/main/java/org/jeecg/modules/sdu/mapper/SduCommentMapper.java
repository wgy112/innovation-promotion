package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduComment;

import java.util.List;

public interface SduCommentMapper extends BaseMapper<SduComment> {
    public List<SduComment> selectListById(@Param("schemeid") Integer id);
}
