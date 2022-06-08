package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduCommentNotice;
import org.jeecg.modules.sdu.entity.SduMessageNotice;
import org.jeecg.modules.sdu.entity.SduSolutionDemand;
import org.jeecg.modules.sdu.service.SduCommentNoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sdu/commentnotice")
@Slf4j
public class SduCommentNoticeController {
    @Autowired
    private SduCommentNoticeService sduCommentNoticeService;

    @PostMapping(value = "/add")
    public Result<SduCommentNotice> add(@RequestBody SduCommentNotice commentNotice){

        Result<SduCommentNotice> result = new Result<>();
        SduCommentNotice sduCommentNotice = new SduCommentNotice();
        BeanUtils.copyProperties(commentNotice, sduCommentNotice);
        //System.out.println(sduCommentNotice);
        Integer res = sduCommentNoticeService.insert(sduCommentNotice);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加失败");
        }
        return result;
    }

    @GetMapping(value = "/getNoticebyName")
    public Result<List<SduCommentNotice>> getNoticebyName(@RequestParam String name) {
        List<SduCommentNotice> res = sduCommentNoticeService.getNoticebyName(name);
        Result<List<SduCommentNotice>> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    @GetMapping(value = "/demandGetNoticebyName")
    public Result<List<SduCommentNotice>> demandGetNoticebyName(@RequestParam String name) {
        List<SduCommentNotice> res = sduCommentNoticeService.demandGetNoticebyName(name);
        Result<List<SduCommentNotice>> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
    @PutMapping(value = "/deleteById")
    public int deleteById(@RequestBody SduCommentNotice sduCommentNotice){
        int res = sduCommentNoticeService.deleteById(sduCommentNotice);
        return res;
    }
}
