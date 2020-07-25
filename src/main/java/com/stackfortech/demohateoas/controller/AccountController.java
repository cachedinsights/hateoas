package com.stackfortech.demohateoas.controller;

import com.stackfortech.demohateoas.model.Account;
import com.stackfortech.demohateoas.model.Transfer;
import com.stackfortech.demohateoas.response.SuccessResponse;
import com.stackfortech.demohateoas.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getAccountDetail(@PathVariable Long id)
    {
        ResponseEntity<?> responseEntity =  accountService.findOne(id);
        SuccessResponse successResponse = (SuccessResponse) responseEntity.getBody();
        Account account = (Account) successResponse.getObject();

        EntityModel<Account> resource = EntityModel.of(account);
        List<String> allowedActions = allowedActions(account);
        allowedActions.stream().forEach(action ->{
            if(action.equalsIgnoreCase("DEPOSIT"))
            {
                resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deposit(new Account())).withRel("deposit"));
            }
            if(action.equalsIgnoreCase("TRANSFER"))
            {
                resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).transfer(new Transfer())).withRel("transfer"));
            }
            if(action.equalsIgnoreCase("WITHDRAW"))
            {
                resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).withdraw(new Account())).withRel("withdraw"));
            }
        });
        return new ResponseEntity<>(resource, HttpStatus.OK);

    }
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody Account account)
    {
        return accountService.createAccount(account);
    }

    @PutMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Account account)
    {
        return accountService.depositToAccount(account);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Account account)
    {
        return accountService.withdrawFromAccount(account);
    }
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer)
    {
        return accountService.transfer(transfer);
    }

    protected List<String> allowedActions(Account account){

        List<String> actions = new ArrayList<>();
        if(account.getAmount() <= 0)
        {
            actions.add("DEPOSIT");
        }
        else
        {
            actions.add("DEPOSIT");
            actions.add("TRANSFER");
            actions.add("WITHDRAW");
        }

        return actions;
    }
}
