package org.example.model;

import lombok.Data;

import java.sql.Date;
@Data
public class Project {
    private Long id;
    private Integer clientId;
    private Date startDate;
    private Date finishDate;
}
