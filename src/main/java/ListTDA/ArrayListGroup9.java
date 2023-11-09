
package ListTDA;

public class ArrayListGroup9<E> implements ListGroup9<E>  {
    
    private Node<E> primero;
    private Node<E> ultimo;
    private int tamanio;

    public Node<E> getPrimero() {
        return primero;
    }

    public void setPrimero(Node<E> primero) {
        this.primero = primero;
    }

    public Node<E> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Node<E> ultimo) {
        this.ultimo = ultimo;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }




    public class Node<E> {

        E contenido;
        Node<E> siguiente, anterior;

        public Node(E content) {
            this.contenido = content;
            this.siguiente = null;
            this.anterior = null;
        }
        
        public E getContenido() {
        return contenido;
    }

    public void setContenido(E contenido) {
        this.contenido = contenido;
    }

    public Node<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Node<E> siguiente) {
        this.siguiente = siguiente;
    }

    public Node<E> getAnterior() {
        return anterior;
    }

    public void setAnterior(Node<E> anterior) {
        this.anterior = anterior;
    }

    }
    

    public ArrayListGroup9() {
        this.primero = null;
        this.ultimo = null;
        this.tamanio = 0;
    }
    
    
    private E[] elements;
    private int MAX_SIZE = 100;
    private int effectiveSize;


    @Override
    public int size() {
        if (isEmpty()){
        return 0;
        }
        int contador = 1;
        Node<E> flecha = primero.siguiente;
        
        while(flecha!= primero){
            contador++;
            flecha = flecha.siguiente;
            
        }
        return contador;
    }


    @Override
    public boolean isEmpty() {
        return this.primero == null && this.ultimo == null;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize] = element;
            effectiveSize++;
            return true;
        }
        if (isFull()) {
            addCapacity();
        }
       //{23,5.6} {23,23,5,6}
        // empujar para hacer espacio. Bit shifting
        for (int i = effectiveSize; i >= 1; i--) {
            elements[i] = elements[i-1];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

//    @Override
//    public boolean add(E element) {
//        if (element == null) {
//            return false;
//        }
//        if (isFull()) {
//            addCapacity();
//        }
//        //con humildad
//        //elements[effectiveSize] = element;
//        //effectiveSize++;
//        // con orgullo
//        elements[effectiveSize++] = element;
//        return true;
//    }
    
    
    @Override
    public boolean add(E element){
    
        addLast(element);
        return true;
        
    }
    
    public boolean addLast(E element) {
        Node<E> nuevoNodo = new Node<>(element);
        if (element == null) {
            return false;
        }

        if (isEmpty()) {   //los dos nodos apuntan al nuevoNodo a ser agregado.
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else{
            nuevoNodo.anterior = this.ultimo;
            this.ultimo.siguiente = nuevoNodo;
            nuevoNodo.siguiente = this.primero; 
            this.primero.anterior = nuevoNodo;  
            this.ultimo = nuevoNodo;

        }
        tamanio++;
        return true;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > effectiveSize) {
            // throw new IndexOutOfBoundsException("Invalid index: " + index);
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
            // elements[i+1] = elements[i]; MAL
        }
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E remove(int index) {
        
        
        if(index < 0 || index > effectiveSize)return null;
        E e = this.elements[index];
        
        E[] newElements = (E[])new Object[effectiveSize];
        for (int i = 0,j=0; i < newElements.length; i++) {
            if(i!=index){
                newElements[j] = this.elements[i];
                j++;
            }
        }
        this.effectiveSize--;
        this.elements = newElements;
        
        
        return e;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
          if(index<0|| index >= effectiveSize) return null;
          E temporal = this.elements[index];
          this.elements[index] = element;
          return temporal;
    }

    private void addCapacity() {
        MAX_SIZE = MAX_SIZE * 2;
        E[] newElements = (E[]) new Object[MAX_SIZE];
        // copiando los elementos del arreglo viejo al nuevo
//        for (int i= 0; i<effectiveSize; i++) {
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        this.elements = newElements;
    }

    private boolean isFull() {
        return effectiveSize == MAX_SIZE;
    }

    @Override
    public void reverse() {
        //{1,2,3}  --> [3,2,1]  3
        E[] newElements = (E[]) new Object[effectiveSize];
        for (int i = 0; i < this.effectiveSize; i++) {
            newElements[i] = elements[effectiveSize-1-i]; 
        }
        this.elements = newElements;
        
    }

    @Override
    public ListGroup9<E> subList(int from, int to) {
        ListGroup9<E> newArray = new ArrayListGroup9<>();
        
        boolean cond1 = from >= 0 && from <= this.effectiveSize;
        boolean cond2 = to >=0 && to <= this.effectiveSize;
        if(cond1 & cond2){
           
            for (int i = from,j=0; i <= to; i++) {
                newArray.add(j,this.elements[i]);
                j++;
           }
            //System.out.println(newArray);
            return newArray;
        }     
        return newArray;
        
    }

    @Override
    //s [1, 2,3, 4, 5, 6], y n es 3, la lista queda así: [4, 5, 6].
    public boolean removeFirstNElements(int n) {
        if(isEmpty()==true && (n>0 && n<=effectiveSize)){
            return false;
        }
        int m = this.effectiveSize;
        for (int i = n,j=0; i <m ; i++) {
            this.elements[j] = this.elements[i];
            j++;           
        }
        this.effectiveSize-=n;
        return true;
        
    }
    
        @Override
    public String toString() {

        if (isEmpty()) {
                return "[]";
            }

        StringBuilder cadena = new StringBuilder("[");
        Node<E> actual = primero;
        do {
            cadena.append(actual.contenido);
            if (actual.siguiente != primero) {
                cadena.append(", ");
            }
            actual = actual.siguiente;
        } while (actual != primero);
        cadena.append("]");

        return cadena.toString();


    }

    public boolean remove(Object o){
        return true;
    }

}