package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduSolution;
import org.jeecg.modules.sdu.service.SduSolutionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sdu/solution")
@Slf4j
public class SduSolutionController {
    @Autowired
    private SduSolutionService sduSolutionService;

    @PostMapping(value = "/add")
    public Result<SduSolution> addSolution(@RequestBody SduSolution solution) {
        Result<SduSolution> result = new Result<>();
        SduSolution sduSolution = new SduSolution();
        BeanUtils.copyProperties(solution, sduSolution);
        Integer res = sduSolutionService.insert(sduSolution);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加失败");
        }
        return result;
    }
    @GetMapping(value = "/findById")
    public Result<SduSolution> findById(@RequestParam Integer id) {
        SduSolution res = sduSolutionService.findById(id);
        Result<SduSolution> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;

    }
    @GetMapping(value = "/findSolutionByClassify")
    public Result<List<SduSolution>> findSolutionByClassify(@RequestParam Integer classify) {
        List<SduSolution> res = sduSolutionService.findSolutionByClassify(classify);
        Result<List<SduSolution>> result = new Result<List<SduSolution>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;

    }

    @GetMapping(value = "/findSolutionByUser")
    public Result<List<SduSolution>> findSolutionByUser(@RequestParam String user) {
        List<SduSolution> res = sduSolutionService.findSolutionByUser(user);
        Result<List<SduSolution>> result = new Result<List<SduSolution>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;

    }

    @PutMapping(value = "/deleteById")
    public int deleteById(@RequestBody SduSolution solution){
        int res = sduSolutionService.deleteById(solution);
        return res;
    }

    @PutMapping(value = "/editById")
    public int editById(@RequestBody SduSolution solution){
        int res = sduSolutionService.editById(solution);
        return res;
    }

    @GetMapping(value = "/findSolutionListLike")
    public Result<List<SduSolution>> findSolutionListLike(@RequestParam String searchName,@RequestParam Integer classify){
        List<SduSolution> res = sduSolutionService.findSolutionListLike(searchName,classify);
        Result<List<SduSolution>> result = new Result<List<SduSolution>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
    @GetMapping(value = "/getLatestSolution")
    public Result<List<SduSolution>> getLatestSolution(){
        List<SduSolution> res = sduSolutionService.getLatestSolution();
        Result<List<SduSolution>> result = new Result<List<SduSolution>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
