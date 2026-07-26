# 🎓 Student Sorting & Management Application

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20OOP%20%2F%20GoF-4EAA25?style=for-the-badge)
![Concurrency](https://img.shields.io/badge/Concurrency-Multithreading-blue?style=for-the-badge)

Командный финальный проект команды Team 4. Представляет собой консольное Java-приложение с чистой архитектурой для управления коллекциями студентов, реализации кастомных структур данных, гибкой сортировки и многопоточной обработки информации.

---

## 🌟 Ключевые возможности

### 📦 1. Кастомная структура данных (`CustomList<T>`)
- Реализация собственного динамического массива с нуля без использования стандартных реализаций `ArrayList`.
- Поддержка интерфейсов `Iterable<T>`, собственного итератора и сплитератора (`Spliterator<T>`).
- Мост к **Java 8 Stream API**: бесшовная работа с последовательными (`stream()`) и параллельными (`parallelStream()`) потоками.
- Адаптер к стандартному `java.util.List` через `AbstractList`.

### 🔄 2. Алгоритмы сортировки (Strategy Pattern)
- Использование паттерна **Стратегия (Strategy)** для динамического переключения алгоритмов в рантайме (`SortingService`).
- **Bubble Sort (`BubbleSortStrategy`)** и **Insertion Sort (`InsertionSortStrategy`)** с поддержкой любых компараторов.
- **Специальная сортировка (`EvenOnlySortStrategy`)**: кастомный алгоритм, сортирующий только элементы на четных позициях, оставляя нечетные на своих местах.
- Комбинированные компараторы (`StudentComparators`): сортировка по среднему баллу, номеру группы, зачетке и их цепочкам.

### ⚡ 3. Многопоточность и Concurrency
- Подсчет вхождений и фильтрация коллекции в многопоточном режиме с использованием `parallelStream()`.
- Потокобезопасная фиксация используемых потоков через `ConcurrentHashMap.newKeySet()`.
- Генерация случайных валидных данных без блокировок с помощью `ThreadLocalRandom`.

### 💾 4. Потоковый ввод-вывод (Java NIO)
- Чтение данных из CSV/текстовых файлов через `Files.lines(Path)` с автоматической фильтрацией комментариев (`#`) и валидацией формата строки.
- Запись отсортированных данных в файл в режиме дозаписи (`StandardOpenOption.APPEND`) с генерацией временных меток и метаинформации.

---

## 🏗️ Архитектура и структура проекта

Проект строго разделен на независимые слои (Layered Architecture):

```text
src/main/java/team4/finalproject/
├── collection/          # Собственные структуры данных (CustomList, CustomIterator, CustomSpliterator)
├── domain/              # Доменные модели (Student с паттерном Builder и валидацией)
├── io/                  # Ввод/вывод (FileHandler, DataGenerator, StudentStreamGenerator)
├── service/             # Бизнес-логика и алгоритмы
│   ├── strategy/        # Реализации паттерна Strategy для сортировок
│   ├── SortingService   # Сервис управления сортировками
│   └── OccurrenceCounter# Многопоточный счетчик вхождений
└── ui/                  # Клиентский уровень CLI (ConsoleController, MenuPrinter, InputReader)
```

---

## 🛠️ Примененные паттерны проектирования (GoF)

1. **Builder Pattern (`Student.builder()`)** — обеспечение иммутабельности доменной модели и безопасная валидация полей при создании объекта.
2. **Strategy Pattern (`SortingStrategy<T>`)** — инкапсуляция алгоритмов сортировки и возможность их взаимозаменяемости без изменения клиентского кода.
3. **Adapter Pattern (`CustomList.ListAdapter`)** — адаптация собственной структуры данных под стандартный интерфейс `java.util.List`.
4. **Dependency Injection (Через конструкторы)** — слабосвязанная архитектура сервисов и контроллеров.

---

## 👥 Роль в проекте

**Team Lead & Core Developer (`shadjkez0r`):**
- Организация командной разработки, построение архитектуры проекта и код-ревью.
- Управление репозиторием и Git-процессами.
- Реализация ядра коллекции `CustomList`, паттернов сортировки и многопоточной обработки данных.

---

## 💻 Интерактивное меню CLI

```text
========================================
       STUDENT SORTING APPLICATION      
              Team 4 Project            
========================================

-------- MAIN MENU --------
1. Fill collection
2. Show current collection
3. Sort collection
4. Special sort  [Доп.1: even values sorted, odd stay in place]
5. Count occurrences  [Доп.4: multithreading]
6. Write collection to file  [Доп.2: append mode]
0. Exit
---------------------------
```

---

## 🚀 Требования и инструкция по запуску

### 📋 Требования (Requirements)
- **Java Development Kit (JDK):** версия 17 или выше (рекомендуется Java 17 / 21)
- **Сборщик проектов:** Apache Maven 3.8+
- **Операционная система:** Windows / Linux / macOS

### 🛠️ Инструкция по сборке и запуску (How to Run)

1. **Клонирование репозитория:**
   ```bash
   git clone https://github.com/shadjkez0r/week5_final_project.git
   cd week5_final_project
   ```

2. **Сборка проекта через Maven:**
   ```bash
   mvn clean package
   ```
   *После успешной сборки в директории `target/` будет создан исполняемый файл `finalproject-1.0-SNAPSHOT.jar`.*

3. **Запуск приложения в консоли:**
   ```bash
   java -cp target/finalproject-1.0-SNAPSHOT.jar team4.finalproject.Main
   ```
