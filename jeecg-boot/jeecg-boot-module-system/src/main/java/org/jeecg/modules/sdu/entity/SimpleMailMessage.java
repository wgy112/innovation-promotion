package org.jeecg.modules.sdu.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SimpleMailMessage {
    private String message;

    private String to;

    private String subject;

    private String content;

    private String from;

}
