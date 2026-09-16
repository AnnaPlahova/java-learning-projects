package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

// Математический модуль ищет числа, у которых совпадает первая и последняя цифра
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = inputNumber(sc);            // ввод количества чисел
            if (n <= 0) {                       // если количество не положительное - ошибка
                throw new IllegalArgumentException("Input error. Size <= 0");
            }
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {       // записываем числа в массив
                numbers[i] = inputNumber(sc);
            }

            // подсчет количества чисел чисел, у которых совпадает первая и последняя цифра
            int resultCount = 0;
            for (int i = 0; i < n; i++) {
                if (hasSameFirstAndLastDigit(numbers[i])) {
                    resultCount++;
                }
            }

            // запись результата в массив
            int[] resultNumbers = new int[resultCount];
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (hasSameFirstAndLastDigit(numbers[i])) {
                    resultNumbers[index++] = numbers[i];
                }
            }

            // вывод результата
            if (resultCount > 0) {
                printNumbers(resultNumbers);
            } else {
                System.out.println("There are no such elements");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());  // вывод текста ошибки "Input error. Size <= 0"
        }
    }

    // метод ввода
    private static int inputNumber(Scanner sc) {
        int n = 0;
        while (true) {
            try {
                n = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                sc.nextLine(); // Очистка буфера
            }
        }
        return n;
    }

    // проверка числа на равенство первой и последней цифр
    private static boolean hasSameFirstAndLastDigit(int number) {
        int absNumber = Math.abs(number);
        int lastDigit = absNumber % 10;
        int firstDigit = absNumber;
        while (firstDigit >= 10) {
            firstDigit /= 10;
        }
        return firstDigit == lastDigit;
    }

    // метод вывода
    private static void printNumbers(int[] resultNumbers) {
        System.out.print(resultNumbers[0]);
        for (int i = 1; i < resultNumbers.length; i++) {
            System.out.print(" " + resultNumbers[i]);
        }
        System.out.println();
    }

}