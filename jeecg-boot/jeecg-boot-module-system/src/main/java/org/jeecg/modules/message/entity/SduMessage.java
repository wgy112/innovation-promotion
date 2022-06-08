package org.jeecg.modules.message.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SduMessage implements Serializable {

    private String userId;

    private String userName;

    private String senderId;

    private String senderName;

    private String message;

    private Date date;

}
