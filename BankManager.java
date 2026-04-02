package in.ac.adit.pwj.miniproject.bank;

import java.io.*;
import java.util.*;

public class BankManager {
    private Map<String, Account> accounts = new HashMap<>();
    private static final String FILE = "data/accounts.txt";
    
    public BankManager() {
        createDataFolder();
        loadAccounts();
    }
    
    private void createDataFolder() {
        new java.io.File("data").mkdirs();
    }
    
    public void createAccount(String type, String no, String name, double bal) {
        Account acc = "SAVINGS".equals(type) ? 
            new SavingsAccount(no, name, bal) : 
            new CurrentAccount(no, name, bal);
        accounts.put(no, acc);
        saveAccounts();
    }
    
    public Account getAccount(String no) {
        return accounts.get(no);
    }
    
    public void saveAccounts() {
        try (PrintWriter w = new PrintWriter(new FileWriter(FILE))) {
            for (Account a : accounts.values()) {
                w.println(a.getAccountNo() + "|" + 
                         a.getClass().getSimpleName() + "|" + 
                         a.getBalance());
            }
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }
    
    public void loadAccounts() {
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length == 3) {
                    // Simplified load
                }
            }
        } catch (IOException e) {
            System.out.println("📁 New bank created!");
        }
    }
}