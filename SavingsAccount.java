package in.ac.adit.pwj.miniproject.bank;

public class SavingsAccount extends Account {
    private static final double INTEREST = 0.03;
    
    public SavingsAccount(String no, String name, double bal) {
        super(no, name, bal);
    }
    
    @Override
    public void deposit(double amt) {
        if (amt > 0) {
            balance += amt * (1 + INTEREST);
            history.addTransaction("DEPOSIT", amt);
            System.out.println("💰 +Interest: $" + balance);
        }
    }
    
    @Override
    public boolean withdraw(double amt) {
        if (amt > 0 && amt <= balance) {
            balance -= amt;
            history.addTransaction("WITHDRAW", amt);
            return true;
        }
        return false;
    }
}