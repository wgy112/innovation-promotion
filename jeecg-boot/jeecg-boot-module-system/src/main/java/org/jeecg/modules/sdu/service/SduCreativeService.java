package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduCreative;

import java.util.List;
import java.util.Map;

public interface SduCreativeService extends IService<SduCreative> {

    public Integer insert(SduCreative sduCreative);

    //    查询所有创意
    public List<SduCreative> findAll();

    //    根据用户名查询所有创意
    public List<SduCreative> findByUser(String user,Integer status);

    public List<SduCreative> findByCid(Integer cid);

    List<SduCreative> findById(Integer id);

    Integer demandChangeById(SduCreative sduCreative);

    Integer demandDelete(Integer id);

    List<SduCreative> findBySingleUser(String user);

    List<Map<String, Object>> findLatestCreative();
}
