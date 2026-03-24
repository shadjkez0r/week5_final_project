package team4.finalproject.ui;

import team4.finalproject.collection.CustomList;
import team4.finalproject.domain.Student;
import team4.finalproject.io.FileHandler;
import team4.finalproject.io.StudentStreamGenerator;
import team4.finalproject.service.SortingService;
import team4.finalproject.service.StudentComparators;
import team4.finalproject.service.strategy.BubbleSortStrategy;
import team4.finalproject.service.strategy.EvenOnlySortStrategy;
import team4.finalproject.service.strategy.InsertionSortStrategy;
import team4.finalproject.service.strategy.SortingStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class ConsoleController {
    private final InputReader inputReader;
    private final MenuPrinter menuPrinter;
    private final StudentStreamGenerator streamGenerator;
    private final SortingService sortingService;
    private CustomList<Student> currentCollection;

    public ConsoleController() {
        this.streamGenerator = new StudentStreamGenerator();
        this.sortingService = new SortingService(new BubbleSortStrategy<>());
        this.inputReader = new InputReader();
        this.menuPrinter = new MenuPrinter();
        this.currentCollection = new CustomList<>();
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
                case 4:
                    specialSort();
                    break;
                case 5:
                    writeToFile();
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
        SortingStrategy<Student> baseStrategy = chooseAlgorithm();
        if (baseStrategy == null) {
            return;
        }

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


        sortingService.setStrategy(baseStrategy);

        long start = System.currentTimeMillis();
        sortingService.sort(currentCollection.asList(), comparator);
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("Sorting complete in " + elapsed + " ms.");
        showCollection();

    }

    private void specialSort() {
        if (currentCollection.isEmpty()) {
            System.out.println("Collection is empty. Use option 1 to fill it first.");
            return;
        }
        System.out.println("--- Special Sort (even values sorted, odd stay in place) ---");

        SortingStrategy<Student> baseStrategy = chooseAlgorithm();
        if (baseStrategy == null) {
            return;
        }

        System.out.println("Select integer field for even/odd check and sort: ");
        System.out.println("  1. Group Number          (ascending)");
        System.out.println("  2. Record Book Number    (ascending)");
        int fieldChoice = inputReader.readInt("Enter your choice: ");

        ToIntFunction<Student> fieldForNaturalOrder;
        Comparator<Student> comparator;

        switch (fieldChoice) {
            case 1:
                fieldForNaturalOrder = Student::getGroupNumber;
                comparator = StudentComparators.BY_GROUP_NUMBER;
                break;
            case 2:
                fieldForNaturalOrder = Student::getRecordBookNumber;
                comparator = StudentComparators.BY_RECORD_BOOK_NUMBER;
                break;
            default:
                System.out.println("Invalid field choice.");
                return;
        }

        EvenOnlySortStrategy strategy = new EvenOnlySortStrategy(baseStrategy, fieldForNaturalOrder);
        sortingService.setStrategy(strategy);

        long start = System.currentTimeMillis();
        sortingService.sort(currentCollection.asList(), comparator);
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("Special sorting complete in " + elapsed + " ms.");
        showCollection();
    }

    private Comparator<Student> resolveComparator(int fieldChoice) {
        return switch (fieldChoice) {
            case 1 -> StudentComparators.BY_GROUP_NUMBER;
            case 2 -> StudentComparators.BY_AVERAGE_SCORE_DESC;
            case 3 -> StudentComparators.BY_RECORD_BOOK_NUMBER;
            default -> null;
        };
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

    private void fillManual() {
        int size = inputReader.readBoundedInt(
                "How many students do you want to enter (1-100)? ", 1, 100);
        CustomList<Student> list = new CustomList<>();

        for (int i = 0; i < size; i++) {
            System.out.println("\nStudent " + (i + 1) + " of " + size + ":");
            int groupNumber = inputReader.readGroupNumber();
            double averageScore = inputReader.readAverageScore();
            int recordBookNumber = inputReader.readRecordBookNumber();

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
        currentCollection = new CustomList<>(size);
        streamGenerator.generateRandom(size).forEach(currentCollection::add);
        System.out.println("Done! Generated " + currentCollection.size() + " students.");
    }

    private void writeToFile() {
        if (currentCollection.isEmpty()) {
            System.out.println("Collection is empty. Nothing to write.");
            return;
        }

        String path = inputReader.readString(
                "Enter output file path (press Enter for 'src/main/resources/output.txt'): ",
                "src/main/resources/output.txt"
        );

        FileHandler fileHandler = new FileHandler();
        fileHandler.writeToFile(currentCollection.asList(), path);
    }

    private void fillFromFile() {
        String path = inputReader.readString(
                "Enter file path (press Enter for 'src/main/resources/output.txt'): ",
                "src/main/resources/output.txt"
        );

        FileHandler fileHandler = new FileHandler();
        List<Student> fromFile = fileHandler.readFromFile(path);
        currentCollection = new CustomList<>();
        fromFile.forEach(currentCollection::add);
    }

    private SortingStrategy<Student> chooseAlgorithm() {
        System.out.println("Select algorithm:");
        System.out.println(" 1. Bubble Sort");
        System.out.println(" 2. Insertion Sort");

        int algoChoice = inputReader.readInt("Enter your choice: ");

        switch (algoChoice) {
            case 1:
                return new BubbleSortStrategy<>();
            case 2:
                return new InsertionSortStrategy<>();
            default:
                System.out.println("Invalid algorithm choice.");
                return null;
        }
    }

}
