package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduCommentNotice;

import java.util.List;

public interface SduCommentNoticeMapper extends BaseMapper<SduCommentNotice> {

    List<SduCommentNotice> getNoticebyName(@Param("solutionusername")String name);

    List<SduCommentNotice> demandGetNoticebyName(@Param("username")String name);
}
