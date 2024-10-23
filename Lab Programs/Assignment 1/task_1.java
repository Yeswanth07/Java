import java.util.Scanner;

public class CreditCardValidator {
    private String ccNumber;
    
    public CreditCardValidator(String ccNumber) {
        this.ccNumber = ccNumber;
    }
    
    public void validateCard() {
        if (ccNumber.length() < 8 || ccNumber.length() > 9) {
            System.out.println("Invalid credit card number");
            return;
        }
        
        int lastDigit = Character.getNumericValue(ccNumber.charAt(ccNumber.length() - 1));
        String remainingNumber = ccNumber.substring(0, ccNumber.length() - 1);
        
        String reversedNumber = new StringBuilder(remainingNumber).reverse().toString();
        
        int sum = 0;
        for (int i = 0; i < reversedNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedNumber.charAt(i));
            
            switch (i % 2) {
                case 0:
                    digit *= 2;
                    if (digit > 9) {
                        digit = digit / 10 + digit % 10; 
                    break;
                case 1:
                    break;
            }
            sum += digit;
        }
        
        int checksum = (10 - (sum % 10)) % 10;
        
        if (checksum == lastDigit) {
            System.out.println("Valid credit card number");
        } else {
            System.out.println("Invalid credit card number");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter credit card number: ");
        String ccNumber = scanner.nextLine();
        
        CreditCardValidator validator = new CreditCardValidator(ccNumber);
        validator.validateCard();
    }
}
