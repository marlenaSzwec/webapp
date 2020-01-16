package com.malik.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure()
                    .loadProperties("hibernate.properties")
                    .build();

            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public static Transaction openTransaction(Session session) {
        if (session.getTransaction() != null && session.getTransaction().isActive())
            return session.getTransaction();
        return session.beginTransaction();
    }

    public static void databaseTest() {
        try (InputStream input = new FileInputStream("src/main/resources/hibernate.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            
            prop.setProperty("user", prop.getProperty("hibernate.connection.username"));
            prop.setProperty("password", prop.getProperty("hibernate.connection.password"));

            String url = prop.getProperty("hibernate.connection.url");
            System.out.println("[DATABASE] Connecting to database: " + url);

            @SuppressWarnings("unused")
            Connection conn = DriverManager.getConnection(url, prop);

            System.out.println("[DATABASE] Connection succesful!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("[DATABASE] Connection error!");
        }
    }
}