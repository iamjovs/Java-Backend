package com.example.website.controller;

import com.example.website.model.DepositBody;
import com.example.website.model.User;
import com.example.website.model.Wallet;
import com.example.website.model.WithdrawalBody;
import com.example.website.response.ResponseMessage;
import com.example.website.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PostMapping(path = "/withdraw")
    public ResponseEntity<?> withdrawWallet(@RequestBody WithdrawalBody withdrawalBody){
        ResponseMessage responseMessage = walletService.withdrawAmount(withdrawalBody);
        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping(path = "/deposit")
    public ResponseEntity<ResponseMessage> depositWallet(@RequestBody DepositBody depositBody){
        ResponseMessage responseMessage = walletService.depositAmount(depositBody);
        return ResponseEntity.ok(responseMessage);
    }
}
