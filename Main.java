package in.ac.adit.pwj.miniproject.bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankManager bank = new BankManager();
        Scanner scanner = new Scanner(System.in);
        
        // Sample data
        bank.createAccount("SAVINGS", "ACC001", "John Doe", 10000);
        bank.createAccount("CURRENT", "ACC002", "Jane Smith", 5000);
        
        System.out.println("🏦 BANKING SYSTEM LOADED!");
        
        while (true) {
            System.out.println("\n1. Deposit  2. Withdraw  3. Transfer  4. Balance  5. History  6. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 6) break;
            
            switch (choice) {
                case 1 -> deposit(bank, scanner);
                case 2 -> withdraw(bank, scanner);
                case 3 -> transfer(bank, scanner);
                case 4 -> showBalance(bank, scanner);
                case 5 -> showHistory(bank, scanner);
                default -> System.out.println("❌ Invalid choice!");
            }
        }
        scanner.close();
    }
    
    static void deposit(BankManager bank, Scanner sc) {
        System.out.print("Account: "); String acc = sc.nextLine();
        System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
        Account a = bank.getAccount(acc);
        if (a != null) {
            a.deposit(amt);
            System.out.println("💰 Balance: $" + a.getBalance());
        }
    }
    
    static void withdraw(BankManager bank, Scanner sc) {
        System.out.print("Account: "); String acc = sc.nextLine();
        System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
        Account a = bank.getAccount(acc);
        if (a != null && a.withdraw(amt)) {
            System.out.println("✅ Withdrawn!");
        } else {
            System.out.println("❌ Failed!");
        }
    }
    
    static void transfer(BankManager bank, Scanner sc) {
        System.out.print("From: "); String from = sc.nextLine();
        System.out.print("To: "); String to = sc.nextLine();
        System.out.print("Amount: "); double amt = sc.nextDouble(); sc.nextLine();
        Account f = bank.getAccount(from), t = bank.getAccount(to);
        if (f != null && t != null && f.withdraw(amt)) {
            t.deposit(amt);
            System.out.println("✅ Transfer OK!");
        }
    }
    
    static void showBalance(BankManager bank, Scanner sc) {
        System.out.print("Account: "); String acc = sc.nextLine();
        Account a = bank.getAccount(acc);
        System.out.println("💳 " + (a != null ? "$" + a.getBalance() : "Not found"));
    }
    
    static void showHistory(BankManager bank, Scanner sc) {
        System.out.print("Account: "); String acc = sc.nextLine();
        Account a = bank.getAccount(acc);
        System.out.println("📜 " + (a != null ? a.getHistory() : "Not found"));
    }
}