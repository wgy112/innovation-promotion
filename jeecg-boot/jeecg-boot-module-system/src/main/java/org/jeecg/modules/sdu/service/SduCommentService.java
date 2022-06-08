package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.jeecg.modules.sdu.entity.SduComment;

import java.util.List;

public interface SduCommentService extends IService<SduComment> {
    public Integer insert(SduComment sduComment);

    public List<SduComment> findAll(Integer id);
}
