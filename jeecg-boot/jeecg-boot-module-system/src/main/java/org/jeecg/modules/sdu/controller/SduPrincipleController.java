package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduPrinciple;
import org.jeecg.modules.sdu.service.SduPrincipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sdu/principle")
@Slf4j
public class SduPrincipleController {

    @Autowired
    private SduPrincipleService sduPrincipleService;

//    通过分类查找原理
    @GetMapping(value = "/findByCategory")
    public Result<List<SduPrinciple>> findByCategory(@RequestParam Integer category) {
        List<SduPrinciple> res = sduPrincipleService.findByCategory(category);
        Result<List<SduPrinciple>> result = new Result<List<SduPrinciple>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
    //传统版发明原理匹配
    @GetMapping(value = "/findPrincipleTraditional")
    public Result<List<SduPrinciple>> findPrincipleTraditional(@RequestParam String enhancePara,@RequestParam String aggravatePara){
        List<SduPrinciple> res = sduPrincipleService.findPrincipleTraditional(enhancePara,aggravatePara);
        Result<List<SduPrinciple>> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
