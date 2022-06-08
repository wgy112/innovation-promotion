package org.jeecg.modules.sdu.controller;


/**
 * @description:
 * @projectName:jeecg-boot
 * @see:org.jeecg.modules.sdu.controller
 * @author:SDU_LLC
 * @createTime:2022/3/25 10:30
 * @version:1.0
 */
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduCreative;
import org.jeecg.modules.sdu.entity.SduMessageNotice;
import org.jeecg.modules.sdu.service.SduMessageNoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sdu/messageNotice")
@Slf4j
public class SduMessageNoticeController {


    @Autowired
    private SduMessageNoticeService sduMessageNoticeService;

    @PostMapping(value = "/addMessageNotice")
    public Result<SduMessageNotice> addMessageNotice(@RequestBody SduMessageNotice messageNotice){
        Result<SduMessageNotice> result = new Result<>();
        SduMessageNotice sduMessageNotice = new SduMessageNotice();
        BeanUtils.copyProperties(messageNotice, sduMessageNotice);
        Integer res = sduMessageNoticeService.insert(sduMessageNotice);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("获取消息失败");
        }
        return result;
    }

    //    通过企业用户名发送消息
    @GetMapping(value = "/messagePostByUser")
    public Result<List<SduMessageNotice>> messagePostByUser(@RequestParam String demanduser) {
        List<SduMessageNotice> res = sduMessageNoticeService.messagePostByUser(demanduser);
        Result<List<SduMessageNotice>> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
//       通过设置企业用户阅读状态
    @PostMapping(value = "/setReadState")
    public Result<SduMessageNotice>setReadState(@RequestBody SduMessageNotice messageNotice){
        Result<SduMessageNotice> result = new Result<SduMessageNotice>();
        SduMessageNotice sduMessageNotice = new SduMessageNotice();
        BeanUtils.copyProperties(messageNotice, sduMessageNotice);
        Integer res = sduMessageNoticeService.setReadState(sduMessageNotice);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("获取消息失败");
        }
        return result;
    }

    //    通过服务商用户名发送消息
    @GetMapping(value = "/messagePostByServiceUser")
    public Result<List<SduMessageNotice>> messagePostByServiceUser(@RequestParam String user) {
        List<SduMessageNotice> res = sduMessageNoticeService.messagePostByServiceUser(user);
        Result<List<SduMessageNotice>> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    //       通过设置企业用户阅读状态
    @PostMapping(value = "/setServiceState")
    public Result<SduMessageNotice>setServiceState(@RequestBody SduMessageNotice messageNotice){
        Result<SduMessageNotice> result = new Result<SduMessageNotice>();
        SduMessageNotice sduMessageNotice = new SduMessageNotice();
        BeanUtils.copyProperties(messageNotice, sduMessageNotice);
        Integer res = sduMessageNoticeService.setServiceState(sduMessageNotice);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("获取消息失败");
        }
        return result;
    }
}
