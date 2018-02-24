package p4Hash;

import static org.junit.Assert.*;

import org.junit.Test;

public class Simulacro {

    ClosedHashTable<Integer> hashExamen = new ClosedHashTable<Integer>(17, 0.9, 0.01, (byte) 0); 
    
    Integer[] valoresQueHacenColisionar=new Integer[]{ 
               // colisiones del 1 al 6 //     17, 19, 18, 17, 4, 20, 
               // rotaciones del 7 al 10 //     23, 18, 18, 18} ;
    };
//    @Before 
    @Test 
    public void testSimulacro() { 
        /*    Ejemplo de una primera posible colisión...          
        assertTrue(h.add(16));  // No hay colisión... 
        assertTrue(h.add(33)); // add(33).Colision.Con.Llena 
//        Si lo que se pedía era un valor que provocase una única colision con celda llena 
//              en un add con valor de retorno true: 
//            habría que poner el 33 como el primer valor del array: valoresQueHacenColisionar 
        System.err.println(h);  
// Hacer algo similar hasta completar lo que se solicita en el examen 
*/ 
    	 assertTrue(hashExamen.add(1));
    	 assertTrue(hashExamen.add(2));
    	 assertTrue(hashExamen.add(3));
    	 assertTrue(hashExamen.add(4));
    	
    	 assertTrue(hashExamen.remove(4));

    	 assertFalse(hashExamen.remove(18));
    	
    	 assertTrue (hashExamen.add(4));
    	 assertTrue(hashExamen.add(18));
    	 
    	 System.out.println(hashExamen.toString());
    	 assertTrue(hashExamen.remove(3));
    	 assertNull(hashExamen.find(20));
    	 
    	 System.out.println(hashExamen.toString());

    	 assertTrue(hashExamen.add(21));
    	 System.out.println(hashExamen.toString());

    	 assertTrue(hashExamen.add(3));
    	 System.out.println(hashExamen.toString());

    	 assertTrue(hashExamen.remove(1));

    	 assertTrue(hashExamen.remove(2));
    	 assertTrue(hashExamen.remove(4));
    	 assertNotNull(hashExamen.find(18));
    	 
    	 System.out.println(hashExamen.toString());
    	 
    	 assertTrue(hashExamen.add(4));
    	 assertTrue(hashExamen.remove(21));
    	 System.out.println(hashExamen.toString());
    	 assertTrue(hashExamen.add(2));
    	 assertTrue(hashExamen.remove(4));
    	 assertNotNull(hashExamen.find(18));
    	 
    	 System.out.println(hashExamen.toString());
    	 assertTrue(hashExamen.add(1));
    	 assertTrue(hashExamen.add(4));
    	 assertTrue(hashExamen.add(35));
    	 System.out.println(hashExamen.toString());

    	 assertTrue(hashExamen.add(5));
    	
    	 assertTrue(hashExamen.add(0));
    	 assertTrue(hashExamen.remove(0));
    	 assertTrue(hashExamen.remove(1));
    	 assertTrue(hashExamen.remove(2));
    	 assertTrue(hashExamen.remove(3));
    	 assertTrue(hashExamen.remove(4));
    	 System.out.println(hashExamen.toString());
    	 
    	 assertFalse(hashExamen.remove(0));
    } 
  
}