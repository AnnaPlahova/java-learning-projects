package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/* Отслеживание прогулок питомцев
отслеживает время начала и завершения прогулки питомца
Программа считывает: -количество питомцев, -тип (dog/cat), -кличка, -возраст.
Каждый питомец добавляется в общий список pets.
Если ввели неправильный тип, программа выводит: «Incorrect input. Unsupported pet type» и переходит к вводу следующего питомца.
Если ввели отрицательный или нулевой возраст, программа выводит: «Incorrect input. Age <= 0» и переходит к вводу следующего питомца.
Программа не завершается с ошибкой при некорректных входных данных при вводе числа. Она выводит: «Could not parse a number. Please, try again» и повторяет попытку ввода.
Вычисление времени прогулки для Dog выполняется по формуле: возраст * 0,5, для Cat возраст * 0,25.
Программа вызывает метод goToWalk() у каждого питомца.
Каждый вызов метода goToWalk() выполняется асинхронно в отдельном потоке.
Программа ждет выполнения всех вызовов метода goToWalk(), перед тем как завершиться.
Время старта прогулки и время конца прогулки вычисляются относительно времени старта программы.
При завершении прогулки программа выводит на одной строке в консоль следующую информацию: информация о питомце, время старта прогулки, время конца прогулки.
 */
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner scanner = new Scanner(System.in)) {
            int count = inputInt(scanner); // рекурсивный ввод количества питомцев
            List<Animal> pets = new ArrayList<>();
            inputPets(scanner, count, pets); // рекурсивный ввод питомцев
            List<Thread> threads = new ArrayList<>(); // создаём список потоков для асинхронных прогулок
            long programStartNanos = System.nanoTime(); // время старта программы (сейчас)
            for (Animal pet : pets) {
                Thread thread = new Thread(() -> {
                    long startOfWalkNanos = System.nanoTime(); // время начала прогулки (сейчас)
                    double walkTimeSeconds = pet.goToWalk();    // засыпает(питомец "гуляет"), например, 2.45 сек
                    // время между стартом программы и началом прогулки
                    double startTimeSeconds = (startOfWalkNanos - programStartNanos) / 1_000_000_000.0;
                    startTimeSeconds = Math.round(startTimeSeconds * 10) / 10.0;
                    // время между стартом программы и окончанием прогулки
                    double endTimeSeconds = startTimeSeconds + walkTimeSeconds;
                    System.out.println(pet + ", start time = " + String.format("%.2f", startTimeSeconds) +
                            ", end time = " + String.format("%.2f", endTimeSeconds));
                });
                threads.add(thread);
                try {
                    Thread.sleep(100); // разница времени старта прогулки питомцев относительно друг друга
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // восстановление флага прирывания потока
                    break;
                }
                thread.start(); // запускаем поток после сна
            }

            // Ждём завершения всех потоков
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        }
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