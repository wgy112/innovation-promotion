package org.jeecg.modules.sdu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("sdu_comments")
public class SduComment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date date;

    private String content;

    private Integer parentmsgid;
    private Integer schemeid;
    private String user;

    private String parentmsguser;

    private List<SduComment> children;
    public void setChildren(List<SduComment> children) {
        this.children = children;
    }
}
