package org.example;

import java.util.*;

/* Определение количества корма питомцу
Программа считывает: -количество питомцев, -тип (dog/cat), -кличка, -возраст, -масса.
Каждый питомец добавляется в общий список pets.
Если ввели неправильный тип, программа выводит: «Incorrect input. Unsupported pet type» и переходит к вводу следующего питомца.
Если ввели отрицательный или нулевой возраст, программа выводит: «Incorrect input. Age <= 0» и переходит к вводу следующего питомца.
Если ввели отрицательную или нулевую массу, то программа выводит: «Incorrect input. Mass <= 0» и переходит к к вводу следующего питомца.
Программа не завершается с ошибкой при некорректных входных данных при вводе числа. Она выводит: «Could not parse a number. Please, try again» и повторяет попытку ввода.
Метод getFeedInfoKg() в классе Dog вычисляет количество корма: количество корма = масса питомца * 0.3, в классе Cat масса * 0.1.
*/

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
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
                n = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return n;
    }

    // Метод ввода double
    private static double inputDouble(Scanner scanner) {
        double n;
        while (true) {
            try {
                n = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
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
            double mass = inputMass(scanner); // ввод веса
            pet = inputAnimal(typeOfAnimal, name, age, mass); // создание объекта класса Cat или Dog
        } else {
            throw new IllegalArgumentException("Incorrect input. Unsupported pet type");
        }
        return pet;
    }

    // Создание объекта класса Cat или Dog
    private static Animal inputAnimal(String typeOfAnimal, String name, int age, double mass) {
        Animal pet;
        if (typeOfAnimal.equals("cat")) {
            pet = new Cat(name, age, mass);
        } else {
            pet = new Dog(name, age, mass);
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

    // Ввод массы
    private static double inputMass(Scanner scanner) {
        double mass = inputDouble(scanner);
        if (mass <= 0) {
            throw new IllegalArgumentException("Incorrect input. Mass <= 0");
        }
        return mass;
    }
}