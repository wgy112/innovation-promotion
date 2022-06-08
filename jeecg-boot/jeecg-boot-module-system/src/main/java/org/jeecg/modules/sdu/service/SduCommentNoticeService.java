package org.jeecg.modules.sdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduCommentNotice;

import java.util.List;

public interface SduCommentNoticeService extends IService<SduCommentNotice> {
    Integer insert(SduCommentNotice sduCommentNotice);

    List<SduCommentNotice> getNoticebyName(String name);

    int deleteById(SduCommentNotice sduCommentNotice);

    List<SduCommentNotice> demandGetNoticebyName(String name);
}
