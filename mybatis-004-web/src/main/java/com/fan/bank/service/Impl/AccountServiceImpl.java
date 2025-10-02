package com.fan.bank.service.Impl;

import com.fan.bank.dao.AccountDao;
import com.fan.bank.exceptions.MoneyNotEnoughException;
import com.fan.bank.exceptions.TransferException;
import com.fan.bank.pojo.Account;
import com.fan.bank.service.AccountService;
import com.fan.bank.utils.GenerateDaoProxy;
import com.fan.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountServiceImpl implements AccountService
{

//    private final AccountDao accountDao = new AccountDaoImpl();


    // 自己封装的
//    private final AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSqlsession(), AccountDao.class);


    // mybatis使用代理机制  在内存中生成接口的实现类 然后创建实现类的实例
    private AccountDao accountDao = SqlSessionUtil.openSqlsession().getMapper(AccountDao.class);




    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {

        SqlSession sqlSession = SqlSessionUtil.openSqlsession();

        // 1. 判断转出账户的余额是否充足(select)
        Account fromAct = accountDao.selectById(fromActno);

        // 检查转出账户余额是否小于转账金额
        if (fromAct.getBalance() < money) {
            // 2. 如果转出账户余额不足，抛出余额不足异常
            throw new MoneyNotEnoughException("对不起，余额不足！");
        }

        // 3. 如果转出账户余额充足，先查询转入账户信息
        Account toAct = accountDao.selectById(toActno);

        // 更新内存中账户对象的余额
        fromAct.setBalance(fromAct.getBalance() - money); // 转出账户减去相应金额
        toAct.setBalance(toAct.getBalance() + money);     // 转入账户加上相应金额

        // 4. 执行数据库更新操作
        int count = accountDao.updateById(fromAct);    // 更新转出账户余额
        count += accountDao.updateById(toAct);         // 更新转入账户余额

        // 5. 检查两次更新是否都成功
        if (count != 2) {
            // 如果更新次数不是2，说明转账过程出现异常
            throw new TransferException("转账异常，未知原因");
        }

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);

    }
}
