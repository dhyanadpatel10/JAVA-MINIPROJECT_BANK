package in.ac.adit.pwj.miniproject.bank;

public class CurrentAccount extends Account {
    private static final double OVERDRAFT = 5000;
    
    public CurrentAccount(String no, String name, double bal) {
        super(no, name, bal);
    }
    
    @Override
    public void deposit(double amt) {
        if (amt > 0) {
            balance += amt;
            history.addTransaction("DEPOSIT", amt);
        }
    }
    
    @Override
    public boolean withdraw(double amt) {
        if (amt > 0 && balance - amt >= -OVERDRAFT) {
            balance -= amt;
            history.addTransaction("WITHDRAW", amt);
            return true;
        }
        return false;
    }
}