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

        ListGroup9 list1 = new ArrayListGroup9<Integer>();
        
        // anañadir elementos aquí
        list1.addFirst(2);
        list1.addFirst(1);
        list1.add(2,3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
      
         System.out.println("Primera Lista");
        // llamar a toString aquí
        System.out.println(list1);
        // llamar a reverse, subList, y removeFirstNElements e imprimir resultados
        list1.reverse();
        
        System.out.println("Reverse:");
        System.out.println(list1);
        list1.reverse();
        //subList
        System.out.println("SubList:");
        System.out.println(list1.subList(1, 4));
        //removeFirstNElemebts
        System.out.println("RemoveFirstElements:");
        list1.removeFirstNElements(4);
        System.out.println(list1);
   
        System.out.println("\nSegunda Lista");
        ListGroup9 list2 = new ArrayListGroup9<String>();
        
        // anañadir elementos aquí
        list2.addFirst("Messi");
        list2.add("Mbbappe");
        list2.add(2, "Ronaldo");
        list2.addFirst("Ronaldinho");
        list2.add("Neymar");
        // llamar a toString aquí
        System.out.println(list2);
        // llamar a reverse, subList, y removeFirstNElements e imprimir resultados
        System.out.println("Reverse:");
        list2.reverse();
        System.out.println(list2);
        list2.reverse();
        System.out.println("SubList:");
        System.out.println(list2.subList(1, 3));
        System.out.println("RemoveFirstNElements:");
        list2.removeFirstNElements(4);
        System.out.println(list2);

    }

    
}