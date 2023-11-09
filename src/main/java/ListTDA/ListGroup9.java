
package ListTDA;
/**
 *
 * @author Abeni
 * @param <E>
 */
public interface ListGroup9<E> {
        public int size();

    public boolean isEmpty();

    public void clear();

    public boolean addFirst(E element); // inserta el elemento element al inicio

    public boolean add(E element); // inserta el elemento element al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast(); // remueve el elemento al final de la lista

    public boolean add(int index, E element); // inserta element en la posición index

    public E remove(int index); // remueve y retorna el elemento en la posición index

    public E get(int index); // retorna el elemento ubicado en la posición index

    public E set(int index, E element); // setea el element en la posición index
    
    public boolean remove(Object o);
    
    public void reverse();
    
    public ListGroup9<E> subList(int from, int to);
    
    public boolean removeFirstNElements(int n);

    @Override
    public String toString();
    
}