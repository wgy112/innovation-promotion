package org.jeecg.modules.message.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Message implements Serializable {
    private String fromid;
    private String receiveid;
    private String content;
}
