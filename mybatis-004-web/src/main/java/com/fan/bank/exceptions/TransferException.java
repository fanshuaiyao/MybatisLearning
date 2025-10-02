package com.fan.bank.exceptions;

/**
 * 转账异常
 */
public class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}
