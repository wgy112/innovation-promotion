package org.jeecg.modules.sdu.service.impl;
/**
 * @description:
 * @projectName:jeecg-boot
 * @see:org.jeecg.modules.sdu.service.impl
 * @author:SDU_LLC
 * @createTime:2022/3/25 10:53
 * @version:1.0
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.sdu.entity.SduMessageNotice;
import org.jeecg.modules.sdu.entity.SduSolutionDemand;
import org.jeecg.modules.sdu.mapper.SduMessageNoticeMapper;
import org.jeecg.modules.sdu.service.SduMessageNoticeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduMessageNoticeServiceImpl extends ServiceImpl<SduMessageNoticeMapper, SduMessageNotice>implements SduMessageNoticeService{
    @Resource
    private SduMessageNoticeMapper sduMessageNoticeMapper;

    @Override
    public Integer insert(SduMessageNotice sduMessageNotice) {
//        return sduMessageNoticeMapper.insert(sduMessageNotice);
        return sduMessageNoticeMapper.insert(sduMessageNotice);
    }

    @Override
    public List<SduMessageNotice> messagePostByUser(String demanduser) {
        return sduMessageNoticeMapper.messagePostByUser(demanduser);
    }

    @Override
    public Integer setReadState(SduMessageNotice sduMessageNotice) {
        return sduMessageNoticeMapper.updateById(sduMessageNotice);
    }
//     服务商查询消息
    @Override
    public List<SduMessageNotice> messagePostByServiceUser(String user) {
        return sduMessageNoticeMapper.messagePostByServiceUser(user);
    }

    @Override
    public Integer setServiceState(SduMessageNotice sduMessageNotice) {
        return sduMessageNoticeMapper.updateById(sduMessageNotice);
    }

}
