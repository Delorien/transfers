package com.company.transfers.repository;

import com.company.transfers.repository.model.Account;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface AccountRepository {

    Account save(Account account);

    Account findById(Long id);

    Boolean update(Account account);
}
