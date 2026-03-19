package com.hse.lab2;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

// Вариант 18
public class App {

    // Строка сообщения об ошибке для некорректного ввода целого числа
    static final String intInputError = "Некорректный ввод. Пожалуйста введите целое число (например, 1, -2, 123).";

    // Генератор случайных чисел для заполнения массивов
    static Random random = new Random();

    // Одномерный массив
    static int[] oneDimensionalArray;

    // Двумерный массив
    static int[][] twoDimensionalArray;

    // Рваный массив
    static int[][] jaggedArray;

    /**
     * Ввод числа с плавающей точкой пользователем с проверкой диапазона
     * Тип значения int
     * 
     * @param scanner Сканер для получения данных ввода
     * @param message Приглашение для ввода чисола
     * @param min     Минимальное допустимое значение
     * @param max     Максимальное допустимое значение
     * @return Введенное число
     */
    static int readInt(Scanner scanner, String message, int min, int max) {
        int result = 0;
        boolean validInput = false;
        do {
            System.out.print(message);
            try {
                result = scanner.nextInt();
                if (result >= min && result <= max) {
                    validInput = true;
                } else {
                    System.out.println("Число должно быть в диапазоне от " + min + " до " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println(intInputError);
            }
            scanner.nextLine();
        } while (!validInput);
        return result;
    }

    /**
     * Ввод числа с плавающей точкой пользователем
     * Тип значения int
     * 
     * @param scanner Сканер для получения данных ввода
     * @param message Приглашение для ввода чисола
     * @return Введенное число
     */
    static int readInt(Scanner scanner, String message) {
        return readInt(scanner, message, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Генерация случайного значения от -100 до 100
     * 
     * @return Случайное целое число
     */
    private static int generateRandomValue() {
        return random.nextInt(201) - 100;
    }

    /**
     * Заполнение одномерного массива случайными числами
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void fillRandomODArray(Scanner scanner) {
        int size = readInt(scanner, "Введите размер одномерного массива: ", 1, Integer.MAX_VALUE);
        oneDimensionalArray = new int[size];
        for (int i = 0; i < size; i++) {
            // Заполнение случайными числами от -100 до 100
            oneDimensionalArray[i] = generateRandomValue();
        }
        printODArray();
    }

    /**
     * Заполнение одномерного массива числами, введенными пользователем
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void fillUserODArray(Scanner scanner) {
        int size = readInt(scanner, "Введите размер одномерного массива: ", 1, Integer.MAX_VALUE);
        oneDimensionalArray = new int[size];
        for (int i = 0; i < size; i++) {
            oneDimensionalArray[i] = readInt(scanner, "Введите элемент " + (i + 1) + ": ");
        }
        printODArray();
    }

    /**
     * Вывод содержимого одномерного массива
     */
    private static void printODArray() {
        System.out.println("Содержимое одномерного массива: ");
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            System.out.print(oneDimensionalArray[i] + " ");
        }
        System.out.println();
    }

    /**
     * Вычисление среднего значения
     */
    private static double calcAverage() {
        double sum = 0;
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            sum += oneDimensionalArray[i];
        }
        return sum / oneDimensionalArray.length;
    }

    /**
     * Удаление элементов, больших среднего значения
     */
    private static void removeGreaterThanAverage() {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        double average = calcAverage();
        System.out.println("Удаление элементов больше среднего значения: " + average);
        // Вычисляем размер нового массива
        int newLength = 0;
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            if (oneDimensionalArray[i] <= average) {
                newLength++;
            }
        }
        // Переносим элементы в новый массив
        int[] newArray = new int[newLength];
        int newIndex = 0;
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            if (oneDimensionalArray[i] <= average) {
                newArray[newIndex++] = oneDimensionalArray[i];
            }
        }
        oneDimensionalArray = newArray;
        printODArray();
    }

    /**
     * Расширение одномерного массива
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void expandODArray(Scanner scanner) {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        int k = readInt(scanner, "Введите количество добавляемых элеметов: ", 1, Integer.MAX_VALUE);
        int[] newArray = new int[oneDimensionalArray.length + k];
        // Переносим элементы в новый массив
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            newArray[i] = oneDimensionalArray[i];
        }
        // Добавляем новые элементы
        for (int i = 0; i < k; i++) {
            newArray[oneDimensionalArray.length + i] = readInt(scanner,
                    "Введите элемент " + (oneDimensionalArray.length + i + 1) + ": ");
        }
        oneDimensionalArray = newArray;
        printODArray();
    }

    /**
     * Перестановка четных элементов с нечетными
     */
    private static void swapODArrayElements() {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        System.out.println("Перестановка четных элементов с нечетными");
        for (int i = 0; i < oneDimensionalArray.length - 1; i += 2) {
            int j = i + 1;
            int temp = oneDimensionalArray[i];
            oneDimensionalArray[i] = oneDimensionalArray[j];
            oneDimensionalArray[j] = temp;
        }
        printODArray();
    }

