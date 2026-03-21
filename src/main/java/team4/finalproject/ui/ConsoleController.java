package team4.finalproject.ui;

import team4.finalproject.domain.Student;
import team4.finalproject.io.StudentStreamGenerator;
import team4.finalproject.service.SortingService;
import team4.finalproject.service.StudentComparators;
import team4.finalproject.service.strategy.BubbleSortStrategy;
import team4.finalproject.service.strategy.InsertionSortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
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
            int choice = inputReader.readInt("Enter your choice: ");

            switch (choice) {
                case 0:
                    System.out.println("Goodbye!");
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
                    System.out.println("Unknown option. Please choose a number from the menu.");

            }
        }
        inputReader.close();
    }

    private void sortCollection() {
        if (currentCollection.isEmpty()) {
            System.out.println("Collection is empty. Use option 1 to fill it first.");
            return;
        }

        System.out.println("--- Sort Collection ---");
        System.out.println("Select algorithm:");
        System.out.println("  1. Bubble Sort");
        System.out.println("  2. Selection Sort");
        int algoChoice = inputReader.readInt("Enter your choice: ");

        System.out.println("Select sort field:");
        System.out.println("  1. Group Number          (ascending)");
        System.out.println("  2. Average Score         (descending — highest first)");
        System.out.println("  3. Record Book Number    (ascending)");
        int fieldChoice = inputReader.readInt("Enter your choice: ");

        Comparator<Student> comparator = resolveComparator(fieldChoice);
        if (comparator == null) {
            System.out.println("Invalid field choice.");
            return;
        }

        switch (algoChoice) {
            case 1:
                sortingService.setStrategy(new BubbleSortStrategy<>());
                break;
            case 2:
                sortingService.setStrategy(new InsertionSortStrategy<>());
                break;
            default:
                System.out.println("Invalid algorithm choice.");
                return;
        }

        long start = System.currentTimeMillis();
        sortingService.sort(currentCollection, comparator);
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("Sorting complete in " + elapsed + " ms.");
        showCollection();

    }

    private Comparator<Student> resolveComparator(int fieldChoice) {
        switch (fieldChoice) {
            case 1:
                return StudentComparators.BY_GROUP_NUMBER;
            case 2:
                return StudentComparators.BY_AVERAGE_SCORE_DESC;
            case 3:
                return StudentComparators.BY_RECORD_BOOK_NUMBER;
            default:
                return null;
        }
    }

    private void showCollection() {
        if (currentCollection.isEmpty()) {
            System.out.println("Collection is empty. Use option 1 to fill it first.");
            return;
        }
        System.out.println("--- Collection (" + currentCollection.size() + " students) ---");
        System.out.printf("%-4s %-12s %-14s %-18s%n",
                "#", "Group No.", "Avg Score", "Record Book No.");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < currentCollection.size(); i++) {
            Student s = currentCollection.get(i);
            System.out.printf("%-4d %-12d %-14.1f %-18d%n",
                    i + 1, s.getGroupNumber(), s.getAverageScore(), s.getRecordBookNumber());
        }
        System.out.println("--------------------------------------------------");
    }

    private void fillCollection() {
        System.out.println("--- Fill Collection ---");
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
        System.out.println("Enter file path (press Enter for 'students.txt'): ");
        String path = scanner.nextLine().trim();
        if (path.isEmpty()) {
            path = "students.txt";
        }

//        try {
//            FileHandler fileHandler = new FileHandler(path, "output.txt");
//            currentCollection = fileHandler.readFromFile();
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
    }

    private void fillManual() {
        int size = inputReader.readBoundedInt(
                "How many students do you want to enter (1-100)? ", 1, 100);
        List<Student> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            System.out.println("\nStudent " + (i + 1) + " of " + size + ":");
            int groupNumber = inputReader.readGroupNumber();
            double averageScore = inputReader.readAverageScore();
            int recordBookNumber = inputReader.readRecordBookNumber();

//            Student student = new Student(groupNumber, averageScore, recordBookNumber);
            Student student = Student.builder()
                    .recordBookNumber(recordBookNumber)
                    .groupNumber(groupNumber)
                    .averageScore(averageScore)
                    .build();
            list.add(student);
        }

        currentCollection = list;
        System.out.println("Done! Entered " + currentCollection.size() + " students.");
    }

    private void fillRandom() {
        int size = inputReader.readBoundedInt("Enter number of students to generate (1-1000): ", 1, 1000);
        currentCollection = streamGenerator.generateRandom(size);
        System.out.println("Done! Generated " + currentCollection.size() + " students.");
    }


}
