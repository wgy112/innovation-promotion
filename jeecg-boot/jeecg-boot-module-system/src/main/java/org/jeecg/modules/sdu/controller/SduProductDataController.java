package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduProductDataTree;
import org.jeecg.modules.sdu.service.SduProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sdu/productdata")
@Slf4j
public class SduProductDataController {

    @Autowired
    private SduProductDataService sduProductDataService;

    @GetMapping(value = "/findIdByName")
    public Result<Integer> findIdByName(@RequestParam String name) {
        Integer res = sduProductDataService.findIdByName(name);
        Result<Integer> result = new Result<Integer>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    @GetMapping(value = "/getTree")
    public Result<List<SduProductDataTree>> getTree(@RequestParam String keyword) {
        List<SduProductDataTree> res = sduProductDataService.buildSduProductDataTree(sduProductDataService.findByKeyword(keyword), 0);
        Result<List<SduProductDataTree>> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
