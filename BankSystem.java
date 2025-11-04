import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {

    // stores all account names
    private static ArrayList<String> accountNames = new ArrayList<>();

    // keeps balances for each account (same index as names)
    private static ArrayList<Double> accountBalances = new ArrayList<>();

    // file names used to save and load data
    private static final String ACCOUNTS_FILE = "accounts.txt";
    private static final String BALANCES_FILE = "balances.txt";

    // scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // load data from files if they exist
        loadData();

        int choice;

        // keep showing menu until user exits
        do {
            showMenu();
            choice = getValidatedInt();

            switch (choice) {
                case 1 -> viewAccounts();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> createAccount();
                case 5 -> saveData();
                default -> System.out.println("Invalid choice, try again.");
            }

        } while (choice != 5);
    }

    // prints the main menu
    private static void showMenu() {
        System.out.println("\n====== SIMPLE BANK SYSTEM ======");
        System.out.println("1. View All Accounts");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Create New Account");
        System.out.println("5. Save & Exit");
        System.out.print("Enter your choice: ");
    }

    // checks if input is an integer
    private static int getValidatedInt() {
        String input = scanner.next();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Not a number, enter again:");
            return getValidatedInt();
        }
    }

    // checks if input is a double (for money)
    private static double getValidatedDouble() {
        String input = scanner.next();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount, enter again:");
            return getValidatedDouble();
        }
    }

    // adds a new account
    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        scanner.nextLine(); // clears leftover newline
        String name = scanner.nextLine();

        accountNames.add(name);
        accountBalances.add(0.0); // new accounts start at 0

        System.out.println("New account created for: " + name);
    }

    // shows all accounts and balances
    private static void viewAccounts() {
        System.out.println("\n--- Account List ---");
        if (accountNames.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (int i = 0; i < accountNames.size(); i++) {
                System.out.printf("%d: %s - $%.2f%n", i, accountNames.get(i), accountBalances.get(i));
            }
        }
    }

    // deposits money into an account
    private static void depositMoney() {
        if (accountNames.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        viewAccounts();
        System.out.print("Enter the index of the account to deposit into: ");
        int index = getValidatedInt();

        try {
            System.out.print("Enter amount to deposit: ");
            double amount = getValidatedDouble();

            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }

            double newBalance = accountBalances.get(index) + amount;
            accountBalances.set(index, newBalance);

            System.out.printf("Deposited $%.2f into %s's account.%n", amount, accountNames.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That account number doesn’t exist.");
        }
    }

    // withdraws money from an account
    private static void withdrawMoney() {
        if (accountNames.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        viewAccounts();
        System.out.print("Enter the index of the account to withdraw from: ");
        int index = getValidatedInt();

        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = getValidatedDouble();

            double currentBalance = accountBalances.get(index);
            if (amount <= 0) {
                System.out.println("Amount must be more than zero.");
                return;
            }
            if (amount > currentBalance) {
                System.out.println("Not enough balance in this account.");
                return;
            }

            double newBalance = currentBalance - amount;
            accountBalances.set(index, newBalance);

            System.out.printf("Withdrew $%.2f from %s's account.%n", amount, accountNames.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That account number doesn’t exist.");
        }
    }

    // saves data to files
    private static void saveData() {
        saveListToFile(accountNames, ACCOUNTS_FILE);
        saveBalancesToFile(accountBalances, BALANCES_FILE);
        System.out.println("Data saved. Goodbye!");
    }

    // writes account names to file
    private static void saveListToFile(ArrayList<String> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }

    // writes balances to file
    private static void saveBalancesToFile(ArrayList<Double> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Double item : list) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }

    // loads account data from files
    private static void loadData() {
        // load account names
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                accountNames.add(line);
            }
            System.out.println("Loaded account names from file.");
        } catch (IOException e) {
            System.out.println("No account file found, starting new.");
        }

        // load balances
        try (BufferedReader reader = new BufferedReader(new FileReader(BALANCES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                accountBalances.add(Double.parseDouble(line));
            }
            System.out.println("Loaded balances from file.");
        } catch (IOException e) {
            System.out.println("No balances file found, starting empty.");
        }
    }
}
