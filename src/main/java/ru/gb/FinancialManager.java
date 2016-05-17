package ru.gb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by onotole on 16.05.16.
 */
public class FinancialManager implements DataStore {
    static Logger logger = LoggerFactory.getLogger(FinancialManager.class);

    private Set<User> users;

    public FinancialManager() {
        users = new HashSet<>();
    }

    @Override
    public User getUser(String name) {
        for (User user : users) {
            if (name.equals(user.getName())) return user;
        }
        return null;
    }

    @Override
    public Set<String> getUserNames() {
        Set<String> names = new HashSet<>();
        for (User user : users) {
            names.add(user.getName());
        }
        return names;
    }

    @Override
    public Set<Account> getAccounts(User owner) {
        return owner.getAccounts();
    }

    @Override
    public Set<Record> getRecords(Account account) {
        return account.getRecords();
    }

    @Override
    public void addUser(User user) {
        if (! users.contains(user)) {
            users.add(user);
            logger.info("user " + user + " added!");
        } else {
            logger.warn("User " + user + "wasn't added, because already exist");
        }
    }

    @Override
    public void addAccount(User user, Account account) {
        user.addAccount(account);
    }

    @Override
    public void addRecord(Account account, Record record) {
        account.addRecord(record);
    }

    @Override
    public User removeUser(String name) {
        User toReturn = getUser(name);
        users.remove(toReturn);
        return toReturn;
    }

    @Override
    public Account removeAccount(User owner, Account account) {
        return owner.removeAccount(account);
    }

    @Override
    public Record removeRecord(Account from, Record record) {
        return from.removeRecord(record);
    }
}
