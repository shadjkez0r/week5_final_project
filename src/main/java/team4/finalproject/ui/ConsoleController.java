package team4.finalproject.ui;

import team4.finalproject.domain.Student;
import team4.finalproject.io.StudentStreamGenerator;
import team4.finalproject.service.SortingService;
import team4.finalproject.strategy.BubbleSortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private final InputReader inputReader;
    private final MenuPrinter menuPrinter;

    private final Scanner scanner;
    private final StudentStreamGenerator streamGenerator;
    private final SortingService sortingService;
    private List<Student> currentCollection;

    public ConsoleController() {
        this.scanner = new Scanner(System.in);
        this.streamGenerator = new StudentStreamGenerator();
        this.sortingService = new SortingService(new BubbleSortStrategy<>());
        this.inputReader = new InputReader();
        this.menuPrinter = new MenuPrinter();
        this.currentCollection = new ArrayList<>();
    }

    public void run() {
        menuPrinter.printBanner();

        boolean isRunning = true;

        while (isRunning) {
            menuPrinter.printMainMenu();
            int choice = inputReader.readInt("Ваш выбор: ");

            switch (choice) {
                case 0:
                    System.out.println("Пока!");
                    isRunning = false;
                    break;
                case 1:
                    fillCollection();
                    break;
                case 2:
                    showCollection();
                    break;
                case 3:
                    sortCollection();
                    break;
                default:
                    System.out.println("Неизвестная опция, пожалуйста укажите цифру из меню.");

            }
        }
    }

    private void sortCollection() {

    }

    private void showCollection() {

    }

    private void fillCollection() {
        System.out.println("--- Заполнить коллекцию ---");
        System.out.println("1. Random generation  [Доп.3 — Stream API]");
        System.out.println("2. Manual input");
        System.out.println("3. Load from file");
        int choice = inputReader.readInt("Enter your choice: ");

        switch (choice) {
            case 1:
                fillRandom();
                break;
            case 2:
                fillManual();
                break;
            case 3:
                fillFromFile();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void fillFromFile() {
    }

    private void fillManual() {
        int size = inputReader.readBoundedInt(
                "How many students do you want to enter (1-100)? ", 1, 100);
        List<Student> list = new ArrayList<>();

        for (int i = 0; i <fsdfdsds ; i++) {
            
        }
    }

    private void fillRandom() {
        int size = inputReader.readBoundedInt("Enter number of students to generate (1-1000): ", 1, 1000);
        currentCollection = streamGenerator.generateRandom(size);
        System.out.println("Done! Generated " + currentCollection.size() + " students.");
    }


}
