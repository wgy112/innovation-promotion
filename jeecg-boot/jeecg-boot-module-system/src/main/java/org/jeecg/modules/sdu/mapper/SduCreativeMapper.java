package org.jeecg.modules.sdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.sdu.entity.SduCreative;

import java.util.List;
import java.util.Map;

public interface SduCreativeMapper extends BaseMapper<SduCreative> {

    public List<SduCreative> findByUser(@Param("user") String user,@Param("status") Integer status);

    List<SduCreative> findByCid(@Param("classify") Integer cid);

    List<SduCreative> findById(@Param("id")Integer id);

    Integer demandDelete(@Param("id") Integer id);

    List<SduCreative> findBySingleUser(@Param("user") String user);
    @Select("select a.*,b.realname from (select * from sdu_creative where deletestate = 0 order by date desc limit 6 ) a left join (select username,realname from sys_user) b on a.user = b.username")
    List<Map<String,Object>> findLatestCreative();
}
