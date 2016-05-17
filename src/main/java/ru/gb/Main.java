package ru.gb;

import ru.gb.FinancialManager;

/**
 * Created by onotole on 16.05.16.
 */
public class Main {
    public static void main(String[] args) {
        DataStore fm = new FinancialManager();
        User user1 = new User("Ivan", "ivan", "123");
        User user2 = new User("Vasya", "vasya", "234");
        fm.addUser(user1);
        fm.addUser(user2);

        Account account_1_1 = new Account("user1 1");
        Account account_1_2 = new Account("user1 2");
        Account account_2_1 = new Account("user2 1");
        user1.addAccount(account_1_1);
        user1.addAccount(account_1_2);
        user2.addAccount(account_2_1);

        Record record_1_1_1 = new Record(false, 10, "food");
        Record record_1_1_2 = new Record(false, 10, "food");
        Record record_1_2_1 = new Record(false, 10, "food");
        Record record_1_2_2 = new Record(false, 10, "food");
        Record record_2_1_1 = new Record(false, 10, "food");
        Record record_2_1_2 = new Record(false, 10, "food");
        Record record_2_1_3 = new Record(false, 10, "food");
        Record record_2_1_4 = new Record(false, 10, "food");
        account_1_1.addRecord(record_1_1_1);
        account_1_1.addRecord(record_1_1_2);
        account_1_2.addRecord(record_1_2_1);
        account_1_2.addRecord(record_1_2_2);
        account_2_1.addRecord(record_2_1_1);
        account_2_1.addRecord(record_2_1_2);
        account_2_1.addRecord(record_2_1_3);
        account_2_1.addRecord(record_2_1_4);

//        System.out.println(account_1_1.getBalance());
//        System.out.println(account_1_2.getBalance());
//        System.out.println(account_2_1.getBalance());
        System.out.println(user1);
    }



}
