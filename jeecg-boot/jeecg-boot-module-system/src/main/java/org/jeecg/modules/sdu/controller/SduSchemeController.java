package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduScheme;
import org.jeecg.modules.sdu.service.SduSchemeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sdu/scheme")
@Slf4j
public class SduSchemeController {
    @Autowired
    private SduSchemeService sduSchemeService;

//    添加方案
    @PostMapping(value = "/add")
    public Result<SduScheme> addScheme(@RequestBody SduScheme scheme) {
        Result<SduScheme> result = new Result<SduScheme>();
//        SduScheme sduScheme = new SduScheme();
//        sduScheme.setName("纸硬币集成终端电源问题");
//        sduScheme.setCategory(6);
//        sduScheme.setDescription("由于各外设所需的工作电压不同......");
//        sduScheme.setDate(null);
//        sduScheme.setUser("user");
        SduScheme sduScheme = new SduScheme();
        BeanUtils.copyProperties(scheme, sduScheme);
        Integer res = sduSchemeService.insert(sduScheme);
        System.out.println(sduScheme);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加方案失败");
        }
        return result;
    }

//    通过用户名查询方案
    @GetMapping(value = "/findByUser")
    public Result<List<SduScheme>> findByUser(@RequestParam String user) {
        List<SduScheme> res = sduSchemeService.findByUser(user);
        Result<List<SduScheme>> result = new Result<List<SduScheme>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

//    查询所有方案
    @GetMapping(value = "/findAll")
    public Result<List<SduScheme>> findAll() {
        List<SduScheme> res = sduSchemeService.findAll();
        Result<List<SduScheme>> result = new Result<List<SduScheme>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

//    通过需求id查询创新方案数量
    @GetMapping(value = "/searchNumberById")
    public int searchNumberById(Integer id){
        int res = sduSchemeService.searchNumberById(id);
        return res;
    }
}
