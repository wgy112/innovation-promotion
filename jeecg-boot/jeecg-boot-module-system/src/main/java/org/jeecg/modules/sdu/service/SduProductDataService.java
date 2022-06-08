package org.jeecg.modules.sdu.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import io.lettuce.core.dynamic.annotation.Param;
import org.jeecg.modules.sdu.entity.SduProductData;
import org.jeecg.modules.sdu.entity.SduProductDataTree;


import java.util.List;

public interface SduProductDataService extends IService<SduProductData> {

    public Integer findIdByName(String name);

    public List<SduProductData> findNext(String pid);

    public List<SduProductDataTree> buildSduProductDataTree(List<SduProductData> sduProductDataList, Integer pid);

    public List<SduProductData> findByKeyword(String keyword);
}
