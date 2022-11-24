package tasks.task2;

import tasks.task2.modList.ModList;
import tasks.task2.unmodList.UnModList;

import java.util.*;

public class ModifUnmodifList<Product> implements List {

    private UnModList unModList;
    private ModList modList;

    public ModifUnmodifList(UnModList unModList, ModList modList) {
        if(unModList == null){
            this.unModList = new UnModList();
        }else{
            this.unModList = unModList;
        }
        if(modList == null){
            this.modList = new ModList();
        }else {
            this.modList = modList;
        }
    }

    @Override
    public int size() {
        return unModList.size()+modList.size();
    }

    @Override
    public boolean isEmpty() {
        return unModList.isEmpty() && modList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return unModList.contains(o) || modList.contains(o);
    }

    @Override
    public Iterator iterator() {
        return new ModifUnmodifListIterator(this);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Object o) {
        return modList.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return modList.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return modList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        checkIndex(index);
        return modList.addAll(Math.abs(unModList.size() - index), c);
    }

    //should I clear both parts or only modifiable one?
    @Override
    public void clear() {
        modList.clear();
    }

    @Override
    public Object get(int index) {
        if(index >= unModList.size()){
            //use abs in order to get right index for modifiable part
            return modList.get(Math.abs(unModList.size() - index));
        }else if(index < unModList.size() && index >= 0){
            return unModList.get(index);
        }
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        checkIndex(index);
        return modList.set(Math.abs(unModList.size() - index), element);
    }

    @Override
    public void add(int index, Object element) {
        checkIndex(index);
        modList.add(Math.abs(unModList.size() - index), element);
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        return modList.remove(Math.abs(unModList.size() - index));
    }

    @Override
    public int indexOf(Object o) {
        int tmp;
        tmp = unModList.indexOf(o);
        if(tmp != -1){
            return tmp;
        }
        tmp = modList.indexOf(o);
        if(tmp != -1){
            return tmp+ unModList.size();
        }
        return tmp;
    }

    @Override
    public int lastIndexOf(Object o) {
        int tmp;
        tmp = modList.lastIndexOf(o);
        if(tmp != -1){
            return tmp+ unModList.size();
        }
        tmp = unModList.lastIndexOf(o);
        return tmp;
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
        return modList.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return modList.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return unModList.containsAll(c) || modList.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    //check whether we can modify part of the list or not
    private void checkIndex(int index){
        if(index < unModList.size()){
            throw new UnsupportedOperationException("Cannot change unmodifiable part");
        }
    }
}
