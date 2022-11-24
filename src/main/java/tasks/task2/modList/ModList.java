package tasks.task2.modList;

import tasks.Product;

import java.util.*;

public class ModList implements List {
    private Product[] arr = new Product[5];
    private int lastIndex = 0;
    private int removeCounter = 0;

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i <= lastIndex; i++){
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
        for(int i = 0; i < lastIndex; i++){
            if(arr[i].equals(product)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new ModListIterator(this);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Cannot copy this list");
    }

    @Override
    public boolean add(Object o) throws ClassCastException{
        if(!(o instanceof Product)){
            throw new ClassCastException("Wrong object : "+o);
        }
        if(lastIndex >= arr.length-1){
            expandArr();
        }
        arr[lastIndex] = (Product) o;
        lastIndex++;
        return true;
    }

    @Override
    public boolean remove(Object o) throws ClassCastException{
        int i;
        for(i = 0; i < lastIndex; i++){
            if(arr[i].equals(o)) {
                arr[i] = null;
                nullPusher(i);
                lastIndex--;
                destroyer();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if(c.size()>0){
            while(lastIndex+c.size() >= arr.length){
                expandArr();
            }
            Iterator iter = c.iterator();
            while(iter.hasNext()){
                arr[lastIndex] = (Product) iter.next();
                lastIndex++;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) throws IndexOutOfBoundsException{
        if(index >= 0 && index <= lastIndex && c.size()>0){
            while(lastIndex+c.size() >= arr.length){
                expandArr();
            }
            Iterator iter = c.iterator();
            while(iter.hasNext()){
                System.out.println(lastIndex);
                arr[lastIndex] = (Product) iter.next();
                lastIndex++;
            }
            return true;
        } throw new IndexOutOfBoundsException("Incorrect index : "+index);
    }

    @Override
    public void clear() {
        arr = Arrays.copyOf(arr, 0);
        lastIndex = 0;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException{
        if(index >= 0 && index < lastIndex) {
            return arr[index];
        }
        throw new IndexOutOfBoundsException("Incorrect index : "+index);
    }

    @Override
    public Object set(int index, Object element) throws IndexOutOfBoundsException, ClassCastException{
        if(!(element instanceof Product)) {
            throw new ClassCastException("Wrong object : " + element);
        }
        Product tmp = null;
        if(index >= 0 && index < lastIndex){
            tmp = arr[index];
            arr[index] = (Product) element;
            return tmp;
        }
        throw new IndexOutOfBoundsException("Incorrect index : "+index);
    }

    @Override
    public void add(int index, Object element) throws IndexOutOfBoundsException, ClassCastException{
        int i;
        if(!(element instanceof Product)) {
            throw new ClassCastException("Wrong object : " + element);
        }
        if(lastIndex >= arr.length-1){
            expandArr();
        }
        if(index >= 0 && index < lastIndex+1){
            lastIndex++;
            for(i = lastIndex-1; i > index; i--){
                arr[i] = arr[i-1];
            }
            arr[i] = (Product) element;
        }else
            throw new IndexOutOfBoundsException("Incorrect index : "+index);
    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException{
        Product tmp = null;
        if(index >= 0 && index < lastIndex){
            tmp = arr[index];
            arr[index] = null;
            nullPusher(index);
            lastIndex--;
            destroyer();
            return tmp;
        }
        throw new IndexOutOfBoundsException("Incorrect index : "+index);
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < lastIndex; i++){
            if(arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = lastIndex-1; i >= 0; i--){
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
        int i, counter = 0;
        for(i = 0; i < lastIndex; i++){
            if(!c.contains(arr[i])) {
                arr[i] = null;
                counter++;
            }
        }
        nullMultiPusher(counter);
        lastIndex -= counter;
        destroyer();
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        int i, counter = 0;
        for(i = 0; i < lastIndex; i++){
            if(c.contains(arr[i])) {
                arr[i] = null;
                counter++;
            }
        }
        nullMultiPusher(counter);
        lastIndex -= counter;
        destroyer();
        return true;
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

    private void expandArr(){
        arr = Arrays.copyOf(arr, arr.length * 2 + 1);
    }

    private boolean nullPusher(int index){
        for(int i = index; i < arr.length-1; i++){
            arr[i] = arr[i+1];
        }
        arr[arr.length-1] = null;
        removeCounter++;
        return true;
    }

    private boolean nullMultiPusher(int size){
        for(int k = 0; k < size; k++){
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == null){
                    nullPusher(i);
                }
            }
        }
        return true;
    }

    //destroys all null when amount of nulls greater of equals than 1/4 of lists size
    private void destroyer(){
        int i = arr.length-1;
        if(removeCounter >= arr.length/4){
            while(arr[i] == null){
                i--;
                if(i == -1){
                    break;
                }
            }
            arr = Arrays.copyOf(arr, i+1);
            removeCounter = 0;
        }
    }
}

