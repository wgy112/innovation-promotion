package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduCommentNotice;
import org.jeecg.modules.sdu.mapper.SduCommentNoticeMapper;
import org.jeecg.modules.sdu.service.SduCommentNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduCommentNoticeServiceImpl extends ServiceImpl<SduCommentNoticeMapper, SduCommentNotice> implements SduCommentNoticeService {

    @Resource
    private SduCommentNoticeMapper sduCommentNoticeMapper;
    @Override
    public Integer insert(SduCommentNotice sduCommentNotice) {
        return sduCommentNoticeMapper.insert(sduCommentNotice);
    }

    @Override
    public List<SduCommentNotice> getNoticebyName(String name) {
        return sduCommentNoticeMapper.getNoticebyName(name);
    }

    @Override
    public int deleteById(SduCommentNotice sduCommentNotice) {
        return sduCommentNoticeMapper.updateById(sduCommentNotice);
    }

    @Override
    public List<SduCommentNotice> demandGetNoticebyName(String name) {
        return sduCommentNoticeMapper.demandGetNoticebyName(name);
    }
}
