package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduPatenttitle;
import org.jeecg.modules.sdu.service.SduPatenttitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sdu/patenttitle")
@Slf4j
public class SduPatenttitleController {
    @Autowired
    private SduPatenttitleService sduPatenttitleService;

//    通过原理id查找相应的专利
    @GetMapping(value = "/findByPid")
    public Result<List<SduPatenttitle>> findByPid(@RequestParam Integer pid) {
        List<SduPatenttitle> res = sduPatenttitleService.findByPid(pid);
        Result<List<SduPatenttitle>> result = new Result<List<SduPatenttitle>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
