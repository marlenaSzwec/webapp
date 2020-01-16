package com.malik.database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.malik.account.Account;
import com.malik.utils.HibernateUtils;

public class Database {
    
    private static volatile Database instance;
    
    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }
    
    public Account login(String login, String password) {
        Account account = getAccountByLogin(login);
        return account == null || !account.getPassword().equals(md5(password, login)) ? null : account;
    }
    
    public Account register(String login, String password) {
        Account account = new Account(login, md5(password, login));
        save(account);
        return account;
    }
    
    public Account getAccountByLogin(String login) {
        return getObjectByHQLQuery("FROM account WHERE login='" + login + "'", Account.class);
    }
    
    public <T> T getObjectByHQLQuery(String hql, Class<T> objectClass) {
        Session session = HibernateUtils.getSession();
        Transaction tx = HibernateUtils.openTransaction(session);
        try {
            List<T> objects = session.createQuery(hql, objectClass).getResultList();
            tx.commit();
            return objects == null || objects.size() == 0 ? null : objects.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return null;
    }
    
    public void save(Object object) {
        Session session = HibernateUtils.getSession();
        Transaction tx = HibernateUtils.openTransaction(session);
        try {
            session.save(object);
            tx.commit();
        } catch (JDBCException e) {
            e.printStackTrace();
            tx.rollback();
        }
        return;
    }
    
    public static String md5(String password, String login) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        m.reset();
        m.update((password + login).getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);

        return hashtext;
    }
}