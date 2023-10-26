package com.example.website.service;

import com.example.website.model.*;
import com.example.website.repository.DepositRepository;
import com.example.website.repository.UserRepository;
import com.example.website.repository.WalletRepository;
import com.example.website.repository.WithdrawRepository;
import com.example.website.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WalletService {

    private final WalletRepository walletRepository;
    private final DepositRepository depositRepository;
    private final WithdrawRepository withdrawRepository;
    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, WithdrawRepository withdrawRepository, DepositRepository depositRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
        this.withdrawRepository = withdrawRepository;
    }

    public Wallet createWallet() {
        Wallet wallet = new Wallet();

        wallet.setId(UUID.randomUUID());
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setBonusBalance(BigDecimal.ZERO);
        wallet.setTotalDeposited(BigDecimal.ZERO);
        wallet.setTotalWithdrawn(BigDecimal.ZERO);

        return walletRepository.save(wallet);
    }

    public Deposit createDeposit(Wallet wallet, BigDecimal amount){
        Deposit deposit = new Deposit();

        deposit.setDepositId(UUID.randomUUID());
        deposit.setWallet(wallet);
        deposit.setAmount(amount);
        deposit.setDate(LocalDate.now());

        return depositRepository.save(deposit);
    }

    public Withdraw createWithdraw(Wallet wallet, BigDecimal amount){
        Withdraw withdraw = new Withdraw();

        withdraw.setWithdrawId(UUID.randomUUID());
        withdraw.setWallet(wallet);
        withdraw.setAmount(amount);
        withdraw.setDate(LocalDate.now());

        return withdrawRepository.save(withdraw);
    }

    public Wallet findWalletById(UUID id) {
        return walletRepository.findById(id).orElse(null);
    }

    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }

    @Transactional
    public ResponseMessage withdrawAmount(WithdrawalBody withdrawalBody) throws InsufficientBalanceException {
        // Check if the user has enough balance to withdraw the specified amount\
        User user = userRepository.findByUsername(withdrawalBody.getUserName());

        LocalDate lastWithdrawDate = user.getWallet().getLastWithdraw();
        LocalDate dateNow = LocalDate.parse("2023-10-31");

        if(lastWithdrawDate != null){
            Long daysBetween = ChronoUnit.DAYS.between(lastWithdrawDate, dateNow);

            if (daysBetween < 30){
                return new ResponseMessage("Withdraw Unsuccessful. There are "+ (30 - daysBetween ) + " days before you can Withdraw again", "Withdraw", false);
            };
        }

        int comparison =  user.getWallet().getBalance().compareTo(withdrawalBody.getWithdrawalAmount());

        if (comparison == -1) {
            return new ResponseMessage("Withdraw Unsuccessful. Insufficient Balance", "Withdraw", false);
        }

        Wallet wallet = findWalletById(user.getWallet().getId());
        wallet.setBalance(wallet.getBalance().subtract(withdrawalBody.getWithdrawalAmount()));
        wallet.setLastWithdraw(LocalDate.now());
        wallet.setTotalWithdrawn(wallet.getTotalWithdrawn().add(withdrawalBody.getWithdrawalAmount()));
        createWithdraw(user.getWallet(), withdrawalBody.getWithdrawalAmount());
        walletRepository.save(wallet);

        return new ResponseMessage("Withdraw Successful", "Withdraw", true);

    }

    @Transactional
    public ResponseMessage depositAmount(DepositBody depositBody) {

        User user = userRepository.findByUsername(depositBody.getUserName());
        Wallet wallet = findWalletById(user.getWallet().getId());
        wallet.setBalance(wallet.getBalance().add(depositBody.getDepositAmount()));
        wallet.setLastDeposit(LocalDate.now());
        wallet.setTotalDeposited(wallet.getTotalDeposited().add(depositBody.getDepositAmount()));

        createDeposit(user.getWallet(), depositBody.getDepositAmount());

        walletRepository.save(wallet);

        return new ResponseMessage("Deposit Successful", "Deposit", true);

    }

}
