package com.example.website.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DepositBody {
    private String userName;
    private BigDecimal depositAmount;

    public DepositBody(){

    }

    public DepositBody(String userName, BigDecimal depositAmount){
        this.userName = userName;
        this.depositAmount = depositAmount;
    }
}
