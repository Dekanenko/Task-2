package tasks.task2.unmodList;

import tasks.Product;

import java.util.*;

public class UnModList implements List {
    private final Product[] arr;

    public UnModList() {
        arr = new Product[]{};
    }

    public UnModList(Product[] arr) {
        this.arr = arr;
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if(!(o instanceof Product)){
            throw new ClassCastException("Wrong object : "+o);
        }
        Product product = (Product) o;
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(product)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new UnModListIterator(this);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Cannot copy this list");
    }

    @Override
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) throws ClassCastException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) throws IndexOutOfBoundsException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index >= 0 && index < arr.length) {
            return arr[index];
        }
        throw new IndexOutOfBoundsException("Incorrect index : "+index);
    }

    @Override
    public Object set(int index, Object element) throws IndexOutOfBoundsException, ClassCastException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, Object element) throws IndexOutOfBoundsException, ClassCastException{
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException{
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = arr.length-1; i >= 0; i--){
            if(arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        Iterator iter = c.iterator();
        while(iter.hasNext()){
            if(!contains(iter.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Cannot copy this list");
    }
}

