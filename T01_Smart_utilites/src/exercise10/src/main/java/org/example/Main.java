package org.example;

import java.util.*;
import java.util.stream.Collectors;

// Модуль ищет имена совершеннолетних пользователей
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Программа считывает количество пользователей
        int count = 0;
        boolean check = false;
        while (!check) {
            count = inputInt(scanner);
            if (count <= 0) {
                System.out.println("Incorrect input. Count <= 0");
                scanner.nextLine(); // очистка буфера
                continue;
            }
            check = true;
        }
        scanner.nextLine(); // очистка буфера от символа новой строки после ввода числа

        // Ввод списка пользователей
        List<User> usersList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            usersList.add(inputUser(scanner));
        }

        // Вывод результата
        output(usersList);
    }

    // Вывод результата
    private static void output(List<User> usersList) {
        String adultNames = usersList.stream()
                .filter(u -> u.getAge() >= 18) // Фильтр по возрасту
                .map(User::getName) // Преобразование в имена
                .collect(Collectors.joining(", ")); // Преобразование в строку
        System.out.println(adultNames);
    }

    // метод ввода int
    private static int inputInt(Scanner scanner) {
        int n;
        while (true) {
            try {
                n = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                scanner.nextLine(); // Очистка буфера
            }
        }
        return n;
    }

    // метод ввода пользователя
    private static User inputUser(Scanner scanner) {
        String name;
        int age;
        while (true) {
            try {
                name = scanner.nextLine();
                age = inputInt(scanner);
                if (age <= 0) {
                    scanner.nextLine(); // очистка буфера
                    throw new IllegalArgumentException("Incorrect input. Age <= 0");
                }
                scanner.nextLine(); // очистка буфера от символа новой строки после ввода int
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        User user = new User(name, age);
        return user;
    }
}