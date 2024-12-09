
import java.util.Scanner;

interface BankInterface {
    double getBalance();
    double getInterestRate();
}

class BankA implements BankInterface {
    private double balance;

    public BankA(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.0;
    }
}

class BankB implements BankInterface {
    private double balance;

    public BankB(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.4;
    }
}

class BankC implements BankInterface {
    private double balance;

    public BankC(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getInterestRate() {
        return 7.9;
    }
}

public class BankDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the balance for BankA: ");
        double balanceA = scanner.nextDouble();
        BankA bankA = new BankA(balanceA);

        System.out.print("Enter the balance for BankB: ");
        double balanceB = scanner.nextDouble();
        BankB bankB = new BankB(balanceB);

        System.out.print("Enter the balance for BankC: ");
        double balanceC = scanner.nextDouble();
        BankC bankC = new BankC(balanceC);

        System.out.println("\nBankA:");
        System.out.println("Balance: " + bankA.getBalance());
        System.out.println("Interest Rate: " + bankA.getInterestRate() + "%\n");

        System.out.println("BankB:");
        System.out.println("Balance: " + bankB.getBalance());
        System.out.println("Interest Rate: " + bankB.getInterestRate() + "%\n");

        System.out.println("BankC:");
        System.out.println("Balance: " + bankC.getBalance());
        System.out.println("Interest Rate: " + bankC.getInterestRate() + "%");

        scanner.close();
    }
}
