package org.example.model;


import lombok.Data;

import java.sql.Date;
@Data
public class Worker {
    private Long id;
    private String name;
    private Date birthday;
    private Level level;
    private Integer salary;
    public enum Level {
        TRAINEE,
        JUNIOR,
        MIDDLE,
        SENIOR
    }
}
