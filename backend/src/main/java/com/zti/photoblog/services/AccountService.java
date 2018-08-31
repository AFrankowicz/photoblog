package com.zti.photoblog.services;

import com.zti.photoblog.aspects.LogExecution;
import com.zti.photoblog.config.AccountUserDetails;
import com.zti.photoblog.models.Account;
import com.zti.photoblog.models.Role;
import com.zti.photoblog.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Account service
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get account details by username
     *
     * @param username
     * @return response
     */
    @LogExecution
    @Transactional
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }


    /**
     * Adding new account
     *
     * @param account
     * @return response
     */
    @Transactional
    @LogExecution
    public ResponseEntity registerAccount(Account account){
        if (accountRepository.existsByUsername(account.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"User alreeady exists.\"}");
        }
        account.setRoles(Arrays.asList(new Role("USER")));
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    /**
     * Details of account
     *
     * @param username
     * @return account for given username
     */
    @LogExecution
    @Transactional
    public Account getInfo(String username){
        return accountRepository.findByUsername(username);
    }
}
