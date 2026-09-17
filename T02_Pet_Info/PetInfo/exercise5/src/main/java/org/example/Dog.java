package org.example;

import java.util.concurrent.TimeUnit;

public class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return String.format("Dog name = %s, age = %d", getName(), getAge());
    }

    @Override
    public double goToWalk() {
        double walkTimeSeconds = getAge() * 0.5;
        try {
            TimeUnit.MILLISECONDS.sleep((long) (walkTimeSeconds * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // восстановление флага прирывания
        }
        return walkTimeSeconds;
    }

}
