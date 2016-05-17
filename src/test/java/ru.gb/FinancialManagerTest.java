package ru.gb;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by onotole on 16.05.16.
 */
public class FinancialManagerTest {
    private DataStore fm = null;
    final private String USER1NAME = "Ivan";
    final private String USER2NAME = "Vasya";
    private User user1;
    private User user2;
    private Account account_1_1 = null;
    private Account account_1_2 = null;
    private Account account_2_1 = null;
    private Record record_1_1_1_plus100 = null;
    private Record record_1_1_2_minus50 = null;
    private Record record_1_2_1_plus50 = null;
    private Record record_1_2_2_minus25 = null;
    private Record record_1_2_3_minus15 = null;

    @Before
    public void setUp() throws Exception {
        fm = new FinancialManager();

        user1 = new User(USER1NAME, "ivan", "123");
        user2 = new User(USER2NAME, "vasya", "234");
        fm.addUser(user1);

        account_1_1 = new Account("account 1 1");
        account_1_2 = new Account("account 1 2");
        account_2_1 = new Account("account 2 1");
        user1.addAccount(account_1_1);

        record_1_1_1_plus100 = new Record(false, 100, "salary");
        record_1_1_2_minus50 = new Record(true, 50, "gift for grandma");
        record_1_2_1_plus50 = new Record(false, 50, "gift from grandma");
        record_1_2_2_minus25 = new Record(true, 25, "gift for ma");
        record_1_2_3_minus15 = new Record(true, 15, "gift for pa");
        account_1_1.addRecord(record_1_1_1_plus100);
        account_1_1.addRecord(record_1_1_2_minus50);
        account_1_2.addRecord(record_1_2_1_plus50);
    }

    @org.junit.Test
    public void getUser() throws Exception {
        assertThat(fm.getUser(USER1NAME), is(user1));
    }

    @org.junit.Test
    public void getUserNames() throws Exception {
        Set<String> set = new HashSet<>();
        set.add(USER1NAME);
        assertThat(fm.getUserNames(), is(set));
        set.add(USER2NAME);
        assertThat(fm.getUserNames(), is(not(set)));
    }

    @org.junit.Test
    public void getAccounts() throws Exception {
        Set<Account> set = new HashSet<>();
        set.add(account_1_1);
        assertThat(fm.getAccounts(user1), is(set));

        user1.addAccount(account_1_2);
        assertThat(fm.getAccounts(user1), is(not(set)));

        set.add(account_1_2);
        assertThat(fm.getAccounts(user1), is(set));

        Set<Account> set2 = new HashSet<>();
        assertThat( fm.getAccounts(user2), is(set2));

        user2.addAccount(account_2_1);
        assertThat( fm.getAccounts(user2), is(not(set2)));
    }

    @org.junit.Test
    public void getRecords() throws Exception {
        Set<Record> set = new HashSet<>();
        assertThat(account_2_1.getRecords(), is(set));

        set.add(record_1_1_1_plus100);
        assertThat(account_1_1.getRecords(), is(not(set)));

        set.add(record_1_1_2_minus50);
        assertThat(account_1_1.getRecords(), is(set));
    }

    @org.junit.Test
    public void addUser() throws Exception {
        fm.addUser(user2);
        assertThat(fm.getUser(USER2NAME), is(user2));
        assertThat(fm.getUser(USER1NAME), not(equalTo(user2)));

    }

    @org.junit.Test
    public void addAccount() throws Exception {
        user1.addAccount(account_1_2);
        Set<Account> set = new HashSet<>();
        set.add(account_1_2);
        set.add(account_1_1);
        assertThat(user1.getAccounts(), is(set));
    }

    @org.junit.Test
    public void addRecord() throws Exception {
        Set<Record> set = new HashSet<>();
        set.add(record_1_2_1_plus50);
        assertThat(account_1_2.getRecords(), is(set));

        set.add(record_1_2_2_minus25);
        set.add(record_1_2_3_minus15);
        account_1_2.addRecord(record_1_2_2_minus25);
        assertThat(account_1_2.getRecords(), is(not(set)));

        account_1_2.addRecord(record_1_2_3_minus15);
        assertThat(account_1_2.getRecords(), is(set));
    }

    @org.junit.Test
    public void removeUser() throws Exception {
        fm.addUser(user2);
        assertThat(fm.getUser(USER2NAME), is(user2));
        assertThat(fm.getUser(USER1NAME), is(user1));
        fm.removeUser(USER2NAME);
        assertNull(fm.getUser(USER2NAME));
        assertThat(fm.getUser(USER1NAME), is(user1));

    }

    @org.junit.Test
    public void removeAccount() throws Exception {
        user1.addAccount(account_1_2);
        Set<Account> set = new HashSet<>();
        set.add(account_1_1);
        set.add(account_1_2);
        assertThat(user1.getAccounts(), is(set));

        user1.removeAccount(account_1_2);
        set.remove(account_1_2);

        assertThat(user1.getAccounts(), is(set));

        user1.removeAccount(account_1_1);
        set = new HashSet<>();
        assertThat(user1.getAccounts(), is(set));
    }

    @org.junit.Test
    public void removeRecord() throws Exception {
        Set<Record> set = new HashSet<>();
        set.add(record_1_1_1_plus100);
        set.add(record_1_1_2_minus50);
        assertThat(account_1_1.getRecords(), is(set));

        set.remove(record_1_1_1_plus100);
        account_1_1.removeRecord(record_1_1_1_plus100);
        assertThat(account_1_1.getRecords(), is(set));

        account_1_1.removeRecord(record_1_1_2_minus50);
        set = new HashSet<>();
        assertThat(account_1_1.getRecords(), is(set));

    }


    @Test
    public void testBalance() throws Exception {
        assertThat( account_1_2.getBalance(), is(50));
        account_1_2.addRecord(record_1_2_2_minus25);
        account_1_2.addRecord(record_1_2_3_minus15);
        assertThat( account_1_2.getBalance(), is(10));

        assertThat( account_2_1.getBalance(), is(0));
    }
}