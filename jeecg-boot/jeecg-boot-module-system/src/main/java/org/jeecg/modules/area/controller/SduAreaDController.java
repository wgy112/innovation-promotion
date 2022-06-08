package org.jeecg.modules.area.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.area.entity.SduAreaD;
import org.jeecg.modules.area.service.SduAreaDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/area/d")
@Slf4j
public class SduAreaDController {
    @Autowired
    private SduAreaDService sduAreaDService;
    @GetMapping(value = "/findAreaDetailBydid")
    public Result<SduAreaD> findAreaDBydid(@RequestParam Integer dId){

        SduAreaD res = sduAreaDService.findAreaDBydid(dId);
        Result<SduAreaD> result = new Result<SduAreaD>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
