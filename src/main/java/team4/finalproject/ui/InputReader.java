package team4.finalproject.ui;

import team4.finalproject.service.DataValidator;

import java.util.Scanner;

public class InputReader {
    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input — please enter a whole number (e.g., 3).");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input — please enter a decimal number (e.g., 4.5).");
            }
        }
    }

    public int readBoundedInt(String prompt, int min, int max) {
        while (true) {
            int val = readInt(prompt);
            if (val >= min && val <= max) {
                return val;
            }
            System.out.println("  Value must be between " + min + " and " + max + ".");
        }
    }

    public int readGroupNumber() {
        while (true) {
            int val = readInt("  Group number (1-10): ");
            if (DataValidator.isValidGroupNumber(val)) {
                return val;
            }
            System.out.println("  Invalid — group number must be in range [1, 10].");
        }
    }

    public double readAverageScore() {
        while (true) {
            double val = readDouble("  Average score (0.0-5.0): ");
            if (DataValidator.isValidAverageScore(val)) {
                return val;
            }
            System.out.println("  Invalid — average score must be in range [0.0, 5.0].");
        }
    }

    public int readRecordBookNumber() {
        while (true) {
            int val = readInt("  Record book number (positive integer): ");
            if (DataValidator.isValidRecordBookNumber(val)) {
                return val;
            }
            System.out.println("  Invalid — record book number must be a positive integer.");
        }
    }


    public void close() {
        scanner.close();
    }
}
