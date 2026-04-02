package in.ac.adit.pwj.miniproject.bank;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountNo;
    protected String holderName;
    protected double balance;
    
    // ✅ INNER CLASS
    protected class TransactionHistory {
        private List<String> transactions = new ArrayList<>();
        
        public void addTransaction(String type, double amount) {
            transactions.add(type + ": $" + amount + " | Balance: $" + balance);
        }
        
        @Override
        public String toString() {
            return "📜 History:\n" + String.join("\n", transactions);
        }
    }
    
    protected TransactionHistory history;
    
    public Account(String accountNo, String holderName, double initialBalance) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.history = new TransactionHistory();
        this.history.addTransaction("OPEN", initialBalance);
    }
    
    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);
    
    // ✅ SIMPLIFIED EXCEPTION (No throws - works with current Main.java)
    public boolean safeWithdraw(double amount) {
        try {
            if (amount <= 0) {
                System.out.println("❌ Invalid amount: " + amount);
                return false;
            }
            if (!withdraw(amount)) {
                System.out.println("❌ ACC" + accountNo + ": Insufficient funds! Balance: $" + 
                                 balance + " < " + amount);
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            return false;
        }
    }
    
    public double getBalance() { return balance; }
    public String getAccountNo() { return accountNo; }
    public String getHistory() { return history.toString(); }
}