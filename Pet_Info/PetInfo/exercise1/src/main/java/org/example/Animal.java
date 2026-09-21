package org.example;

public abstract class Animal {
    private String name;
    private int age;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String toString();

}
