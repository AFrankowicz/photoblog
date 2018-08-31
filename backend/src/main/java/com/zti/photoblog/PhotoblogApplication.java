package com.zti.photoblog;

import com.zti.photoblog.config.AccountUserDetails;
import com.zti.photoblog.models.Account;
import com.zti.photoblog.models.Post;
import com.zti.photoblog.models.Role;
import com.zti.photoblog.repositories.AccountRepository;
import com.zti.photoblog.services.AccountService;
import com.zti.photoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Main class
 * Starts the SpringBoot Application
 */
@SpringBootApplication
public class PhotoblogApplication {
    /**
     * Main function
     * Starts the application
     *
     * @param  args  aplication arguments
     */
	public static void main(String[] args) {
		SpringApplication.run(PhotoblogApplication.class, args);
	}


    /**
     * Password Encoder used in encoding
     */
	@Autowired
	private PasswordEncoder passwordEncoder;


    /**
     * Authentification Manager used to encode the password.
     */
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder,
									  AccountRepository accountRepository,
									  AccountService accountService,
                                      PostService postService) throws Exception {
		builder.userDetailsService(userDetailsService(accountRepository)).passwordEncoder(passwordEncoder);
	}

    /**
     * Function mapping default UserDetailsService
     * to a custom AccountDetailsSerivce
     */
	private UserDetailsService userDetailsService(final AccountRepository accountRepository) {
		return username -> new AccountUserDetails(accountRepository.findByUsername(username));
	}
}
