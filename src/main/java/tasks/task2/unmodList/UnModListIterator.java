package tasks.task2.unmodList;
import tasks.Product;

import java.util.Iterator;

public class UnModListIterator implements Iterator {
    private UnModList list;
    private int currentIndex = 0;

    public UnModListIterator(UnModList list) {
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
