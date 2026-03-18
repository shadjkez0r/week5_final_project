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
        System.out.println("-------- ГЛАВНОЕ МЕНЮ --------");
        System.out.println("1. Заполнить коллекцию");
        System.out.println("2. Показать коллекцию");
        System.out.println("3. Сортировка");
//        System.out.println("4. Специальная сортировка[Доп. 1 - even natural order]");
//        System.out.println("5. Количество вхождений  [Доп.4 — multithreaded]");
//        System.out.println("6. Запись в файл  [Доп.2 — append mode]");
        System.out.println("0. Выход");
        System.out.println("---------------------------");
    }
}
