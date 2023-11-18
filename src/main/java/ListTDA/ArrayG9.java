/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListTDA;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Abeni
 */
public class ArrayG9<E> implements ListGroup9<E> {
    private int capacity = 100;
    private E[] elements = null;
    private int effectiveSize = 0;

    public ArrayG9() {
        this.elements = (E[]) (new Object[capacity]);
        this.effectiveSize = 0;
    }

    private boolean isFull() {
        return elements.length == effectiveSize;
    }

    private void addCapacity() {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        effectiveSize = 0;
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        int index = effectiveSize;
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(this.effectiveSize - 1);
    }

    @Override
    public boolean add(E Element,int index) {
        if (Element == null) {
            throw new NullPointerException("Element provided is null.");
        } else if (index < 0 || index > this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        } else if (isEmpty()) {
            elements[effectiveSize++] = Element;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        effectiveSize++;
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];

        }
        elements[index] = Element;
        return true;

    }

    @Override
    public E remove(int index) {
        E elementToRemove = null;
        if (this.isEmpty() || index >= this.effectiveSize || index<0) {
            throw new IndexOutOfBoundsException();
        } else {
            elementToRemove = elements[index];
            for (int i = index; i < this.effectiveSize - 1; i++) {
                elements[i] = elements[i + 1];
            }
            this.effectiveSize--;
        }
        return elementToRemove;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements[index];
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }
    
    
    public boolean swap(int pos1, int pos2) {
        
        if (isEmpty()){
            return false;
        }

        if (pos1 > effectiveSize || pos2 > effectiveSize){
            return false;
        }
        
        E elemento1 = this.get(pos1);
        
        E elemento2 = this.get(pos2);
        
        E temp = elemento2;
        
        this.set(pos2, elemento1);
        
        this.set(pos1, temp);
        
        return true;
        
    }

    @Override
    public String toString() {
        String result = "[";

        if (isEmpty()){
            return result + "]";
        } else {
            for (int i = 0; i < effectiveSize - 1; i++) {
                result += elements[i].toString() + ", ";
            }
            result += elements[effectiveSize -1].toString() + "]";
        }
        return result;
    }

    public ArrayG9<E> subList(int from, int to) {
        if (from < 0 || from > to || to >= effectiveSize){
            return new ArrayG9<E>();
        }
        ArrayG9<E> subList = new ArrayG9<E>();
        for (int i = from; i <= to; i++){
            subList.add(elements[i]);
        }
        return subList;
    }

    public ArrayG9<E> removeFirstNElements(int n) {
        if (n <= 0 || n >= effectiveSize){
            return this;
        }

        for (int i = 0; i < effectiveSize - n; i++) {   // [ 1 2 3 4 5 ]  5 - 3 = 2
            elements[i] = elements[i+n];                // [ 4 2 3 4 5 ]  5 - 3 = 2
        }                                               // [ 4 5 3 4 5 ]  5 - 3 = 2
        effectiveSize -= n;                             // [ 4 5 ]  5 - 3 = 2

        return this;
    }

    public boolean reverse(){
        if (this.isEmpty()){
            return false;
        }

        ArrayG9<E> aux = new ArrayG9<E>();

        for (int i = effectiveSize - 1; i > -1; i--) {
            aux.add(this.elements[i]);
        }

        for (int i = 0; i < effectiveSize; i++) {
            this.elements[i] = aux.get(i);
        }

        return true;
    }

    public boolean addAll(ListGroup9<E> l)  {
        if(l == null) {
            return false;
        }
        int total_a_tener = effectiveSize + l.size();
        int añadir_capacity = total_a_tener/100 ;
        if (total_a_tener > capacity){
            for (int i = 0 ; i < (añadir_capacity + 1) ; i++ ){
                addCapacity();   
            }
        }
        for (int u = 0; u < l.size() ; u++){
            elements[effectiveSize] = l.get(u) ;
            effectiveSize ++;
        }
        return true;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < effectiveSize; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(E element, Comparator<E> cmp) {
        for (int i = 0; i < effectiveSize; i++) {
            if (cmp.compare(element, elements[i]) == 0) {
                return true;
            }
        }
        return false;
    }    

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < effectiveSize; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                return i;
            }
        }
        return -1; // Elemento no existe
    }

    @Override
    public Iterator<E> iterator() {
        if (isEmpty()) {return null;}
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < effectiveSize;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];
                
            }
            
        };
    }

    public Iterator<E> nthIterator(int n) {
        if (isEmpty()){return null;}
        return new Iterator<E>() {
            private int currentIndex = 0;
            
            @Override
            public boolean hasNext() {
                return currentIndex < effectiveSize;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = elements[currentIndex];
                currentIndex += n; // Skip n elements
                return element;
            }
        };
    }
    
    @Override
    public boolean remove(Object o){
        if(o == null || isEmpty()){
            return false;
        }
        
        for(int x = 0; x<effectiveSize; x++){
            if(o.equals(elements[x])){
                remove(x);
                return true;
            }
        }
        
        return false;
    }
    
}
