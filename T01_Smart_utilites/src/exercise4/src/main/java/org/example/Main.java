package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

// Математический модуль находит среднее арифметическое отрицательных чисел
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = inputNumber(sc);            // количество чисел
            if (n <= 0) {                       // если количество не положительное - ошибка
                throw new IllegalArgumentException("Input error. Size <= 0");
            }
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {       // записываем числа в массив
                numbers[i] = inputNumber(sc);
            }
            calculateArithmeticMean(n, numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());  // текст ошибки
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

    // расчет среднего арифметического и вывод результата
    private static void calculateArithmeticMean(int n, int[] numbers) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[i] < 0) {
                count++;
                sum += numbers[i];
            }
        }
        if (count == 0) {
            System.out.println("There are no negative elements");
        } else {
            double arithmeticMean = (double) sum / count;
            if (sum % count == 0) {
                System.out.println(sum / count);
            } else {
                System.out.println(arithmeticMean);
            }
        }
    }

}