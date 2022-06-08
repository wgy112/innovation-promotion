package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduProductData;
import org.jeecg.modules.sdu.entity.SduProductDataTree;
import org.jeecg.modules.sdu.mapper.SduProductDataMapper;
import org.jeecg.modules.sdu.service.SduProductDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class SduProductDataServiceImpl extends ServiceImpl<SduProductDataMapper, SduProductData> implements SduProductDataService {

    @Resource
    private SduProductDataMapper sduProductDataMapper;

    @Override
    public Integer findIdByName(String name) {
        return sduProductDataMapper.findIdByName(name);
    }

    @Override
    public List<SduProductData> findNext(String pid) {
        return sduProductDataMapper.findNext(pid);
    }

    @Override
    public List<SduProductDataTree> buildSduProductDataTree(List<SduProductData> sduProductDataList, Integer pid) {
        List<SduProductDataTree> SduProductDataTreeList = new ArrayList<>();
        sduProductDataList.forEach(sduProductData -> {
            SduProductDataTree sduProductDataTree = new SduProductDataTree();
            if (pid == sduProductData.getPid()) {
                sduProductDataTree.setName(sduProductData.getName());
                sduProductDataTree.setChildren(buildSduProductDataTree(sduProductDataList, sduProductData.getId()));
                SduProductDataTreeList.add(sduProductDataTree);
            }
        });
        return SduProductDataTreeList;
    }

    @Override
    public List<SduProductData> findByKeyword(String keyword) {
        return sduProductDataMapper.findByKeyword(keyword);
    }
}
