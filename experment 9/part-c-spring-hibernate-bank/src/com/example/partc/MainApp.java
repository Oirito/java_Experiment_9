package com.example.partc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SessionFactory sf = (SessionFactory) ctx.getBean(LocalSessionFactoryBean.class).getObject();
        BankService bankService = ctx.getBean(BankService.class);
        // prepare two accounts (run inside a transaction)
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        Account a1 = new Account(1, "Alice", 1000.0);
        Account a2 = new Account(2, "Bob", 1000.0);
        s.saveOrUpdate(a1);
        s.saveOrUpdate(a2);
        t.commit();
        s.close();
        // perform a transfer (wrapped by Spring transaction)
        try {
            bankService.transfer(1, 2, 200.0);
            System.out.println("Transfer invoked - check DB for balances."); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.close();
    }
}
