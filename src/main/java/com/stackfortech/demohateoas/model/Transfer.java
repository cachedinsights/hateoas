package com.stackfortech.demohateoas.model;

public class Transfer {

    private Long toAccountNumber;
    private Long fromAccountNumber;
    private Long transferAmount;
    private String mode;

    public Transfer(Long toAccountNumber, Long fromAccountNumber, Long transferAmount, String mode) {
        this.toAccountNumber = toAccountNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.transferAmount = transferAmount;
        this.mode = mode;
    }

    public Transfer() {
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Long transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "toAccountNumber=" + toAccountNumber +
                ", fromAccountNumber=" + fromAccountNumber +
                ", transferAmount=" + transferAmount +
                ", mode='" + mode + '\'' +
                '}';
    }
}
