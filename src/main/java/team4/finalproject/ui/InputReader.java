package team4.finalproject.ui;


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

    public double readBoundedDouble(String prompt, double min, double max) {
        while (true) {
            double val = readDouble(prompt);
            if (val >= min && val <= max) {
                return val;
            }
            System.out.println("  Value must be between " + min + " and " + max + ".");
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            int val = readInt(prompt);
            if (val > 0) {
                return val;
            }
            System.out.println("  Value must be a positive integer.");
        }
    }

    public int readGroupNumber() {
        return readBoundedInt(" Group number (1 - 1000): ", 1, 1000);
    }

    public double readAverageScore() {
        double raw = readBoundedDouble(" Average score (0.0 - 5.0): ", 0, 5.0);
        return Math.round(raw * 100.0) / 100.0;
    }

    public int readRecordBookNumber() {
        return readPositiveInt(" Record book number (positive integer): ");
    }

    public String readString(String prompt, String defaultValue) {
        System.out.println(prompt);
        String line = scanner.nextLine().trim();
        return line.isEmpty() ? defaultValue : line;
    }


    public void close() {
        scanner.close();
    }
}
