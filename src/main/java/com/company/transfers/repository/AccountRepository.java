package com.company.transfers.repository;

import com.company.transfers.repository.model.Account;

import java.util.List;
import java.util.Optional;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findById(Long id);

    Boolean update(Account account);

    List<Account> list();
}
