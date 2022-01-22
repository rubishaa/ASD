package com.track.expensetrackerapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class TransactionDto {
    private Double amount;
    private String description;
    private String startDate;
    private String transactionType;
    private String categoryId;
    private String accountTypeId;
}
