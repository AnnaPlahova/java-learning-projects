package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/* Список питомцев
Программа считывает: -количество питомцев, -тип (dog/cat), -кличка, -возраст.
Каждый питомец добавляется в общий список pets.
Если ввели неправильный тип, программа выводит: «Incorrect input. Unsupported pet type» и переходит к вводу следующего питомца.
Если ввели отрицательный или нулевой возраст, программа выводит: «Incorrect input. Age <= 0» и переходит к вводу следующего питомца.
Программа не завершается с ошибкой при некорректных входных данных при вводе числа. Она выводит: «Could not parse a number. Please, try again» и повторяет попытку ввода.
Программа выводит информацию о каждом питомце.
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int count = inputInt(scanner); // ввод количества питомцев
            List<Animal> pets = new ArrayList<>(); // ввод списка питомцев
            for (int i = 0; i < count; i++) {
                try {
                    pets.add(inputPets(scanner));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (Animal pet : pets) { // вывод списка питомцев
                System.out.println(pet);
            }
        }
    }

    // Метод ввода int
    private static int inputInt(Scanner scanner) {
        int n;
        while (true) {
            try {
                n = scanner.nextInt();
                scanner.nextLine(); // убирает \n после числа
                break;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                scanner.nextLine(); // Очистка буфера
            }
        }
        return n;
    }

    // Ввод питомца
    private static Animal inputPets(Scanner scanner) {
        Animal pet;
        String typeOfAnimal = scanner.nextLine().trim().toLowerCase(); // ввод типа питомца
        if (typeOfAnimal.equals("cat") || typeOfAnimal.equals("dog")) {
            String name = scanner.nextLine(); // ввод имени
            int age = inputAge(scanner); // ввод возраста
            pet = inputAnimal(typeOfAnimal, name, age); // создание объекта класса Cat или Dog
        } else {
            throw new IllegalArgumentException("Incorrect input. Unsupported pet type");
        }
        return pet;
    }

    // Создание объекта класса Cat или Dog
    private static Animal inputAnimal(String typeOfAnimal, String name, int age) {
        Animal pet;
        if (typeOfAnimal.equals("cat")) {
            pet = new Cat(name, age);
        } else {
            pet = new Dog(name, age);
        }
        return pet;
    }

    // Ввод возраста
    private static int inputAge(Scanner scanner) {
        int age = inputInt(scanner);
        if (age <= 0) {
            throw new IllegalArgumentException("Incorrect input. Age <= 0");
        }
        return age;
    }
}