    /**
     * Поиск первого отрицательного элемента в массиве
     */
    private static void findODArrayNegativeElement() {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        System.out.println("Поиск первого отрицательного элемента");
        int i = 0;
        while (i < oneDimensionalArray.length && oneDimensionalArray[i] >= 0) {
            i++;
        }
        if (i < oneDimensionalArray.length) {
            System.out.println("Первый отрицательный элемент[" + i + "]: " + oneDimensionalArray[i]);
        } else {
            System.out.println("Отрицательных элементов не найдено.");
        }
        System.out.println("Количество сравнений: " + (i + 1));
    }

    /**
     * Сортировка массива
     */
    private static void sortODArray() {
        for (int i = 0; i < oneDimensionalArray.length - 1; i++) {
            for (int j = 0; j < oneDimensionalArray.length - i - 1; j++) {
                if (oneDimensionalArray[j] > oneDimensionalArray[j + 1]) {
                    int temp = oneDimensionalArray[j];
                    oneDimensionalArray[j] = oneDimensionalArray[j + 1];
                    oneDimensionalArray[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Бинарный поиск элемента в массиве
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void binarySearchODArray(Scanner scanner) {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        sortODArray();
        printODArray();
        int target = readInt(scanner, "Введите число для поиска: ");
        int left = 0;
        int right = oneDimensionalArray.length - 1;
        int comparisons = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = Integer.compare(oneDimensionalArray[mid], target);
            comparisons++;
            if (comparison == 0) {
                System.out.println("Элемент найден на позиции: " + (mid + 1));
                System.out.println("Количество сравнений: " + comparisons);
                return;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Элемент не найден в массиве.");
        System.out.println("Количество сравнений: " + comparisons);
    }

    /**
     * Заполнение двумерного массива случайным набором
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void fillRandomTDArray(Scanner scanner) {
        int rows = readInt(scanner,
                "Введите количество строк двумерного массива: ",
                1,
                Integer.MAX_VALUE);
        int cols = readInt(scanner,
                "Введите количество столбцов двумерного массива: ",
                1,
                Integer.MAX_VALUE);
        twoDimensionalArray = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Заполнение случайными числами от -100 до 100
                twoDimensionalArray[i][j] = generateRandomValue();
            }
        }
        printTDArray();
    }

    /**
     * Вывод двумерного массива
     */
    private static void printTDArray() {
        System.out.println("Содержимое двумерного массива: ");
        if (twoDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            for (int j = 0; j < twoDimensionalArray[i].length; j++) {
                System.out.print(twoDimensionalArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void removeZeroColumns() {
        if (twoDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        System.out.println("Удаление столбцов с нулевыми элементами");
        int rows = twoDimensionalArray.length;
        int cols = twoDimensionalArray[0].length;
        int removedCols = 0;
        boolean[] zeroColumns = new boolean[cols];
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (twoDimensionalArray[i][j] == 0) {
                    zeroColumns[j] = true;
                    removedCols++;
                    break;
                }
            }
        }
        int[][] newArray = new int[rows][cols - removedCols];
        for (int i = 0, newColIndex = 0; i < cols; i++) {
            if (!zeroColumns[i]) {
                for (int j = 0; j < rows; j++) {
                    newArray[j][newColIndex] = twoDimensionalArray[j][i];
                }
                newColIndex++;
            }
        }
        twoDimensionalArray = newArray;
        printTDArray();
    }

    /**
     * Заполнение рваного массива случайным набором
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void fillRandomJaggedArray(Scanner scanner) {
        int rows = readInt(scanner,
                "Введите количество строк рваного массива: ",
                1,
                Integer.MAX_VALUE);
        int cols = readInt(scanner,
                "Введите максимальное количество столбцов для строки: ",
                1,
                Integer.MAX_VALUE);
        jaggedArray = new int[rows][];
        for (int i = 0; i < rows; i++) {
            int curCols = random.nextInt(cols) + 1; // Случайное количество столбцов для текущей строки
            jaggedArray[i] = new int[curCols];
            for (int j = 0; j < curCols; j++) {
                // Заполнение случайными числами от -100 до 100
                jaggedArray[i][j] = generateRandomValue();
            }
        }
        printJaggedArray();
    }

    /**
     * Вывод рваного массива
     */
    private static void printJaggedArray() {
        System.out.println("Содержимое рваного массива: ");
        if (jaggedArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        for (int i = 0; i < jaggedArray.length; i++) {
            for (int j = 0; j < jaggedArray[i].length; j++) {
                System.out.print(jaggedArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void addRowToJaggedArray(Scanner scanner) {
        if (jaggedArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        int newRowPos = readInt(scanner,
                "Введите позицию для добавления новой строки (0 - " + (jaggedArray.length) + "): ",
                0,
                jaggedArray.length);
        int newRowCols = readInt(scanner,
                "Введите количество столбцов для новой строки: ",
                1,
                Integer.MAX_VALUE);
        int[] newRow = new int[newRowCols];
        for (int j = 0; j < newRowCols; j++) {
            // Заполнение случайными числами от -100 до 100
            newRow[j] = generateRandomValue();
        }
        int[][] newArray = new int[jaggedArray.length + 1][];
        int destPos = 0;
        for (int i = 0; i < jaggedArray.length; i++) {
            if (i == newRowPos) {
                newArray[destPos++] = newRow;
            }
            newArray[destPos++] = jaggedArray[i];
        }
        if (newRowPos == jaggedArray.length) {
            newArray[destPos] = newRow;
        }
        jaggedArray = newArray;
        printJaggedArray();
    }

    /**
     * Вывод меню работы с одномерным массивом
     */
    private static void printODMenu() {
        System.out.println();
        System.out.println("Выберите задачу:");
        System.out.println("0 - Выход");
        System.out.println("1 - Заполнение массива случайным набором");
        System.out.println("2 - Заполнение массива с клавиатуры");
        System.out.println("3 - Вывод массива");
        System.out.println("4 - Удаление элементов, больших среднего значения");
        System.out.println("5 - Расширение массива");
        System.out.println("6 - Перестановка четных элементов с нечетными");
        System.out.println("7 - Поиск первого отрицательного элемента");
        System.out.println("8 - Бинарный поиск в массиве");
        System.out.print("Введите номер пункта > ");
    }

    /**
     * Вывод меню работы с двумерным массивом
     */
    private static void printTDMenu() {
        System.out.println();
        System.out.println("Выберите задачу:");
        System.out.println("0 - Выход");
        System.out.println("1 - Заполнение двумерного массива случайным набором");
        System.out.println("2 - Вывод двумерного массива");
        System.out.println("3 - Удаление столбцов с нулевыми элементами");
        System.out.println("4 - Заполнение рваного массива случайным набором");
        System.out.println("5 - Вывод рваного массива");
        System.out.println("6 - Добавление строки в рваный массив");
        System.out.print("Введите номер пункта > ");
    }

    /**
     * Вывод главного меню для выбора типа массива
     */
    private static void printMainMenu() {
        System.out.println();
        System.out.println("Выберите тип массива:");
        System.out.println("0 - Выход");
        System.out.println("1 - Одномерный массив");
        System.out.println("2 - Двумерный массив");
        System.out.print("Введите номер пункта > ");
    }

    /**
     * Исполнение задач для одномерного массива
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void execODTasks(Scanner scanner) {
        boolean running = true;
        while (running) {
            printODMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        fillRandomODArray(scanner);
                        break;
                    case 2:
                        fillUserODArray(scanner);
                        break;
                    case 3:
                        printODArray();
                        break;
                    case 4:
                        removeGreaterThanAverage();
                        break;
                    case 5:
                        expandODArray(scanner);
                        break;
                    case 6:
                        swapODArrayElements();
                        break;
                    case 7:
                        findODArrayNegativeElement();
                        break;
                    case 8:
                        binarySearchODArray(scanner);
                        break;
                    case 0:
                        System.out.println("Возврат в главное меню...");
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println(intInputError);
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    /**
     * Исполнение задач для двумерного массива
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void execTDTasks(Scanner scanner) {
        boolean running = true;
        while (running) {
            printTDMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        fillRandomTDArray(scanner);
                        break;
                    case 2:
                        printTDArray();
                        break;
                    case 3:
                        removeZeroColumns();
                        break;
                    case 4:
                        fillRandomJaggedArray(scanner);
                        break;
                    case 5:
                        printJaggedArray();
                        break;
                    case 6:
                        addRowToJaggedArray(scanner);
                        break;
                    case 0:
                        System.out.println("Возврат в главное меню...");
                        running = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(intInputError);
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    /**
     * Основной метод программы
     * 
     * @param args параметры командной строки
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Лабораторная работа 2");
        while (running) {
            printMainMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        execODTasks(scanner);
                        break;
                    case 2:
                        execTDTasks(scanner);
                        break;
                    case 0:
                        System.out.println("Выход из программы...");
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Некорректный ввод.");
                scanner.nextLine(); // Очистка буфера
            }
        }
        scanner.close();
    }
}
