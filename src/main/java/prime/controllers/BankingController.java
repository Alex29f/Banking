package prime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import prime.models.BankAccount;
import prime.models.User;
import prime.services.BankAccountService;
import prime.services.UserService;

import java.math.BigDecimal;

@Controller
public class BankingController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

    @GetMapping("/banking")
    public String bankingHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByEmail(userDetails.getUsername());
        BankAccount account = bankAccountService.getAccountByUser(user);
        model.addAttribute("balance", account.getBalance());
        return "banking";
    }

    @PostMapping("/deposit")
    public String deposit(@AuthenticationPrincipal UserDetails userDetails,
                          @RequestParam BigDecimal amount) {
        User user = userService.findByEmail(userDetails.getUsername());
        BankAccount account = bankAccountService.getAccountByUser(user);
        bankAccountService.deposit(account, amount);
        return "redirect:/banking";
    }

    @PostMapping("/withdraw")
    public String withdraw(@AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam BigDecimal amount) {
        User user = userService.findByEmail(userDetails.getUsername());
        BankAccount account = bankAccountService.getAccountByUser(user);
        try {
            bankAccountService.withdraw(account, amount);
        } catch (IllegalStateException e) {
            // Handle insufficient funds error
            return "redirect:/banking?error=insufficient_funds";
        }
        return "redirect:/banking";
    }

    @PostMapping("/transfer")
    public String transfer(@AuthenticationPrincipal UserDetails userDetails,
                           @RequestParam String toEmail,
                           @RequestParam BigDecimal amount) {
        User fromUser = userService.findByEmail(userDetails.getUsername());
        User toUser = userService.findByEmail(toEmail);
        if (toUser == null) {
            return "redirect:/banking?error=user_not_found";
        }
        BankAccount fromAccount = bankAccountService.getAccountByUser(fromUser);
        BankAccount toAccount = bankAccountService.getAccountByUser(toUser);
        try {
            bankAccountService.transfer(fromAccount, toAccount, amount);
        } catch (IllegalStateException e) {
            return "redirect:/banking?error=insufficient_funds";
        }
        return "redirect:/banking";
    }
}