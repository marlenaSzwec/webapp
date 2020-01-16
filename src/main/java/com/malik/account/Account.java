package com.malik.account;

import javax.persistence.*;

@Entity(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String login;
    private String password;
    
    public Account() {}
    
    public Account(String login, String password) {
       this.login = login;
       this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}