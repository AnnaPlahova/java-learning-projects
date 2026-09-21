package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Locale;

// Нахождение периметра треугольника
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        double x1, y1, x2, y2, x3, y3;
        while (true) {
            try {
                x1 = sc.nextDouble();
                y1 = sc.nextDouble();
                x2 = sc.nextDouble();
                y2 = sc.nextDouble();
                x3 = sc.nextDouble();
                y3 = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
                sc.nextLine(); // Очистка буфера
            }
        }

        double side12 = calculateSide(x1, y1, x2, y2);
        double side23 = calculateSide(x2, y2, x3, y3);
        double side31 = calculateSide(x3, y3, x1, y1);
        if (side12 == 0 || side23 == 0 || side31 == 0 || isCollinear(side12, side23, side31)) {
            System.out.println("It's not a triangle");
        } else {
            double perimeter = side12 + side23 + side31;
            System.out.printf("Perimeter: %.3f\n", perimeter);
        }
        sc.close();
    }

    private static boolean isCollinear(double a, double b, double c) {
        return (a + b) - c <= 1e-9 || (a + c) - b <= 1e-9 || (b + c) - a <= 1e-9;
    }

    private static double calculateSide(double coordX1, double coordY1, double coordX2, double coordY2) {
        return Math.sqrt(Math.pow(coordX2 - coordX1, 2) + Math.pow(coordY2 - coordY1, 2));
    }
}

