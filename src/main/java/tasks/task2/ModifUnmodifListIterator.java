package tasks.task2;

import tasks.Product;

import java.util.Iterator;

public class ModifUnmodifListIterator implements Iterator<Product> {

    private ModifUnmodifList list;
    private int currentIndex = 0;

    public ModifUnmodifListIterator(ModifUnmodifList list) {
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
