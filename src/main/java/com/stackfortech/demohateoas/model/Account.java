package com.stackfortech.demohateoas.model;

public class Account {

    private String currency;
    private Long amount;
    private Long accountNumber;
    private double rateOfInterest;
    private String accountType;
    private String accountStatus;

    public Account(String currency, Long amount, Long accountNumber, double rateOfInterest, String accountType, String accountStatus) {
        this.currency = currency;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.rateOfInterest = rateOfInterest;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
    }

    public Account() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Account{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                ", accountNumber=" + accountNumber +
                ", rateOfInterest=" + rateOfInterest +
                ", accountType='" + accountType + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
