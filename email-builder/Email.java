package Project.Email;

import java.security.SecureRandom;
import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity = 500;
    private String alternateEmail;

    // ANSI color codes
    private static final String PURPLE = "\u001B[35m";
    private static final String RESET = "\u001B[0m";

    // Constructor: Set first and last name
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        System.out.println("Full name: " + this.firstName + " " + this.lastName);
        this.department = getDepartment();
        if(department=="unknown") return;
        System.out.println("Department: " + department);

        this.password = generatePassword(8);
        this.email = (firstName + "." + lastName + generateRandomDigits(3) + "@" + department + ".com").toLowerCase();

        System.out.println("Email: " + PURPLE + email + RESET);
        System.out.println("Your Password: " + password);
    }

    // Prompt user to choose a department
    private String getDepartment() {
        System.out.print("Enter your Choice\n1 Sales\n2 Development\n3 Accounting\n0 None\nEnter your code: ");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 0: return "general";
            case 1: return "sales";
            case 2: return "development";
            case 3: return "accounting";
            default: return "unknown";
        }
    }

    // Generate secure password
    private String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        SecureRandom random = new SecureRandom();
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(password);
    }

    // Generate random digits
    private String generateRandomDigits(int count) {
        SecureRandom random = new SecureRandom();
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < count; i++) {
            digits.append(random.nextInt(10)); // 0–9 inclusive
        }
        return digits.toString();
    }

    // Set mailbox capacity
    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    // Get mailbox capacity
    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    // Set alternate email
    public void setAlternateEmail(String email) {
        this.alternateEmail = email;
    }

    // Get alternate email
    public String getAlternateEmail() {
        return alternateEmail;
    }

    // Get full email
    public String getEmail() {
        return email;
    }

    // Get generated password
    public String getPassword() {
        return password;
    }
}
