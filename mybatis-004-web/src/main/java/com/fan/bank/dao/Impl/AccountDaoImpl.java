package com.fan.bank.dao.Impl;

import com.fan.bank.dao.AccountDao;
import com.fan.bank.pojo.Account;
import com.fan.bank.utils.SqlSessionUtil;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account selectByActno(String accountNo) {
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();

        Account account = sqlSession.selectOne("account.selectByActon", accountNo);

        sqlSession.close();
        return account;
    }

    @Override
    public int updateByActno(Account account) {
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        int count = sqlSession.update("account.updateByActon", account);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }
}
