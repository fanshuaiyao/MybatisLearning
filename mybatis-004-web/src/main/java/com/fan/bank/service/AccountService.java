package com.fan.bank.service;

import com.fan.bank.exceptions.MoneyNotEnoughException;

/**
 * 业务接口
 * @author fanshuaiyao
 * @version 1.0
 */
public interface AccountService {

    /**
     * 转账接口
     * @param fromActon 转出接口
     * @param toActon 转入接口
     * @param money 转账金额
     */
    void transfer (String fromActon, String toActon, double money) throws MoneyNotEnoughException;
}
