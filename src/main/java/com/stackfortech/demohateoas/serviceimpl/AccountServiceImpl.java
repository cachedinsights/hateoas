package com.stackfortech.demohateoas.serviceimpl;

import com.stackfortech.demohateoas.errorHandler.ErrorHandlerService;
import com.stackfortech.demohateoas.model.Account;
import com.stackfortech.demohateoas.model.Transfer;
import com.stackfortech.demohateoas.response.ErrorResponse;
import com.stackfortech.demohateoas.response.SuccessResponse;
import com.stackfortech.demohateoas.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ErrorHandlerService errorHandlerService;

    private static Map<Long, Account> accountMap = new HashMap<>();
    private Long currentKey = 1L;


    @Override
    public ResponseEntity<?> findOne(Long accountNumber) {
        if(accountMap.containsKey(accountNumber))
        {
            Account accountToRet =  accountMap.get(accountNumber);
            SuccessResponse successResponse = new SuccessResponse("Transaction Successful",accountToRet,200);
            return  new ResponseEntity<>(successResponse,HttpStatus.OK);

        }
        else
        {
            ErrorResponse errorResponse = errorHandlerService.inValidAccountNumber("Account Number does not exist",400,null);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> depositToAccount(Account account) {
        if(accountMap.containsKey(account.getAccountNumber()))
        {
            Long currentAmount = accountMap.get(account.getAccountNumber()).getAmount();
            Long newAmount = currentAmount + account.getAmount();
            Account newAccount = accountMap.get(account.getAccountNumber());
            newAccount.setAmount(newAmount);
            accountMap.replace(account.getAccountNumber(),newAccount);
            SuccessResponse successResponse =new SuccessResponse("Deposit successful",newAccount,200);
            return new ResponseEntity<>(successResponse,HttpStatus.OK);
        }
        else
        {
            ErrorResponse errorResponse = errorHandlerService.inValidAccountNumber("Invalid Account Number",400,null);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> withdrawFromAccount(Account account) {

         if(accountMap.containsKey(account.getAccountNumber()) && accountMap.get(account.getAccountNumber()).getAmount() >= account.getAmount())
        {
            Long currentAmount = accountMap.get(account.getAccountNumber()).getAmount();
            Long newAmount = currentAmount - account.getAmount();
            Account newAccount = accountMap.get(account.getAccountNumber());
            newAccount.setAmount(newAmount);
            accountMap.replace(account.getAccountNumber(),newAccount);
            SuccessResponse successResponse = new SuccessResponse("Withdrawal successful",newAccount,200);
            ResponseEntity<SuccessResponse> response = new ResponseEntity<SuccessResponse>(successResponse,HttpStatus.OK);
            return response;
        }
        else
        {
            ErrorResponse errorResponse = errorHandlerService.insufficientFunds("Insufficient Funds.",400,accountMap.get(account.getAccountNumber()));
            ResponseEntity<ErrorResponse> response = new ResponseEntity<>(errorResponse, HttpStatus.OK);
            return response;
        }

    }

    @Override
    public ResponseEntity<?> createAccount(Account account) {
        Account accountToRet;
        if(account.getAmount() < 0)
        {
           return null;
        }
        else if(account.getAccountNumber()!=null && !accountMap.containsKey(account.getAccountNumber()))
        {
            accountMap.put(account.getAccountNumber(),account);
            currentKey = account.getAccountNumber();
        }
        else
        {
            account.setAccountNumber(++currentKey);
            accountMap.put(currentKey,account);
        }
        accountToRet = accountMap.get(currentKey);
        SuccessResponse successResponse = new SuccessResponse("Account Created Successfully!",accountToRet,200);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> transfer(Transfer transfer) {
        ErrorResponse errorResponse;
        if(!accountMap.containsKey(transfer.getToAccountNumber()))
        {
            errorResponse = errorHandlerService.invalidToAccount("Invalid destination Account Number",400,null);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        else if(!accountMap.containsKey(transfer.getFromAccountNumber()))
        {
            errorResponse = errorHandlerService.invalidToAccount("Invalid source Account Number",400,null);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        else if(accountMap.get(transfer.getFromAccountNumber()).getAmount() < transfer.getTransferAmount())
        {
            errorResponse = errorHandlerService.insufficientFunds("Not Enough Funds to carry out transaction!",400,accountMap.get(transfer.getFromAccountNumber()));
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        Account  fromAccount = accountMap.get(transfer.getFromAccountNumber());
        Account toAccount = accountMap.get(transfer.getToAccountNumber());
        fromAccount.setAmount(fromAccount.getAmount()-transfer.getTransferAmount());
        toAccount.setAmount(toAccount.getAmount()+transfer.getTransferAmount());
        accountMap.replace(fromAccount.getAccountNumber(),fromAccount);
        accountMap.replace(toAccount.getAccountNumber(),toAccount);
        SuccessResponse successResponse = new SuccessResponse("Fund transferred Successful!",fromAccount,200);
        ResponseEntity<SuccessResponse> responseEntity = new ResponseEntity<>(successResponse,HttpStatus.OK);
        return responseEntity;
    }
}
