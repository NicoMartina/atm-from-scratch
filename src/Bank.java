import java.util.ArrayList;

public class Bank {
    ArrayList<Account> accounts = new ArrayList<>();

    public void createAccount(String accountName, double balance){
        Account newAccount = new Account(accountName, balance);
        accounts.add(newAccount);
        System.out.println("Account added successfully");
    }

    public void deleteAccount(String accountName){
        if (accounts.isEmpty()){
            System.out.println("There are no accounts to delete.");
            return;
        }
        
        boolean found = false;
        for (int i = accounts.size() - 1; i >= 0; i--) {
            Account a = accounts.get(i);

            if (a.getAccountName().equalsIgnoreCase(accountName)){
                accounts.remove(i);
                System.out.println("Account from " + " has been deleted successfully.");
                System.out.println("---------");
                found = true;
            }
        }


        if (!found){
            System.out.println("The account you are looking for does not exist.");
        }
    }


    public void displayAccounts(){
        for (Account a : accounts){
            System.out.println("Account: " + a.getAccountName());
            System.out.println("Balance: " + a.getBalance());
            System.out.println("---------");
        }
    }

    public void searchAccount(String accountName){
        if (accounts.isEmpty()){
            System.out.println("No accounts with that name exist.");
            return;
        }
        boolean found = false;
        for (Account a : accounts){
            if (a.getAccountName().equalsIgnoreCase(accountName)){
                System.out.println("Account: " + a.getAccountName());
                System.out.println("Balance: " + a.getBalance());
                System.out.println("---------");
                System.out.println();
                found = true;
            }
        }

        if (!found){
            System.out.println("The account with name " + accountName + " doesn't exist");
            System.out.println("-----");
            System.out.println();
        }
    }


    public void depositToAccount(String accountName, double balance){
        if (accounts.isEmpty()){
            System.out.println("The account doesn't exist");
            return;
        }

        boolean found = false;
        for (Account a : accounts){
            if (a.getAccountName().equalsIgnoreCase(accountName)){
                a.deposit(balance);
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println("Account not found.");
        }
    }

    public void withdrawFromAccount(String accountName, double amount){
        if (accounts.isEmpty()){
            System.out.println("The account doesn't exist");
            return;
        }

        boolean found = false;
        for (Account a : accounts){
            if (a.getAccountName().equalsIgnoreCase(accountName)){
                a.withdraw(amount);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Account not found.");
        }
    }

    public void transferBetweenAccounts(String fromAccountName, String toAccountName, double amount){
        if (accounts.isEmpty()){
            System.out.println("The account you are looking for doesn't exist");
            return;
        }

        if (fromAccountName.equalsIgnoreCase(toAccountName)){
            System.out.println("Cannot transfer to same account.");
            return;
        }


        Account fromAccount = null;
        for (Account a : accounts){
            if (a.getAccountName().equalsIgnoreCase(fromAccountName)){
               fromAccount = a;
               break;
            }
        }

        Account toAccount = null;
        for (Account a : accounts){
            if (a.getAccountName().equalsIgnoreCase(toAccountName)){
                toAccount = a;
                break;
            }
        }

        if (fromAccount == null) {
            System.out.println("The source was not found");
            return;
        }

        if (toAccount == null) {
            System.out.println("The source was not found");
            return;
        }

        if (fromAccount.getBalance() < amount){
            System.out.println("Not enough money on " + fromAccountName);
            return;
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        System.out.println("Transfered $ " + amount + " from account " + fromAccountName + "to account " + toAccountName);


    }






}
