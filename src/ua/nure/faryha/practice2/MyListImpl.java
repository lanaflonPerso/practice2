package ua.nure.faryha.practice2;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {
    //=================part1======================
    public static final int TWO= 2;
    private Object[] massive = new Object[TWO];
    private int size1;
    public static final boolean BOL=false;

    public void add(Object e) {
        if (size1 == massive.length) {
            int newSize = size1 * TWO;
            Object[] massive2 = new Object[newSize];
            System.arraycopy(massive, 0, massive2, 0, size1);
            massive = massive2;
        }
        massive[size1++] = e;
    }

    public void clear() {
        massive = new Object[TWO];
        size1 = 0;
    }

    public boolean remove(Object o) {
        boolean bol = BOL;
        if (o != null) {
            for (int i = 0; i < size1; i++) {
                if (o.equals(massive[i])) {
                    return rem(i);
                }
            }
        } else {
            for (int i = 0; i < size1; i++) {
                if (massive[i] == null) {
                    return rem(i);
                }
            }
        }
        return bol;
    }

    private boolean rem(int massivePoint) {
        System.arraycopy(massive, massivePoint + 1, massive, massivePoint, size1 - massivePoint - 1);
        massive[size1] = null;
        size1--;
        return true;
    }

    public boolean contains(Object o) {
        boolean bol = BOL;
        for (int i = 0; i < size1; i++) {
            if (Objects.equals(o, massive[i])) {
                bol = true;
            } else {bol = false;}
        }
        return bol;
    }

    public Object[] toArray() {
        return Arrays.copyOf(massive, size1);
    }


    public int size() {
        return this.size1;
    }

    public boolean containsAll(MyList c) {
        boolean bol = BOL;
        Object[] massive4 = c.toArray();
        for (Object obj : massive4) {
            if (!contains(obj)) {
                bol = false;
            } else {bol = true;}
        }
        return bol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size1; i++) {
            if (Objects.isNull(massive[i])) {
                sb.append("null");
            } else {
                sb.append(massive[i].toString());
            }
            if (i != size1 - 1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //=======================part2===============================
    public Iterator<Object> iterator() {

        return new IteratorImpl();
    }

    public class IteratorImpl implements Iterator<Object> {

        protected int cursorPosition;
        private boolean bol = BOL; //false
        // returns true if the iteration has more elements
        public boolean hasNext() {
            if (cursorPosition < size1) {
                bol = true;
            } else {
                bol = false;
            }
            return bol;
        }
        // returns the next element in the iteration
        public Object next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            bol = true;
            return massive[cursorPosition++];
        }
        // removes from the underlying collection___ the last element returned by this iterator
        @Override
        public void remove() {
            if (!bol) {
                throw new IllegalStateException();
            }
            System.arraycopy(massive, cursorPosition, massive, cursorPosition - 1, massive.length - cursorPosition);
            size1--;
            cursorPosition--;
            bol = false;
        }
    }


    //================part3=======================
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    public class ListIteratorImpl extends IteratorImpl implements ListIterator {

        private boolean bol2 = BOL; //false

        // IMPLEMENT ALL METHODS HERE!!!
        @Override
        public boolean hasPrevious() {
            return cursorPosition != 0;
        }

        @Override
        public Object previous() {
            bol2 = true;
            --cursorPosition;
            return massive[cursorPosition];
        }

        @Override
        public void set(Object e) {
            if (!bol2) {
                throw new IllegalStateException();
            }
            massive[cursorPosition] = e;
            bol2 = false;
        }

    }
}

