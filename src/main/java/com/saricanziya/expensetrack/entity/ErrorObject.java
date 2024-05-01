package com.saricanziya.expensetrack.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ErrorObject {

    private Integer statusCode;

    private String message;

    private LocalDate timestamp;
}
