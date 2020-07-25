package com.stackfortech.demohateoas.service;

import com.stackfortech.demohateoas.model.Account;
import com.stackfortech.demohateoas.model.Transfer;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    public ResponseEntity<?> findOne(Long accountNumber);
    public ResponseEntity<?> depositToAccount(Account account);
    public ResponseEntity<?> withdrawFromAccount(Account account);
    public ResponseEntity<?> createAccount(Account account);
    public ResponseEntity<?> transfer(Transfer transfer);

}
