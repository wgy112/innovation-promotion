package org.jeecg.modules.sdu.mapper;

/**
 * @description:
 * @projectName:jeecg-boot
 * @see:org.jeecg.modules.sdu.mapper
 * @author:SDU_LLC
 * @createTime:2022/3/25 10:59
 * @version:1.0
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduMessageNotice;
import java.util.List;

public interface SduMessageNoticeMapper extends BaseMapper<SduMessageNotice>{
    List<SduMessageNotice> messagePostByUser(@Param("demanduser") String demanduser);


//    Integer setReadState(@Param("id") Integer id);

    List<SduMessageNotice> messagePostByServiceUser(@Param("user") String user);
}
