package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduSolution;
import org.jeecg.modules.sdu.entity.SduSolutionDemand;
import org.jeecg.modules.sdu.service.SduSolutionDemandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sdu/solutiondemand")
@Slf4j
public class SduSolutionDemandController {
    @Autowired
    private SduSolutionDemandService sduSolutionDemandService;

    @PostMapping(value = "/add")
    public Result<SduSolutionDemand> addSolution(@RequestBody SduSolutionDemand solution){
        Result<SduSolutionDemand> result = new Result<>();
        SduSolutionDemand sduSolutionDemand = new SduSolutionDemand();
        BeanUtils.copyProperties(solution, sduSolutionDemand);
        Integer res = sduSolutionDemandService.insert(sduSolutionDemand);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加评论失败");
        }
        return result;
    }

    @GetMapping(value = "/searchnumberbyid")
    public int searchNumberById(@RequestParam Integer id){
        int res = sduSolutionDemandService.searchNumberById(id);
        return res;
    }

    @GetMapping(value = "/getSolutionByUser")
    public Result<List<SduSolutionDemand>> getSolutionByUser(@RequestParam String user){
        List<SduSolutionDemand> res = sduSolutionDemandService.getSolutionByUser(user);
        Result<List<SduSolutionDemand>> result = new Result<>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    @PutMapping(value = "/editById")
    public int editById(@RequestBody SduSolutionDemand solution){
        int res = sduSolutionDemandService.editById(solution);
        return res;
    }

    @PutMapping(value = "/deleteById")
    public int deleteById(@RequestBody SduSolutionDemand solution){
        int res = sduSolutionDemandService.deleteById(solution);
        return res;
    }


    //   根据需求id 查找所有方案
    @GetMapping(value = "/findSolutionById")
    public Result<List<SduSolutionDemand>> findSolutionById(@RequestParam Integer demandId){
        List<SduSolutionDemand> res = sduSolutionDemandService.findSolutionById(demandId);
        Result<List<SduSolutionDemand>> result = new Result<List<SduSolutionDemand>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    //  根据方案名字查找所有方案
    @GetMapping(value = "/findSolutionByName")
    public Result<List<SduSolutionDemand>> findSolutionByName(@RequestParam String name){
        List<SduSolutionDemand> res = sduSolutionDemandService.findSolutionByName(name);
        Result<List<SduSolutionDemand>> result = new Result<List<SduSolutionDemand>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    //根据id查询方案
    @GetMapping(value = "/findById")
    public Result<List<SduSolutionDemand>> findById(@RequestParam Integer id){
        List<SduSolutionDemand> res = sduSolutionDemandService.findById(id);
        Result<List<SduSolutionDemand>> result = new Result<List<SduSolutionDemand>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    @GetMapping(value = "/findSolutionByStatus")
    public Result<List<SduSolutionDemand>> findSolutionByStatus(@RequestParam Integer demandid){
        List<SduSolutionDemand> res = sduSolutionDemandService.findSolutionByStatus(demandid);
        Result<List<SduSolutionDemand>> result = new Result<List<SduSolutionDemand>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
}
