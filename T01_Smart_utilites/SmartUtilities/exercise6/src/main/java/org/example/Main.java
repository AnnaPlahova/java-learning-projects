package org.example;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

// Математический модуль производит сортировку массива выбором по возрастанию
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {
            int n = inputInt(sc);               // ввод количества чисел
            if (n <= 0) {                       // если количество не положительное - ошибка
                throw new IllegalArgumentException("Input error. Size <= 0");
            }
            double[] numbers = new double[n];
            for (int i = 0; i < n; i++) {       // записываем числа в массив
                numbers[i] = inputDouble(sc);
            }
            selectionSort(numbers);             // сортировка массива
            printArray(numbers);                // вывод результата
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());  // вывод текста ошибки "Input error. Size <= 0"
        }
    }

    // метод ввода int
    private static int inputInt(Scanner sc) {
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

    // метод ввода double
    private static double inputDouble(Scanner sc) {
        double n = 0;
        while (true) {
            try {
                n = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                sc.nextLine(); // Очистка буфера
            }
        }
        return n;
    }

    // Метод сортировки выбором по возрастанию
    // Алгоритм: находим минимум в оставшейся части массива и меняем с текущим элементом
    private static void selectionSort(double[] array) {
        int s = array.length;
        for (int i = 0; i < s - 1; i++) {
            double min = array[i];
            int minPosition = i;
            for (int j = i + 1; j < s; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minPosition = j;
                }
            }
            if (minPosition != i) {
                double temp = array[i];
                array[i] = array[minPosition];
                array[minPosition] = temp;
            }
        }
    }

    // метод вывода
    private static void printArray(double[] array) {
        System.out.print(array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }

}