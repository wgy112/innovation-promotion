package org.jeecg.modules.area.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QueryParam implements Serializable {

    private String keyword;

    private String startDate;

    private String endDate;

    private List<String> checkedList;

}
