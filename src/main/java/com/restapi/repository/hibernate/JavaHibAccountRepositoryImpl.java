package com.restapi.repository.hibernate;

import com.restapi.model.Account;
import com.restapi.repository.AccountRepository;
import com.restapi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ejb.Stateless;
import java.util.List;


@Stateless
public class JavaHibAccountRepositoryImpl implements AccountRepository {
    private final HibernateUtil hibernateUtil = new HibernateUtil();

    @Override
    public void create(Account account) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.save(account);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public void update(Account account) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.update(account);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public void delete(Account account) {
        hibernateUtil.openTransactionSession();
        Session session = hibernateUtil.getSession();
        session.remove(account);
        hibernateUtil.closeTransactionSession();
    }

    @Override
    public List<Account> getAll() {
        hibernateUtil.openTransactionSession();
        String hql = "SELECT * FROM Account";
        Session session = hibernateUtil.getSession();
        Query query = session.createNativeQuery(hql).addEntity(Account.class);
        List<Account> accounts = query.list();
        hibernateUtil.closeTransactionSession();
        return accounts;
    }

    @Override
    public Account getId(Integer id) {
        hibernateUtil.openTransactionSession();
        String hql = "SELECT * FROM Account WHERE id = :id";
        Session session = hibernateUtil.getSession();
        Query query = session.createNativeQuery(hql).addEntity(Account.class);
        query.setParameter("id", id);
        Account account = (Account) query.getSingleResult();
        hibernateUtil.closeTransactionSession();
        return account;
    }
}
