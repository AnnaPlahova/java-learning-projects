package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/* Увеличение возраста конкретных питомцев в функциональной парадигме
Программа увеличивает на 1 год возраст питомцев старше 10 лет, придерживаясь функциональной парадигмы.
Программа считывает: -количество питомцев, -тип (dog/cat), -кличка, -возраст.
Каждый питомец добавляется в общий список pets.
Если ввели неправильный тип, программа выводит: «Incorrect input. Unsupported pet type» и переходит к вводу следующего питомца.
Если ввели отрицательный или нулевой возраст, программа выводит: «Incorrect input. Age <= 0» и переходит к вводу следующего питомца.
Программа не завершается с ошибкой при некорректных входных данных при вводе числа. Она выводит: «Could not parse a number. Please, try again» и повторяет попытку ввода.
Программа должна выводит информацию о каждом питомце.
Программа использует Stream API.
Запрещается использовать любые операторы повторения.
*/
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            int count = inputInt(scanner); // рекурсивный ввод количества питомцев
            List<Animal> pets = new ArrayList<>();
            inputPets(scanner, count, pets); // рекурсивный ввод питомцев
            List<Animal> updatePets = pets.stream()
                    .map(pet -> pet.getAge() <= 10 ? pet :
                            createAnimal(getTypeOfAnimal(pet), pet.getName(), pet.getAge() + 1))
                    .toList();
            updatePets.forEach(pet -> System.out.println(pet));
        }
    }

    //
    private static String getTypeOfAnimal(Animal pet) {
        String type;
        if (pet instanceof Dog) {
            type = "dog";
        } else {
            type = "cat";
        }
        return type;
    }

    // Метод ввода int
    private static int inputInt(Scanner scanner) {
        int n;
        try {
            n = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Could not parse a number. Please, try again");
            n = inputInt(scanner); // рекурсивный вызов, если введено не число
        }
        return n;
    }

    // Ввод питомца
    private static void inputPets(Scanner scanner, int count, List<Animal> pets) {
        if (count <= 0) {
            return;
        }
        count--;
        String typeOfAnimal = scanner.nextLine().trim().toLowerCase(); // ввод типа питомца
        if (!isValidPetType(typeOfAnimal)) {
            System.out.println("Incorrect input. Unsupported pet type");
        } else {
            String name = scanner.nextLine().trim(); // ввод имени
            int age = inputInt(scanner); // ввод возраста
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
            } else {
                pets.add(createAnimal(typeOfAnimal, name, age)); // создание объекта класса Cat, Dog
            }
        }
        inputPets(scanner, count, pets);
    }

    // Проверка валидности типа питомца
    private static boolean isValidPetType(String typeOfAnimal) {
        return (typeOfAnimal.equals("cat") || typeOfAnimal.equals("dog"));
    }

    // Создание объекта класса Cat, Dog
    private static Animal createAnimal(String typeOfAnimal, String name, int age) {
        Animal pet;
        switch (typeOfAnimal) {
            case "cat":
                pet = new Cat(name, age);
                break;
            case "dog":
                pet = new Dog(name, age);
                break;
            default:
                throw new IllegalArgumentException("Incorrect input. Unsupported pet type");
        }
        return pet;
    }

}