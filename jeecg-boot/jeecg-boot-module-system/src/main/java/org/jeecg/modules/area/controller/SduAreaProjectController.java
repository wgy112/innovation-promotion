package org.jeecg.modules.area.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.area.entity.SduAreaProject;
import org.jeecg.modules.area.entity.QueryParam;
import org.jeecg.modules.area.service.SduAreaProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/area/project")
@Slf4j
public class SduAreaProjectController {

    @Autowired
    private SduAreaProjectService sduAreaProjectService;

    //查所有
    @GetMapping(value = "/findAllProject")
    public Result<List<SduAreaProject>> findAllProject() {
        List<SduAreaProject> res = sduAreaProjectService.findAll();
        Result<List<SduAreaProject>> result = new Result<List<SduAreaProject>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
    //查询所有企业名
    @GetMapping(value="/findAllCompany")
    public List<String> findAllCompany(){
        List<String> res = sduAreaProjectService.findAllCompany();
//        Result<List<String>> result = new Result<List<String>>();
//        result.setResult(res);
//        result.setSuccess(true);
        return res;
    }
    //查询
    @PostMapping(value = "/findProjectListLike")
    public Result<List<SduAreaProject>> findProjectListLike(@RequestBody QueryParam queryParam) {
        List<SduAreaProject> res = sduAreaProjectService.findProjectListLike(
                queryParam.getKeyword(),
                queryParam.getStartDate(),
                queryParam.getEndDate(),
                queryParam.getCheckedList());
        Result<List<SduAreaProject>> result = new Result<List<SduAreaProject>>();
        result.setResult(res);
        result.setSuccess(true);
       return result;
    }

    @PostMapping(value = "/findCompanyListLike")
    public Result<List<SduAreaProject>> findCompanyListLike(@RequestBody QueryParam queryParam) {
        List<SduAreaProject> res = sduAreaProjectService.findCompanyListLike(
                queryParam.getKeyword(),
                queryParam.getStartDate(),
                queryParam.getEndDate(),
                queryParam.getCheckedList());
        Result<List<SduAreaProject>> result = new Result<List<SduAreaProject>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }


}
