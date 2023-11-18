/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListTDA;

/**
 *
 * @author Abeni
 */
public class main {



    public static void main(String[] args) {
        
        LLDouble<Integer> l= new LLDouble<>();
        l.add(1);
        l.add(2);
        l.add(69);
        l.add(5);
        
        Integer numero = 2;
        
        System.out.println(l);
        
        l.remove(numero);
        System.out.println(l);
        
//        for (Integer i: l){
//            System.out.println(i);
//        }

        
}
    
}