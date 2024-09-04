package prime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prime.models.User;
import prime.models.BankAccount;
import prime.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BankAccountService bankAccountService;

    @Transactional
    public User registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        BankAccount newAccount = bankAccountService.createAccount(savedUser);
        savedUser.setBankAccount(newAccount);
        return savedUser;
    }
}