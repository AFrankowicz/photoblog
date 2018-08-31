package com.zti.photoblog.repositories;

import com.zti.photoblog.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Account repository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Get account by username
     *
     * @param username
     * @return account for given username
     */
    Account findByUsername(String username);

    /**
     * Check if account exists
     *
     * @param username username of checked account
     * @return if exists value
     */
    boolean existsByUsername(String username);

    /**
     * Delete account by username
     *
     * @param username
     */
    void deleteByUsername(String username);
}