package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sdu_comment_notice")
public class SduCommentNotice {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String comment;

    //private String commentid;

    //private String user;

    private String username;
    private String solutionusername;
    private Integer solutionid;

    private Date date;

    private Integer state;
    private Integer demandreadstate;
    private String parentmsguser;
}
