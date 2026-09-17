package org.example;

import java.util.List;
import java.util.NoSuchElementException;

public class AnimalIterator implements BaseIterator <Animal>{
    private List<Animal> animals;
    private int currentIndex;

    public AnimalIterator(List<Animal>animals) {
        this.animals = animals;
        this.currentIndex = 0;
    }

    @Override
    public Animal next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements to iterate");
        }
        return animals.get(currentIndex++);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < animals.size();
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }
}
