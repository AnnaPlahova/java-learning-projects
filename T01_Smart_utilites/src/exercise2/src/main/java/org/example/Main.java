package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

// Математический модуль переводит секунды к формату hh:mm:ss
public class Main {
    public static void main(String[] args) {
        int sec, min, hour;
        sec = inputSeconds();
        if (sec < 0) {
            System.out.println("Incorrect time");
        } else {
            hour = calculateTime(sec, 'h');
            min = calculateTime(sec, 'm');
            sec = calculateTime(sec, 's');
            outputTime(hour, min, sec);
        }
    }

    //метод ввода
    private static int inputSeconds() {
        Scanner sc = new Scanner(System.in);
        int seconds;
        while (true) {
            try {
                seconds = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                sc.nextLine(); // Очистка буфера
            }
        }
        sc.close();
        return seconds;
    }

    //метод нахождения часов, минут и секунд
    private static int calculateTime(int seconds, char part) {
        int timeUnits = 0;
        switch (part) {
            case 'h': // часы
                timeUnits = seconds / 3600;
                break;
            case 'm': // минуты
                timeUnits = (seconds % 3600) / 60;
                break;
            case 's': // секунды
                timeUnits = seconds % 60;
                break;
        }
        return timeUnits;
    }

    //метод вывода
    private static void outputTime(int hours, int minutes, int seconds) {
        if (hours < 10) System.out.print(0);
        System.out.print(hours);
        System.out.print(":");
        if (minutes < 10) System.out.print(0);
        System.out.print(minutes);
        System.out.print(":");
        if (seconds < 10) System.out.print(0);
        System.out.println(seconds);
    }
}