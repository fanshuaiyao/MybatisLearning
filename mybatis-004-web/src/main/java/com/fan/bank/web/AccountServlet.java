package com.fan.bank.web;

import com.fan.bank.exceptions.MoneyNotEnoughException;
import com.fan.bank.exceptions.TransferException;
import com.fan.bank.service.AccountService;
import com.fan.bank.service.Impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    // 为了让这个对象在其他方法中也可以用。声明为实例变量。
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取表单数据
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        // 调用service的转账方法完成转账。(调业务层)
        try {
            accountService.transfer(fromActno, toActno, money);
            response.sendRedirect(request.getContextPath() + "/success.html");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/error1.html");
        } catch (TransferException e){
            response.sendRedirect(request.getContextPath() + "/error2.html");
        }

        // 调用View完成展示结果。
    }
}