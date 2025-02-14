
import java.util.Scanner;

class BankAccount {
    private double balance;

    // Constructor to initialize balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0; // if negative balance is entered, set to 0
        }
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Method to withdraw money from the account
    public boolean withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            System.out.println("Successfully withdrew: " + amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
            return false;
        }
    }

    // Method to check the current balance
    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor to initialize ATM with a user's bank account
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Display ATM interface menu
    public void showMenu() {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Please choose an option (1-4): ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleCheckBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return; // Exit the ATM
                default:
                    System.out.println("Invalid choice, please select between 1 and 4.");
            }
        }
    }

    // Method to handle withdrawal
    private void handleWithdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    // Method to handle deposit
    private void handleDeposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // Method to check the balance
    private void handleCheckBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }
}

public class BankAccounts {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(5000); // Starting with $5000 balance

        // Create an ATM object and link it to the user's bank account
        ATM atm = new ATM(account);

        // Display the ATM menu and start the process
        atm.showMenu();
    }
}
