package ru.gb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class User {
    private static Logger logger = LoggerFactory.getLogger(User.class);
    private String name;
    private String login;
    private String password;
    private Set<Account> accounts = new HashSet<>();

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public boolean equals(User user) {
        return name.equals(user.name);

    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account removeAccount(Account account) {
        accounts.remove(account);
        return account;
    }

    @Override
    public String toString() {
        String output = "User " + name + "\n";
        output = output + " login: " + login + "\n pass: " + password + "\n";

        for (Account acc: accounts) {
            output += acc.toString();
        }
        return output;
    }
}
