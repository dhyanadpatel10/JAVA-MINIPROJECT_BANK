package in.ac.adit.pwj.miniproject.bank;

public class BankDemo {
    public static void main(String[] args) {
        BankManager bank = new BankManager();
        
        bank.createAccount("SAVINGS", "ACC001", "Nidhi", 10000);
        bank.createAccount("CURRENT", "ACC002", "Mudra", 5000);
        
        System.out.println("🧵 MULTI-THREADING BANK TEST");
        System.out.println("👤1 = Nidhi (Deposits) | 👤2 = Mudra (Withdraws)\n");
        
        Thread nidhi = new Thread(() -> {
            Account acc = bank.getAccount("ACC001");
            for (int i = 0; i < 3; i++) {
                acc.deposit(500);
                System.out.println("👤1 Nidhi DEPOSIT $500 → " + acc.getBalance());
                try { Thread.sleep(300); } catch (InterruptedException e) {}
            }
        });
        
        Thread mudra = new Thread(() -> {
            Account acc = bank.getAccount("ACC002");
            for (int i = 0; i < 2; i++) {
                if (acc.withdraw(300)) {
                    System.out.println("👤2 Mudra WITHDRAW $300 → " + acc.getBalance());
                }
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        });
        
        nidhi.start();
        mudra.start();
        
        try {
            nidhi.join();
            mudra.join();
        } catch (InterruptedException e) {}
        
        System.out.println("\n🏦 FINAL BALANCES:");
        System.out.println("Nidhi (ACC001): $" + bank.getAccount("ACC001").getBalance());
        System.out.println("Mudra (ACC002): $" + bank.getAccount("ACC002").getBalance());
        System.out.println("\n✅ THREADING SUCCESS!");
    }
}