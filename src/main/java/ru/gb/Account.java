package ru.gb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by onotole on 16.05.16.
 */
public class Account {
    static private Logger logger = LoggerFactory.getLogger(Account.class);
    private String description;
    private int balance;
    private Set<Record> records;

    public Account(String description) {
        this.description = description;
        this.balance = 0;
        records = new HashSet<>();
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void addRecord(Record record) {
        if ( record.isWithdrawal() ) {
            balance -= record.getValue();
        } else {
            balance += record.getValue();
        }
        records.add(record);
    }

    public Record removeRecord(Record record) {
        records.remove(record);
        return record;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        String output = "Account: " + description + "\n Balance: " + balance + "\n ";
        for (Record rec : records) {
            output = output + rec.toString() + "\n ";
        }
        output += "\n";
        return output;
    }
}
