package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduPatentWord;
import org.jeecg.modules.sdu.service.SduPatentWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sdu/patentWord")
@Slf4j
public class SduPatentWordController {
    @Autowired
    private SduPatentWordService sduPatentWordService;

    //    专利信息 详细信息
    @GetMapping(value = "/findByKeyword")
    public Result<List<SduPatentWord>> findByKeyword (@RequestParam String keyword){
        List<SduPatentWord> res = sduPatentWordService.findByKeyword(keyword);
        Result<List<SduPatentWord>> result = new Result<List<SduPatentWord>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
