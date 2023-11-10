/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListTDA;

/**
 *
 * @author Carlos
 * @param <E>
 */
public class NodeList<E> {
    
    private E contenido;
    private NodeList<E> siguiente;

    public NodeList(E content) {
        this.contenido = content;
    }

    public E getContenido() {
        return contenido;
    }

    public void setContenido(E contenido) {
        this.contenido = contenido;
    }

    public NodeList<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodeList<E> siguiente) {
        this.siguiente = siguiente;
    }
    
}
