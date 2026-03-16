package team4.finalproject.ui;

import team4.finalproject.domain.Student;
import team4.finalproject.io.StudentStreamGenerator;
import team4.finalproject.service.SortingService;
import team4.finalproject.strategy.BubbleSortStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private final Scanner scanner;
    private final StudentStreamGenerator streamGenerator;
    private final SortingService sortingService;
    private List<Student> currentCollection;

    public ConsoleController() {
        this.scanner = new Scanner(System.in);
        this.streamGenerator = new StudentStreamGenerator();
        this.sortingService = new SortingService(new BubbleSortStrategy<>());
        this.currentCollection = new ArrayList<>();
    }


}
