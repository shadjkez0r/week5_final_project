# 🎓 Student Sorting & Management Application

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8%2B-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20OOP%20%2F%20GoF-4EAA25?style=for-the-badge)
![Concurrency](https://img.shields.io/badge/Concurrency-Multithreading-blue?style=for-the-badge)

Командный финальный проект (Team 4), разработанный в рамках стажировки. Представляет собой консольное Java-приложение с чистой архитектурой для управления коллекциями студентов, реализации кастомных структур данных, гибкой сортировки и многопоточной обработки информации.

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
