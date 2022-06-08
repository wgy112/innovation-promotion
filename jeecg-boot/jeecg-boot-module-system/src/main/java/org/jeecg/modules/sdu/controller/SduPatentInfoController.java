package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduPatentInfo;
import org.jeecg.modules.sdu.service.SduPatentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sdu/patentInfo")
@Slf4j
public class SduPatentInfoController {
    @Autowired
    private SduPatentInfoService sduPatentInfoService;

    //    专利信息 详细信息
    @GetMapping(value = "/findByKeyword")
    public Result<List<SduPatentInfo>> findByKeyword(@RequestParam String keyword) {
        List<SduPatentInfo> res = sduPatentInfoService.findByKeyword(keyword);
        Result<List<SduPatentInfo>> result = new Result<List<SduPatentInfo>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
