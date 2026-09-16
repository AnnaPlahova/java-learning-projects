package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Модуль фильтрует список строк по подстроке
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // Чтение количества строк
            int sizeOriginalList = inputInt(scanner);

            // Чтение sizeOriginalList строк в список
            List<String> originalList = new ArrayList<>();
            for (int i = 0; i < sizeOriginalList; i++) {
                originalList.add(scanner.nextLine());
            }

            // Чтение подстроки для фильтрации
            String substring = scanner.nextLine();

            // Создание отфильтрованного списка
            List<String> filteredList = filterList(originalList, substring);

            // Вывод результата через запятую и пробел
            String result = String.join(", ", filteredList);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Чтение количества строк
    public static int inputInt(Scanner scanner) {

        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("Input error");
        }
        int sizeOriginalList = scanner.nextInt();
        // Очистка буфера от оставшейся части строки после nextInt() (символ переноса строки)
        scanner.nextLine();
        if (sizeOriginalList < 0) {
            throw new IllegalArgumentException("Incorrect size");
        }
        return sizeOriginalList;
    }

    // Фильтрация строк
    public static List<String> filterList(List<String> lines, String substring) {
        List<String> filteredLines = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(substring)) {
                filteredLines.add(line);
            }
        }
        return filteredLines;
    }
}


