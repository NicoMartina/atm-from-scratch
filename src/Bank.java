import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
                try{
                    a.withdraw(amount);
                } catch (InsufficientFundsException e){
                    System.out.println("Withdrawal failed: " + e.getMessage());
                } catch (IllegalArgumentException e){
                    System.out.println("Invalid amount: " + e.getMessage());
                }
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

        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfered $ " + amount + " from account " + fromAccountName + "to account " + toAccountName);
        } catch(InsufficientFundsException e) {
            System.out.println("Transfer  failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        }
    }

    public void saveToFile(){
        try {
            FileWriter writer = new FileWriter("accounts.txt");
            for (Account a : accounts){
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
                    accounts.add(new Account(accountName, accountBalance));
                }
            }

            scanner.close();
            System.out.println("Accounts loaded successfully");

        } catch (FileNotFoundException e){
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }






}
