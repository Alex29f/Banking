package prime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prime.models.BankAccount;
import prime.models.User;
import prime.repositories.BankAccountRepository;

import java.math.BigDecimal;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createAccount(User user) {
        BankAccount newAccount = new BankAccount(user, BigDecimal.ZERO);
        return bankAccountRepository.save(newAccount);
    }

    public BankAccount getAccountByUser(User user) {
        return bankAccountRepository.findByUser(user);
    }

    @Transactional
    public void deposit(BankAccount account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        account.setBalance(account.getBalance().add(amount));
        bankAccountRepository.save(account);
    }

    @Transactional
    public void withdraw(BankAccount account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        bankAccountRepository.save(account);
    }

    public BigDecimal getBalance(BankAccount account) {
        return account.getBalance();
    }

    @Transactional
    public void transfer(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) {
        withdraw(fromAccount, amount);
        deposit(toAccount, amount);
    }
}