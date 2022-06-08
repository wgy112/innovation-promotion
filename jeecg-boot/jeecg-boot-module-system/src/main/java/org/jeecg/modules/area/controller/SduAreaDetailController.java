package org.jeecg.modules.area.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.area.entity.SduAreaDetail;
import org.jeecg.modules.area.service.SduAreaDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area/detail")
@Slf4j
public class SduAreaDetailController {

    @Autowired
    private SduAreaDetailService sduAreaDetailService;

    @GetMapping(value = "/findDetailByPid")
    public Result<List<SduAreaDetail>> findDetailByPid(@RequestParam Integer pId){
        List<SduAreaDetail> res = sduAreaDetailService.findDetailByPid(pId);
        Result<List<SduAreaDetail>> result = new Result<List<SduAreaDetail>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }


}
