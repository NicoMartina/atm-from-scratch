import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Bank {

    HashMap<String, Account> accounts = new HashMap<>();

    public void createAccount(String accountName, double balance){
        if (accounts.containsKey(accountName)){
            System.out.println("Account already exists");
            return;
        }
        Account newAccount = new Account(accountName, balance);
        accounts.put(accountName, newAccount);
        System.out.println("Account added successfully");
    }



    public void deleteAccount(String accountName){
        if (accounts.isEmpty()){
            System.out.println("There are no accounts to delete.");
            return;
        }
        Account removed = accounts.remove(accountName);
        if (removed == null){
            System.out.println("Account not found.");
        } else {
            System.out.println("Account '" + accountName + "' deleted successfully");
        }
    }




    public void displayAccounts(){
        for (Account a : accounts.values()){
            System.out.println("Account: " + a.getAccountName());
            System.out.println("Balance: " + a.getBalance());
            System.out.println("---------");
        }
    }

    public void searchAccount(String accountName){
        Account account = accounts.get(accountName);

        if (account == null){
            System.out.println("Account doesn't exist");
            return;
        }

        System.out.println("Account: " + account.getAccountName());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("---------");
    }


    public void depositToAccount(String accountName, double balance){
        Account account = accounts.get(accountName);
        if (account == null){
            System.out.println("Account not found.");
            return;
        }

        account.deposit(balance);
    }

    public void withdrawFromAccount(String accountName, double amount){
        Account account = accounts.get(accountName);

        if (account == null){
            System.out.println("Account doesn't exist");
            return;
        }

        try{
            accounts.get(accountName).withdraw(amount);
        } catch(InsufficientFundsException e) {
            System.out.println("Withdrawal: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        }
    }

    public void transferBetweenAccounts(String fromAccountName, String toAccountName, double amount){
        if (fromAccountName.equalsIgnoreCase(toAccountName)){
            System.out.println("Cannot transfer to same account.");
            return;
        }

        Account fromAccount = accounts.get(fromAccountName);
        Account toAccount = accounts.get(toAccountName);

        if (fromAccount == null){
            System.out.println("Source account not found.");
            return;
        }

        if (toAccount == null){
            System.out.println("Destination account not found.");
            return;
        }
        try{
            accounts.get(fromAccountName).withdraw(amount);
            accounts.get(toAccountName).deposit(amount);
        } catch (InsufficientFundsException e){
            System.out.println("Transfer failed: " + e.getMessage());
        } catch(IllegalArgumentException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        }
    }

    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter("accounts.txt");
            for (Account a : accounts.values()){
                writer.write(a.getAccountName() + "," + a.getBalance() + "\n");
            }
            writer.close();
            System.out.println("Data saved successfully");

        } catch (IOException e) {
            throw new RuntimeException("Error saving account's data: " + e.getMessage());
        }
    }

    public void loadFromFile(){
        try {
            File file = new File("accounts.txt");
            if (!file.exists()){
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2){
                    String accountName = parts[0];
                    String accountBalanceStr = parts[1];
                    double accountBalance = Double.parseDouble(accountBalanceStr);
                    accounts.put(accountName, new Account(accountName, accountBalance));
                }
            }

            scanner.close();
            System.out.println("Accounts loaded successfully");

        } catch (FileNotFoundException e){
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }






}
