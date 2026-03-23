package team4.finalproject.ui;

public class MenuPrinter {
    public void printBanner() {
        System.out.println("========================================");
        System.out.println("       STUDENT SORTING APPLICATION      ");
        System.out.println("              Team 4 Project            ");
        System.out.println("========================================");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("-------- MAIN MENU --------");
        System.out.println("1. Fill collection");
        System.out.println("2. Show current collection");
        System.out.println("3. Sort collection");
        System.out.println("4. Special sort  [Доп.1: even values sorted, odd stay in place]");
//        System.out.println("5. Count occurrences  [Доп.4 — multithreaded]");
        System.out.println("5. Write collection to file  [Доп.2: append mode]");
        System.out.println("0. Exit");
        System.out.println("---------------------------");
    }
}
