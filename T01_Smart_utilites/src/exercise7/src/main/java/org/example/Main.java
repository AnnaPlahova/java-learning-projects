package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Математический модуль считывает в массив числа из файла,
// производит поиск максимального и минимального значений, сохраняет результат в файл
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc1 = new Scanner(System.in);
        String path = sc1.nextLine();                       // считываем путь до файла
        sc1.close();
        try {
            double[] numbers = readAndValidateNumbers(path);
            System.out.println(numbers.length);              // выводим в консоль считанное количество чисел
            printArray(numbers);                            // выводим в консоль считанные числа
            writeResult(numbers);                           // запись результата в файл
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static double[] readAndValidateNumbers(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("Input error. File doesn't exist");
        }
        try (Scanner sc2 = new Scanner(file)) {
            // ввод количества чисел
            int size = inputInt(sc2);
            if (size <= 0) {
                throw new IllegalArgumentException("Input error. Size <= 0");
            }
            // запись чисел в массив
            double[] array = new double[size];
            int count = 0;
            while (sc2.hasNext() && count < size) {
                double value = inputDouble(sc2);
                if (!Double.isNaN(value)) {
                    array[count] = value;
                    count++;
                }
            }
            if (count < size) {
                throw new IllegalArgumentException("Input error. Insufficient number of elements");
            }
            return array;
        }
    }

    // метод ввода int
    private static int inputInt(Scanner sc2) {
        int n;
        while (true) {
            try {
                n = sc2.nextInt();
                break;
            } catch (InputMismatchException e) {
                sc2.next(); // Очистка буфера
            } catch (NoSuchElementException e) {
                n = 0;
                break;
            }
        }
        return n;
    }

    // метод ввода double
    private static double inputDouble(Scanner sc) {
        double n = Double.NaN;
        while (sc.hasNext()) {
            try {
                n = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                sc.next(); // Очистка буфера
            }
        }
        return n;
    }

    // Метод поиска минимума
    private static double searchMin(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    // Метод поиска максимума
    private static double searchMax(double[] array) {
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // метод вывода
    private static void printArray(double[] array) {
        System.out.print(array[0]);
        for (int i = 1; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }

    // метод записи в файл
    private static void writeResult(double[] array) {
        File resultFile = new File("result.txt");
        try (PrintWriter pw = new PrintWriter(resultFile)) {
            pw.println(searchMin(array) + " " + searchMax(array));
            System.out.println("Saving min and max values in file"); // сообщение о записи в файл только при успехе
        } catch (FileNotFoundException e) {
            // в задании не сказано, какое сообщение выводить в случае сбоя записи в файл
        }
    }

}
