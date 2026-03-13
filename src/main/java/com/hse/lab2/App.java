package com.hse.lab2;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

// Вариант 18
public class App {

    static final String intInputError = "Некорректный ввод. Пожалуйста введите корректное число (например, 1, 2, 123).";
    static final String doubleInputError = "Некорректный ввод. Пожалуйста введите корректное число (например, 3.14, -5.2, 100).";

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
                System.out.println(doubleInputError);
            }
            scanner.nextLine();
        } while (!validInput);
        return result;
    }

    /**
     * Ввод числа с плавающей точкой пользователем
     * Тип значения double
     * 
     * @param scanner Сканер для получения данных ввода
     * @param message Приглашение для ввода чисола
     * @return Введенное число
     */
    static double readDouble(Scanner scanner, String message) {
        double result = 0;
        boolean validInput = false;
        do {
            System.out.print(message);
            try {
                result = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println(doubleInputError);
            }
            scanner.nextLine();
        } while (!validInput);
        return result;
    }

    /**
     * Вывод главного меню
     */
    public static void printMenu() {
        System.out.println();
        System.out.println("Выберите задачу:");
        System.out.println("0 - Выход");
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
