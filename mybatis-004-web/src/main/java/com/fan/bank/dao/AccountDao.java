package com.fan.bank.dao;

import com.fan.bank.pojo.Account;

/**
 * 账户的Dao层，负责CURD， 和业务中的任何一个方法没有挂钩  只负责增删改查
 * @author fanshuaiyao
 * @version 1.0
 */
public interface AccountDao {

    /**
     * 查询接口根据账户id
     * @param accountNo 账户id
     * @return 账户实体信息
     */
    Account selectById(String accountNo);

    /**
     * 更新接口根据id
     * @param account 账户西信息
     * @return 影响的行数
     */
    int updateById(Account account);

}
