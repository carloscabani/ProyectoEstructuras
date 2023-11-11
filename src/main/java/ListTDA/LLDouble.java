
package ListTDA;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LLDouble<E> implements ListGroup9<E>  {

    private NodeList<E> first;
    private NodeList<E> last;

    public LLDouble() {
        this.first = null;
        this.last = null;
    }

    public NodeList<E> getFirst() {
        return first;
    }

    public void setFirst(NodeList<E> first) {
        this.first = first;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        nuevo.setSiguiente(this.getFirst());
        if (this.isEmpty()) {
            this.setLast(nuevo);
        }
        this.setFirst(nuevo);
        return true;
    }

    //addLast()
    @Override  
    public boolean add(E e) {    
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        if (this.isEmpty()) {
            this.setFirst(nuevo);
        } else {
            this.getLast().setSiguiente(nuevo);
        }

        this.setLast(nuevo);

        return true;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()) {
            if (size() == 1) {
                last = null;
            }
            NodeList<E> node = first;
            first = first.getSiguiente();
            node.setSiguiente(null);
            return node.getContenido();
        }
        return null;
    }

    @Override
    public E removeLast() {

        if (!isEmpty()) {
            NodeList<E> deleted = last;
            if (first == last) {
                return removeFirst();
            }
            if (first.getSiguiente() == last) {

                last = first;
                first.setSiguiente(null);
                return deleted.getContenido();
            }
            NodeList<E> n;
            for (n = first; n.getSiguiente().getSiguiente() != last; n = n.getSiguiente()) {
            }
            last = n.getSiguiente();
            n.getSiguiente().setSiguiente(null);
            return deleted.getContenido();
        }
        return null;
    }

    @Override
    public int size() {
        int cont = 0;
        if (!isEmpty()) {
            NodeList<E> t;
            for (t = this.getFirst(); t != null; t = t.getSiguiente()) {
                cont++;
            }
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            removeFirst();
        }
    }

    @Override
    public boolean add(E element, int index) {
        int size = size();
        if (element != null && index >= 0 && index <= size) {
            if (index == 0) {
                addFirst(element);
                return true;
            } else if (index == size) {
                add(element);
                return true;
            } else {
                NodeList<E> n = first;
                for (int i = 0; i < index - 1; i++) {
                    n = n.getSiguiente();
                }
                NodeList<E> newNode = new NodeList<E>(element);
                newNode.setSiguiente(n.getSiguiente());
                n.setSiguiente(newNode);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        int size = size();
        if (index >= 0 && index < size) {
            if (index == 0) {
                return removeFirst();
            } else if (index == size - 1) {
                return removeLast();
            } else {
                NodeList<E> n = first;

                for (int i = 0; i < index - 1; i++) {
                    n = n.getSiguiente();
                }
                NodeList<E> deleted = n.getSiguiente();
                n.setSiguiente(n.getSiguiente().getSiguiente());
                deleted.setSiguiente(null);
                return deleted.getContenido();
            }
        }
        return null;
    }

    @Override
    public E get(int index) {
        int size = size();
        if (index >= 0 && index < size) {
            NodeList<E> n = first;
            for (int i = 0; i < index; i++) {
                n = n.getSiguiente();
            }
            return n.getContenido();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        int size = size();
        if (index >= 0 && index < size) {
            NodeList<E> n = first;
            for (int i = 0; i < index; i++) {
                n = n.getSiguiente();
            }
            E old = n.getContenido();
            n.setContenido(element);
            return old;
        }
        return null;
    }

    @Override
    public String toString() {
        
        if(isEmpty()){ return "[]";}
        
        
        String cadena = "[ ";
        NodeList<E> nActual = first;
        
        while(nActual != null){
            cadena += nActual.getContenido();
            if(nActual.getSiguiente() != null){
                cadena+= ", ";
                
            }
        
            nActual = nActual.getSiguiente();
        }
        
        cadena += " ]";
        return cadena;
//        String s = "";
//        NodeList<E> n;
//        for (n = this.getFirst(); n != null; n = n.getSiguiente()) {
//            s += n.getContenido() + " ";
//        }
//        
//        return s;
    }


    public boolean swap(int pos1, int pos2){
        
        if (isEmpty()){
            return false;            
        }

        if (first == null || last == null){
            return false;
        }
        
        if(pos1 > this.size() || pos2 > this.size()){
            return false;
        }
        
        NodeList<E> nodeTmp = new NodeList<>(this.get(pos1));
        
        NodeList<E> nodeTmp2 = new NodeList<>(this.get(pos2));
        
        if (pos1 == 0){
            nodeTmp2.setSiguiente(first.getSiguiente());
            first = nodeTmp2;
        }

        if (pos2 == 0){
            nodeTmp.setSiguiente(first.getSiguiente());
            first = nodeTmp;
        }

        NodeList<E> n = first;

        for (int i = 0; i < this.size(); i++) {

            if (i+1 == pos1 && (n.getSiguiente() != null)){
                nodeTmp2.setSiguiente(n.getSiguiente().getSiguiente());
                n.setSiguiente(nodeTmp2);
            }
            if (i+1 == pos2 && (n.getSiguiente() != null)){
                nodeTmp.setSiguiente(n.getSiguiente().getSiguiente());
                n.setSiguiente(nodeTmp);
            }
            n = n.getSiguiente();
            
        }

        return true;
    }
    
    public boolean reverse(){
        if (this.isEmpty()){
            return false;            
        }
        
        NodeList<E> current = first;
        NodeList<E> previo = null;
        NodeList<E> siguiente = null;
        
        while (current != null) {
            siguiente = current.getSiguiente();
            current.setSiguiente(previo);
            previo = current;
            current = siguiente;
        }
        first = previo;
        
        return true;
    }

    
    public Iterator<E> iterator() {
        if(isEmpty()) return null;
        return new Iterator<E>(){
            
            NodeList<E> node = first;
            
            @Override
            public boolean hasNext(){
                return node != null;
            }
            

            @Override
            public E next() {
                if(hasNext()){
                    E data = node.getContenido();
                    node = node.getSiguiente();
                    return data;
                }
                return null;
            }
        
        };
    }

    @Override
    public boolean contains(E e){
        if(isEmpty()) return false;
        NodeList<E> node = first;
        while(node!=null){
            if(e.equals(node.getContenido())) return true;
            node = node.getSiguiente();
        }
        return false;
    }

    @Override
    public int indexOf(E e) {
        if(e == null) return -2;
        if(isEmpty()) return -1;
        if(e == last.getContenido()) return size()-1;
        
        NodeList<E> node = first;
        int index = 0;
        while(node != last){
            if(node.getContenido() == e) return index;
            index++;
            node = node.getSiguiente();
        }
        
        return -2;
    }

    /* Metodo reverse Iterator */

    public Iterator<E> reverseIterator(){
        if(this.isEmpty()){
            return null;
        }

        Iterator<E> itAux = this.iterator();
        LLDouble<E> lAux = new LLDouble<>();

        while(itAux.hasNext()){
            lAux.addFirst(itAux.next());
        }

        return new Iterator<E>() {
            NodeList<E> node = lAux.first;

            @Override
            public boolean hasNext(){
                return node != null;
            }

            @Override
            public E next(){
                if (hasNext()) {
                    E data = node.getContenido();
                    node = node.getSiguiente();
                    return data;
                }
                return null;
            }
        };
    }

    public Iterator<E> nthIterator(int n) {
        if (isEmpty()){return null;}
        return new Iterator<E>() {
            private NodeList<E> current = first;
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.getContenido();
                for (int i = 0; i < n && current != null; i++) {
                    current = current.getSiguiente();
                    currentIndex++;
                }

                return element;
            }
        };
    }


}