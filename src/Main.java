
import java.util.Scanner;


public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        Bank bank = new Bank();

        bank.loadFromFile();
        while (option != 8){

            System.out.println(" ==== Bank Management System ====");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Show All Accounts");
            System.out.println("4. Search an Account");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer");
            System.out.println("8. Exit");
            System.out.println();
            System.out.println("Enter choice: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number (1-8): ");
                scanner.nextLine();
                continue;
            }


            switch (option){
                case 1:
                    System.out.println("Enter the account name: ");
                    String accountName = scanner.nextLine();
                    System.out.println("Enter a starting balance: ");
                    double balance = scanner.nextDouble();
                    bank.createAccount(accountName, balance);
                    break;
                case 2:
                    System.out.println("Enter name of account you want to delete: ");
                    String deleteAccount = scanner.nextLine();
                    bank.deleteAccount(deleteAccount);
                    break;
                case 3:
                    bank.displayAccounts();
                    break;
                case 4:
                    System.out.println("Enter name of the account: ");
                    String searchAccount = scanner.nextLine();
                    bank.searchAccount(searchAccount);
                    break;
                case 5:
                    System.out.println("Enter name of the account: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    double amount = scanner.nextDouble();
                    bank.depositToAccount(name, amount);
                    break;
                case 6:
                    System.out.println("Enter name of the account: ");
                    String withdrawalAccount = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    double amountToWithdraw = scanner.nextDouble();
                    bank.withdrawFromAccount(withdrawalAccount, amountToWithdraw);
                    break;
                case 7:
                    System.out.println("Enter provider account: ");
                    String fromAccount = scanner.nextLine();
                    System.out.println("Enter receiver account: ");
                    String toAccount = scanner.nextLine();
                    System.out.println("Enter amount to transfer: ");
                    double amountToTransfer = scanner.nextDouble();
                    bank.transferBetweenAccounts(fromAccount, toAccount,amountToTransfer);
                    break;
                case 8:
                    bank.saveToFile();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("invalid choice, please try again.");

            }
        }

        scanner.close();



    }
}
