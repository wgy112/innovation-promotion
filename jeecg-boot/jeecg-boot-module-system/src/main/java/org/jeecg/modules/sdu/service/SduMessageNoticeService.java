package org.jeecg.modules.sdu.service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.sdu.entity.SduMessageNotice;
import java.util.List;
/**
 * @description:
 * @projectName:jeecg-boot
 * @see:org.jeecg.modules.sdu.service
 * @author:SDU_LLC
 * @createTime:2022/3/25 10:44
 * @version:1.0
 */
public interface SduMessageNoticeService extends IService<SduMessageNotice> {
    public Integer insert(SduMessageNotice sduMessageNotice);

    List<SduMessageNotice> messagePostByUser(String demanduser);

    Integer setReadState(SduMessageNotice sduMessageNotice);

    List<SduMessageNotice> messagePostByServiceUser(String user);

    Integer setServiceState(SduMessageNotice sduMessageNotice);
}
