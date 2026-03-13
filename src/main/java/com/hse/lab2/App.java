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
            // Заполнение случайными числами от 0 до 99
            oneDimensionalArray[i] = random.nextInt(100);
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
     * Вывод главного меню
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("Выберите задачу:");
        System.out.println("0 - Выход");
        System.out.println("1 - Заполнение одномерного массива случайным набором");
        System.out.println("2 - Заполнение одномерного массива с клавиатуры");
        System.out.println("3 - Вывод одномерного массива");
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
