package com.example.partc;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

public class BankService {
    private SessionFactory sessionFactory;
    public BankService(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }
    @Transactional
    public void transfer(int fromId, int toId, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Account from = session.get(Account.class, fromId);
        Account to = session.get(Account.class, toId);
        if (from == null || to == null) throw new RuntimeException("Account not found");
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        session.update(from);
        session.update(to);
    }
}
