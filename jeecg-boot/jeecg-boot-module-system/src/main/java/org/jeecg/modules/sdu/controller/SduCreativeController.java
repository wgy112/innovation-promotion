package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduCreative;
import org.jeecg.modules.sdu.service.SduCreativeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/sdu/creative")
@Slf4j
public class SduCreativeController {

    @Autowired
    private SduCreativeService sduCreativeService;

    //    添加创意
    @PostMapping(value = "/add")
    public Result<SduCreative> addCreative(@RequestBody SduCreative creative) {
        Result<SduCreative> result = new Result<SduCreative>();
        SduCreative sduCreative = new SduCreative();
//        sduCreative.setName("黏胶长丝调湿工艺改进研究");
//        sduCreative.setDescription("在黏胶长丝调湿工艺过程中，潮湿空气从调湿间的一侧吹向另一侧，经过30米的距离后，空气湿度和绝对湿度明显下降，影响产品质量，需要改进工艺。");
//        sduCreative.setDate(null);
//        sduCreative.setStatus("已归档");
//        sduCreative.setUser("user");
        BeanUtils.copyProperties(creative, sduCreative);
        Integer res = sduCreativeService.insert(sduCreative);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加创意失败");
        }
        return result;
    }

    //    通过用户名查询本人的所有创意
    @GetMapping(value = "/findBySingleUser")
    public Result<List<SduCreative>> findBySingleUser(@RequestParam String user) {
        List<SduCreative> res = sduCreativeService.findBySingleUser(user);
        Result<List<SduCreative>> result = new Result<List<SduCreative>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
    //    通过用户名查询创意
    @GetMapping(value = "/findByUser")
    public Result<List<SduCreative>> findByUser(@RequestParam String user,@RequestParam Integer status) {
        List<SduCreative> res = sduCreativeService.findByUser(user,status);
        Result<List<SduCreative>> result = new Result<List<SduCreative>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    //    查询所有创意
    @GetMapping(value = "/findAll")
    public Result<List<SduCreative>> findAll() {
        List<SduCreative> res = sduCreativeService.findAll();
        Result<List<SduCreative>> result = new Result<List<SduCreative>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }
    @GetMapping(value = "/findByCid")
    public Result<List<SduCreative>> findByCid(@RequestParam Integer cid) {
        List<SduCreative> res = sduCreativeService.findByCid(cid);
        Result<List<SduCreative>> result = new Result<List<SduCreative>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    @GetMapping(value = "/findById")
    public Result<List<SduCreative>> findByName(@RequestParam Integer id) {
        List<SduCreative> res = sduCreativeService.findById(id);
        Result<List<SduCreative>> result = new Result<List<SduCreative>>();
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    //  根据id  修改需求
    @PostMapping(value = "/demandChange")
    public Result<SduCreative> demandChangeById(@RequestBody SduCreative creative) {
        Result<SduCreative> result = new Result<SduCreative>();
        SduCreative sduCreative = new SduCreative();
        BeanUtils.copyProperties(creative, sduCreative);
        Integer res = sduCreativeService.demandChangeById(sduCreative);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加创意失败");
        }
        return result;
    }

    //   设置需求删除状态
    @PostMapping(value="/demandDelete")
    public  Result<SduCreative> demandDelete(@RequestParam Integer id){
        Result<SduCreative> result = new Result<SduCreative>();
        SduCreative sduCreative = new SduCreative();
        BeanUtils.copyProperties(id, sduCreative);
        Integer res = sduCreativeService.demandDelete(id);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加创意失败");
        }
        return result;
    }

    @GetMapping(value = "/findLatestCreative")
    public List<Map<String, Object>> findLatestCreative(){
        List<Map<String, Object>> res = sduCreativeService.findLatestCreative();
        //Result<Map<String, Object>> result = new Result<>();
//        for (Map<String, Object> map : res) {
//            Set<String> set = map.keySet();
//            Iterator<String> it = set.iterator();
//            while (it.hasNext()) {
//                Object key = it.next();
//                Object value = map.get(key);
//                //System.out.println(value);
//            }
//        }
        //result.setResult((Map<String, Object>) res);
        //result.setSuccess(true);
        return res;
    }
}
