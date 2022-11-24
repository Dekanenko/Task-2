package tasks.task2.modList;

import tasks.Product;

import java.util.Iterator;

public class ModListIterator implements Iterator {

    private ModList list;
    private int currentIndex = 0;

    public ModListIterator(ModList list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    @Override
    public Product next() {
        if(hasNext()){
            currentIndex++;
            return (Product) list.get(currentIndex-1);
        }
        return null;
    }
}
