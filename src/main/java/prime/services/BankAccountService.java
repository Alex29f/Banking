package prime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}