package com.hse.lab2;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

// Вариант 18
public class App {

    static final String intInputError = "Некорректный ввод. Пожалуйста введите целое число (например, 1, -2, 123).";

    /**
     * Одномерный массив
     */
    static int[] oneDimensionalArray;

    /**
     * Ввод числа с плавающей точкой пользователем
     * Тип значения int
     * 
     * @param scanner Сканер для получения данных ввода
     * @param message Приглашение для ввода чисола
     * @return Введенное число
     */
    static int readInt(Scanner scanner, String message) {
        int result = 0;
        boolean validInput = false;
        do {
            System.out.print(message);
            try {
                result = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println(intInputError);
            }
            scanner.nextLine();
        } while (!validInput);
        return result;
    }

    /**
     * Заполнение одномерного массива случайными числами
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void fillRandomODArray(Scanner scanner) {
        Random random = new Random();
        int size = readInt(scanner, "Введите размер одномерного массива: ");
        oneDimensionalArray = new int[size];
        for (int i = 0; i < size; i++) {
            // Заполнение случайными числами от -100 до 100
            oneDimensionalArray[i] = random.nextInt(201) - 100;
        }
        printODArray();
    }

    /**
     * Заполнение одномерного массива числами, введенными пользователем
     * 
     * @param scanner Сканер для получения данных ввода
     */
    private static void fillUserODArray(Scanner scanner) {
        int size = readInt(scanner, "Введите размер одномерного массива: ");
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
     * Удаление элементов, больших среднего значения
     */
    private static void removeGreaterThanAverage() {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        double sum = 0;
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            sum += oneDimensionalArray[i];
        }
        double average = sum / oneDimensionalArray.length;
        System.out.println("Удаление элементов больше среднего значения: " + average);
        int[] newArray = new int[oneDimensionalArray.length];
        int newIndex = 0;
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            if (oneDimensionalArray[i] <= average) {
                newArray[newIndex++] = oneDimensionalArray[i];
            }
        }
        oneDimensionalArray = new int[newIndex];
        // Переносим элементы из нового массива обратно в исходный массив
        System.arraycopy(newArray, 0, oneDimensionalArray, 0, newIndex);
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
        int k = readInt(scanner, "Введите количество добавляемых элеметов: ");
        int[] newArray = new int[oneDimensionalArray.length + k];
        System.arraycopy(oneDimensionalArray, 0, newArray, 0, oneDimensionalArray.length);
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
    private static void swapODArraayElements() {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        System.out.println("Перестановка четных элементов с нечетными");
        for (int i = 0; i < oneDimensionalArray.length; i += 2) {
            int j = i + 1;
            if (j < oneDimensionalArray.length) {
                int temp = oneDimensionalArray[i];
                oneDimensionalArray[i] = oneDimensionalArray[j];
                oneDimensionalArray[j] = temp;
            }
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
        for (int i = 0; i < oneDimensionalArray.length; i++) {
            if (oneDimensionalArray[i] < 0) {
                System.out.println("Первый отрицательный элемент[" + i + "]: " + oneDimensionalArray[i]);
                System.out.println("Количество сравнений: " + (i + 1));
                return;
            }
        }
        System.out.println("Отрицательных элементов не найдено.");
        System.out.println("Количество сравнений: " + (oneDimensionalArray.length + 1));
    }

    /**
     * Сортировка массива
     */
    private static void sortODArray() {
        if (oneDimensionalArray == null) {
            System.out.println("Массив не заполнен.");
            return;
        }
        System.out.println("Сортировка массива");
        java.util.Arrays.sort(oneDimensionalArray);
        printODArray();
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
        int target = readInt(scanner, "Введите число для поиска: ");
        int left = 0;
        int right = oneDimensionalArray.length - 1;
        int comparisons = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = Integer.compare(oneDimensionalArray[mid], target);
            comparisons++;
            if (comparison == 0) {
                System.out.println("Элемент найден на позиции: " + mid);
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
     * Вывод главного меню
     */
    public static void printMenu() {
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
            printMenu();
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
                        swapODArraayElements();
                        break;
                    case 7:
                        findODArrayNegativeElement();
                        break;
                    case 8:
                        binarySearchODArray(scanner);
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
