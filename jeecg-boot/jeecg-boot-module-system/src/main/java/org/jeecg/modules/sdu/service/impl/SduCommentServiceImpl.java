package org.jeecg.modules.sdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.sdu.entity.SduComment;
import org.jeecg.modules.sdu.mapper.SduCommentMapper;
import org.jeecg.modules.sdu.service.SduCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SduCommentServiceImpl extends ServiceImpl<SduCommentMapper, SduComment> implements SduCommentService {

    @Resource
    private SduCommentMapper sduCommentMapper;

    @Override
    public Integer insert(SduComment sduComment) {
        return sduCommentMapper.insert(sduComment);
    }

    @Override
    public List<SduComment> findAll(Integer id) {
        return sduCommentMapper.selectListById(id);
    }
}
