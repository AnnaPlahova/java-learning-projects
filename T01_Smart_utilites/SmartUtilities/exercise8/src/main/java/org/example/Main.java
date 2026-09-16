package org.example;

import java.util.Scanner;

// Математический модуль определяет, является ли последовательность упорядоченной по возрастанию
public class Main {
    public static void main(String[] args) {

        // Попытка прочитать первое число
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Input error");
        } else {
            int firstNumber = scanner.nextInt();
            int count = 1;
            boolean ascendingOrder = true;

            // Цикл для проверки последующих чисел
            while (scanner.hasNextInt()) {
                int secondNumber = scanner.nextInt();
                count++;
                if (secondNumber < firstNumber) { // Нарушение при строго меньшем
                    ascendingOrder = false;
                    break;
                } else {
                    firstNumber = secondNumber; // Обновить предыдущее
                }
            }

            // Вывод результата
            if (ascendingOrder) {
                System.out.println("The sequence is ordered in ascending order");
            } else {
                System.out.println("The sequence is not ordered from the ordinal number of the number " + (count - 1));
            }
            scanner.close();
        }
    }
}
