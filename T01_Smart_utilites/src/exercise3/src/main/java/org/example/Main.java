package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

//  Математический модуль находит n число Фибоначчи
public class Main {
    private static final int MAX_N = 92;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = inputNumber(sc);        // порядковый номер числа Фибоначчи
            if (n > MAX_N) {
                throw new IllegalArgumentException("Too large n");
            }
            int i = 0;                  // номер итерации, увеличивается от 0 до n
            long prevPrevious = 0;      // пред-предыдущее число Фибоначчи
            long previous = 0;          // предыдущее число Фибоначчи
            long fibonacci = calculateFibonacci(n, i, prevPrevious, previous);
            System.out.println(fibonacci);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //метод ввода
    private static int inputNumber(Scanner sc) {
        int n = 0;
        while (true) {
            try {
                n = sc.nextInt();
                if (n < 0) {
                    throw new IllegalArgumentException("Could not parse a number. Please, try again");
                }
                break;
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println(e.getMessage() != null ? e.getMessage() :
                        "Could not parse a number. Please, try again");
                sc.nextLine(); // Очистка буфера
            }
        }
        return n;
    }

    private static long calculateFibonacci(int n, int i, long prevPrevious, long previous) {
        long fibonacci = prevPrevious + previous;
        if (i == 1) {
            fibonacci = 1;
        }
        if (i != n) {
            fibonacci = calculateFibonacci(n, ++i, previous, fibonacci);
        }
        return fibonacci;
    }
}

