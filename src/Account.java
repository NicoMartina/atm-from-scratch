public class Account {
    private String accountName;
    private double balance;

    public Account(String accountName, double balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit (double amount){
        if (amount <= 0) {
            System.out.println("Please enter a valid amount.");
            return;
        }
        balance += amount;
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException{
        if (amount <= 0){
            throw new InsufficientFundsException("Amount must be positive");

        }

        if (amount > balance){
            throw new InsufficientFundsException(
                    "Insufficient funds. Balance $" + balance + ", Requested: $" + amount
            );
        }

        balance -= amount;
        System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
    }


}
