package com.zti.photoblog.controllers;

import com.zti.photoblog.models.Account;
import com.zti.photoblog.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Endpoints for accounts
 */
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Endpoint for adding new users
     *
     * @param  account  new account
     * @return response
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerAccount(@RequestBody Account account) {
        return accountService.registerAccount(account);
    }

    /**
     * Endpoint for getting account by username
     *
     * @param  username  username of the account
     * @return response
     */
    @RequestMapping(value = "/accounts/{username}", method = RequestMethod.GET)
    public ResponseEntity getInfo(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getInfo(username));
    }
}
