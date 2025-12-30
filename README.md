# Banking System

A command-line banking application built in Java that allows users to manage multiple bank accounts, perform deposits and withdrawals, and transfer money between accounts.

## Features

- **Create Account**: Set up new bank accounts with a starting balance
- **Delete Account**: Remove accounts from the system
- **Display All Accounts**: View all existing accounts and their balances
- **Search Account**: Find specific accounts by name
- **Deposit Money**: Add funds to any account
- **Withdraw Money**: Remove funds from an account (with balance validation)
- **Transfer Between Accounts**: Move money from one account to another
- **Input Validation**: Prevents negative amounts and overdrafts

## How to Run

1. Make sure you have Java installed on your system
2. Clone this repository
3. Navigate to the project directory
4. Compile the Java files:
```
   javac Main.java Account.java Bank.java
```
5. Run the program:
```
   java Main
```

## Usage

When you run the program, you'll see a menu with 8 options:
```
==== Bank Management System ====
1. Create Account
2. Delete Account
3. Show All Accounts
4. Search an Account
5. Deposit
6. Withdraw
7. Transfer
8. Exit
```

Simply enter the number corresponding to the action you want to perform.

### Example Workflow

1. Create two accounts: "Checking" with $500 and "Savings" with $1000
2. Deposit $200 into Checking
3. Withdraw $100 from Savings
4. Transfer $150 from Checking to Savings
5. View all accounts to see updated balances

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList for data management
- Scanner for user input handling

## What I Learned

- Working with decimal numbers (double) for money calculations
- Managing relationships between multiple objects (Account and Bank classes)
- Implementing financial validations (overdraft protection)
- Complex operations involving multiple objects (transfers)
- Input validation and error handling
- Building intuitive command-line interfaces

## Project Structure
```
├── Account.java       # Represents a single bank account
├── Bank.java          # Manages all accounts and operations
├── Main.java          # User interface and menu system
└── README.md          # Project documentation
```

## Future Improvements

- Add file persistence to save/load accounts
- Implement account numbers for unique identification
- Add transaction history for each account
- Support for different account types (checking, savings, credit)
- Interest calculation for savings accounts
- Password protection for accounts
```

---

**Copy this, create `README.md`, save it, then:**
```
git add README.md
git commit -m "Add README documentation"
git push
