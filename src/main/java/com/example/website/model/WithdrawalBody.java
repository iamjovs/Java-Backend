package com.example.website.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WithdrawalBody {
    private String userName;
    private BigDecimal withdrawalAmount;

    public WithdrawalBody(){

    }

    public WithdrawalBody(String userName, BigDecimal withdrawalAmount){
        this.userName = userName;
        this.withdrawalAmount = withdrawalAmount;
    }
}
