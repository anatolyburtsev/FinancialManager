package ru.gb;

import java.util.Set;

/**
 * Created by onotole on 16.05.16.
 */
public interface DataStore {
    User getUser(String name);
    Set<String> getUserNames();
    Set<Account> getAccounts(User owner);
    Set<Record> getRecords(Account account);
    void addUser(User user);
    void addAccount(User user, Account account);
    void addRecord(Account account, Record record);
    User removeUser(String name);
    Account removeAccount(User owner, Account account);
    Record removeRecord(Account from, Record record);
}
