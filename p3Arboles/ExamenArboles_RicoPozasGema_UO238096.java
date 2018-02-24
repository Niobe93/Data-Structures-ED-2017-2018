package p3Arboles;

import static org.junit.Assert.*;

//import org.junit.Before;
import org.junit.Test;

public class ExamenArboles_RicoPozasGema_UO238096 {

	Double[] nodosQueHacenRotar = new Double[] { // Esto es un ejemplo, debéis cambiarlo
			/* ejemplo rotaciones del 1 al 5 */ 18.0, 24.0, 20.0, 22.0, 8.0, 
			/* ejemplo rotaciones del 6 al 10 */ 11.0, 10.6,10.6, 26.5, 24.0 };

	AVLTree<Double> arbolExamen = new AVLTree<Double>(); // No crear otro árbol dentro de la prueba

	//@Before // Debe quedar con @Before
	 @Test // Si queréis que se ejecute podéis descomentar esto (y comentar el @Before) // pero antes de entregar, dejadlo como estba
	public void testExamen() {
		// No creeis un árbol aquí dentro, usad el atributo “arbolExamen” de la clase
		// Rellenar con operaciones add y remove hasta completar lo que se solicita en el examen
		 
		 assertEquals(true, arbolExamen.addNode(15.0));	
		 assertEquals(true, arbolExamen.addNode(10.0));	
		 assertEquals(true, arbolExamen.addNode(20.0));	
		 assertEquals(true, arbolExamen.addNode(9.0));	
		 assertEquals(true, arbolExamen.addNode(25.0));	
		 assertEquals(true, arbolExamen.addNode(11.0));	
		 assertEquals(true, arbolExamen.addNode(18.0));
		 assertEquals(true, arbolExamen.addNode(8.0));
		 assertEquals(true, arbolExamen.addNode(28.0));
		 
		 //9
		 
		 
		 
		 
		 //primer remove
		 assertEquals(true, arbolExamen.addNode(12.0));
		 assertEquals(true, arbolExamen.addNode(17.0));
		 
		 //11
		 
		// System.out.println(arbolExamen.toString());
		 
		 
		 assertEquals(true, arbolExamen.addNode(24.0));
		 //12
		 
		 assertEquals(true, arbolExamen.removeNode(17.0));
		 assertEquals(true, arbolExamen.removeNode(18.0));
		// System.out.println(arbolExamen.toString());
		 
		 
		 
		 //2
		 
		 assertEquals(true, arbolExamen.removeNode(24.0));
		 //9
		 assertEquals(true, arbolExamen.addNode(9.5));
		 assertEquals(true, arbolExamen.removeNode(28.0));
		 assertEquals(true, arbolExamen.addNode(24.0));
		 
		 //10
		 
		// System.out.println(arbolExamen.toString());
		// 
		 //
		 assertEquals(true, arbolExamen.addNode(26.0));
		 assertEquals(true, arbolExamen.removeNode(20.0));
		// System.out.println(arbolExamen.toString());
		 
		 //
		 assertEquals(true, arbolExamen.addNode(23.0));
		 assertEquals(true, arbolExamen.addNode(22.0));
		// System.out.println(arbolExamen.toString());
		 
		 //12
		
		 assertEquals(true, arbolExamen.removeNode(12.0));
		 assertEquals(true, arbolExamen.addNode(10.5));
		 assertEquals(true, arbolExamen.addNode(27.0));
		 assertEquals(true, arbolExamen.addNode(25.5));
		 assertEquals(true, arbolExamen.removeNode(9.0));
		 assertEquals(true, arbolExamen.removeNode(9.5));
		
		 assertEquals(true, arbolExamen.removeNode(8.0));
		 //11
		 
		// System.out.println(arbolExamen.toString());
		 
		 
		 
		 
		 //
		 assertEquals(true, arbolExamen.addNode(9.0));
		 assertEquals(true, arbolExamen.removeNode(11.0));
		// System.out.println(arbolExamen.toString());
		 //
		 assertEquals(true, arbolExamen.removeNode(9.0));
		 assertEquals(true, arbolExamen.addNode(10.6));
		// System.out.println(arbolExamen.toString());
		 
		 //11
		 
		 assertEquals(true, arbolExamen.addNode(9.0));
		 assertEquals(true, arbolExamen.addNode(10.1));
		 assertEquals(true, arbolExamen.removeNode(10.6));
		// System.out.println(arbolExamen.toString());
		 
		 //
		 assertEquals(true, arbolExamen.removeNode(25.5));
		 assertEquals(true, arbolExamen.addNode(26.5));
		// System.out.println(arbolExamen.toString());
		 
		 //
		 assertEquals(true, arbolExamen.addNode(22.5));
		 assertEquals(true, arbolExamen.removeNode(24.0));
		 
	}
}
