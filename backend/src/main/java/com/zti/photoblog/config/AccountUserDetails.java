package com.zti.photoblog.config;

import com.zti.photoblog.models.Account;
import com.zti.photoblog.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Wrapper around UserDetails
 * Provides role management in security
 */
public class AccountUserDetails implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    /**
     * Constructor
     *
     * @param  account  new Account
     */
    public AccountUserDetails(Account account) {
        username = account.getUsername();
        password = account.getPassword();
        authorities = translate(account.getRoles());
    }

    /**
     * Traslates roles assigned for an user to security authorities.
     *
     * @param  roles  list of roles assignet do Account
     * @return security authorities
     */
    private Collection<? extends GrantedAuthority> translate(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            String name = role.getName().toUpperCase();
            if (!name.startsWith("ROLE_"))
                name = "ROLE_" + name;
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    /**
     * Getter for authorities
     *
     * @return collection of security authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Password getter
     *
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Username getter
     *
     * @return username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Getter for non expired value
     *
     * @return non expired value
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Getter for non locked value
     *
     * @return non locked value
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Getter for non expired credentials value
     *
     * @return non expired credentials value
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Getter for is enabled value
     *
     * @return is enabled value
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}