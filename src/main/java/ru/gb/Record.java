package ru.gb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by onotole on 16.05.16.
 */
public class Record implements Comparable {
    private static Logger logger = LoggerFactory.getLogger(Record.class);
    private boolean withdrawal; // true - outcome, false - income
    private Date date;
    private int value;
    private String description;


    public Record(boolean withdrawal, int value, String description) {
        this.withdrawal = withdrawal;
        this.date = new Date();
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public boolean isWithdrawal() {

        return withdrawal;
    }

    @Override
    public int compareTo(Object o) {
        Record record = (Record) o;
        return date.compareTo(record.date);
    }

    @Override
    public String toString() {
        String direction = isWithdrawal() ? "outcome" : "income";
        return "Record: " + date + " " + direction + " " + value + " desc: " + description;
    }
}
