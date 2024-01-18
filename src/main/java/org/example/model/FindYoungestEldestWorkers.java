package org.example.model;

import lombok.Data;

import java.sql.Date;
@Data
public class FindYoungestEldestWorkers {
    private String type;
    private String name;
    private Date birthday;
}
