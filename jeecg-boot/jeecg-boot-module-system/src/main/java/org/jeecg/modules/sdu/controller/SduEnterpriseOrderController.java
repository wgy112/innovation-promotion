package org.jeecg.modules.sdu.controller;

/**
 * @description:
 * @projectName:jeecg-boot-parent
 * @see:org.jeecg.modules.sdu.controller
 * @author:SDU_LLC
 * @createTime:2022/4/10 16:42
 * @version:1.0
 */
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduCommentNotice;
import org.jeecg.modules.sdu.entity.SduCreative;
import org.jeecg.modules.sdu.entity.SduMessageNotice;
import org.jeecg.modules.sdu.entity.SduEnterpriseOrder;

import org.jeecg.modules.sdu.service.SduCommentNoticeService;
import org.jeecg.modules.sdu.service.SduEnterpriseOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sdu/enterpriseOrder")
@Slf4j

public class SduEnterpriseOrderController {
    @Autowired
    private SduEnterpriseOrderService sduEnterpriseOrderService;

    @PostMapping(value = "/add")
    public Result<SduEnterpriseOrder> add(@RequestBody SduEnterpriseOrder enterpriseOrder){

        Result<SduEnterpriseOrder> result = new Result<>();
        SduEnterpriseOrder sduEnterpriseOrder = new SduEnterpriseOrder();
        BeanUtils.copyProperties(enterpriseOrder, sduEnterpriseOrder);
        //System.out.println(sduCommentNotice);
        Integer res = sduEnterpriseOrderService.insert(sduEnterpriseOrder);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加失败");
        }
        return result;
    }
//      均根据企业需求用户账号 查询相应的订单
    @GetMapping(value="/queryEnterpriseOrderByUser")
    public Result<List<SduEnterpriseOrder>> findByUser(@RequestParam String duserName) {
        List<SduEnterpriseOrder> res = sduEnterpriseOrderService.queryEnterpriseOrderByUser(duserName);
        Result<List<SduEnterpriseOrder>> result = new Result<List<SduEnterpriseOrder>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

//     根据方案服务商用户username进行查找
    @GetMapping(value="/queryOrderByServiceUser")
    public Result<List<SduEnterpriseOrder>> queryOrderByServiceUser(@RequestParam String suserName) {
        List<SduEnterpriseOrder> res = sduEnterpriseOrderService.queryOrderByServiceUser(suserName);
        Result<List<SduEnterpriseOrder>> result = new Result<List<SduEnterpriseOrder>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }


    //   根据订单的id 进行  更新
    @PostMapping(value = "/updateByOrderId")
    public  Integer updateByOrderId(@RequestBody SduEnterpriseOrder sduEnterpriseOrder){
        Integer res = sduEnterpriseOrderService.deleteByOrderId(sduEnterpriseOrder);
        return res;
    }


//       根据duserName  和  solutionName 和 suserName进行查找特定的订单
    @GetMapping(value = "/queryOrderByThreeName")
    public Result<List<SduEnterpriseOrder>> queryOrderByThreeName(@RequestParam String suserName,@RequestParam String duserName, @RequestParam String solutionName) {
        List<SduEnterpriseOrder> res = sduEnterpriseOrderService.queryOrderByThreeName(suserName,duserName,solutionName);
        Result<List<SduEnterpriseOrder>> result = new Result<List<SduEnterpriseOrder>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
//      根据duserName  和  solutionName 和 suserName进行查找特定的订单，同时要求confirmstate 为 已确认

//    @GetMapping(value = "/queryOrderByThreeName")
//    public Result<List<SduEnterpriseOrder>> queryOrderByThreeName(@RequestParam String suserName,@RequestParam String duserName, @RequestParam String solutionName) {
//        List<SduEnterpriseOrder> res = sduEnterpriseOrderService.queryOrderByThreeName(suserName,duserName,solutionName);
//        Result<List<SduEnterpriseOrder>> result = new Result<List<SduEnterpriseOrder>>();
//        result.setResult(res);
//        result.setSuccess(true);
//        return result;
//    }



}
