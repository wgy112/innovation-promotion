package org.jeecg.modules.sdu.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.sdu.entity.SduComment;
import org.jeecg.modules.sdu.service.SduCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/sdu/comments")
@Slf4j
public class SduCommentController {
    @Autowired
    private SduCommentService sduCommentService;

    @PostMapping(value = "/add")
    public Result<SduComment> addComments(@RequestBody SduComment comment){
        Result<SduComment> result = new Result<>();
        SduComment sduComment = new SduComment();
        BeanUtils.copyProperties(comment, sduComment);
        Integer res = sduCommentService.insert(sduComment);
        if (res == 1) {
            result.setSuccess(true);
        } else {
            result.error500("添加评论失败");
        }
        return result;
    }

    @GetMapping(value = "/findAll")
    public Result<List<SduComment>> findAll(@RequestParam Integer id){
        List<SduComment> res = sduCommentService.findAll(id);
        Result<List<SduComment>> result= new Result<>();
        res = sortMsg(res);
        result.setResult(res);
        result.setSuccess(true);
        return result;
    }

    public List<SduComment> sortMsg(List<SduComment> cursor){
        List<SduComment> list = new ArrayList<>();
        for(int i =0;i< cursor.size();i++){
            SduComment tmpParent = cursor.get(i);
            List<SduComment> tmpChild;
            if(tmpParent.getParentmsgid() == null){
                tmpChild = bfs(tmpParent,cursor);
            }else {
                continue;
            }
            tmpParent.setChildren(tmpChild);
            list.add(tmpParent);
         }
        return list;
    }

    public List<SduComment> bfs(SduComment tmpParent,List<SduComment> cursor){
        List<SduComment> childrenList = new ArrayList<>();
        Queue<SduComment> queue = new LinkedList<>();
        queue.add(tmpParent);
        while (!queue.isEmpty()){
            SduComment tmpChild = queue.poll();
            for (SduComment sduComment : cursor) {
                if (sduComment.getParentmsgid() != null && sduComment.getParentmsgid().equals(tmpChild.getId())) {
                    childrenList.add(sduComment);
                    queue.add(sduComment);
                }
            }
        }

        return childrenList;
    }
}
