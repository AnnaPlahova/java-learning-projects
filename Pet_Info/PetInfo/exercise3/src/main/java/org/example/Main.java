package org.example;

import java.util.*;

/* Списки травоядных и всеядных питомцев
Модуль сначала выводит только травоядных, а затем только всеядных животных.
Программа считывает: -количество питомцев, -тип (dog/cat/hamster/guinea), -кличка, -возраст.
Каждый питомец добавляется в общий список pets.
Если ввели неправильный тип, программа выводит: «Incorrect input. Unsupported pet type» и переходит к вводу следующего питомца.
Если ввели отрицательный или нулевой возраст, программа выводит: «Incorrect input. Age <= 0» и переходит к вводу следующего питомца.
Программа не завершается с ошибкой при некорректных входных данных при вводе числа. Она выводит: «Could not parse a number. Please, try again» и повторяет попытку ввода.
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
            for (Animal pet : pets) { // вывод списка травоядных питомцев
                if (pet instanceof Herbivore) {
                    System.out.println(pet);
                }
            }
            for (Animal pet : pets) { // вывод списка всеядных питомцев
                if (pet instanceof Omnivore) {
                    System.out.println(pet);
                }
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

    // Ввод питомца
    private static Animal inputPets(Scanner scanner) {
        String typeOfAnimal = scanner.nextLine().trim().toLowerCase(); // ввод типа питомца
        if (!isValidPetType(typeOfAnimal)) {
            throw new IllegalArgumentException("Incorrect input. Unsupported pet type");
        } else {
            String name = scanner.nextLine().trim(); // ввод имени
            int age = inputAge(scanner); // ввод возраста
            Animal pet = createAnimal(typeOfAnimal, name, age); // создание объекта класса Cat, Dog, Hamster, GuineaPig
            return pet;
        }
    }

    // Проверка валидности типа питомца
    private static boolean isValidPetType(String typeOfAnimal) {
        return (typeOfAnimal.equals("cat") || typeOfAnimal.equals("dog") ||
                typeOfAnimal.equals("hamster") || typeOfAnimal.equals("guinea"));
    }

    // Создание объекта класса Cat, Dog, Hamster, GuineaPig
    private static Animal createAnimal(String typeOfAnimal, String name, int age) {
        Animal pet;
        switch (typeOfAnimal) {
            case "cat":
                pet = new Cat(name, age);
                break;
            case "dog":
                pet = new Dog(name, age);
                break;
            case "hamster":
                pet = new Hamster(name, age);
                break;
            case "guinea":
                pet = new GuineaPig(name, age);
                break;
            default:
                throw new IllegalArgumentException("Incorrect input. Unsupported pet type");
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