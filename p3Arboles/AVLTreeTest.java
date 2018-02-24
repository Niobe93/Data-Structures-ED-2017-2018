package p3Arboles;

import static org.junit.Assert.*;

import org.junit.Test;

public class AVLTreeTest {

	AVLTree<Integer> arbol = new AVLTree<>();

	@Test
	public void addTest() {

		// CASOS POSITIVOS
		assertEquals(true, arbol.addNode(13));
		assertEquals(true, arbol.addNode(5));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));
		// Nos muestra el arbol con los nodos a�adidos
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());

		// CASOS NEGATIVOS

		// A�adimos un nodo repetido
		assertEquals(false, arbol.addNode(4));

		// A�adimos un nodo null
		assertEquals(false, arbol.addNode(null));
	}

	@Test
	public void rotacionesSimplesTest() {

		// ROTACION SIMPLE IZQUIERDA
		System.out.println("------------------------------------------------------------------------");
		System.out.println("ROTACION SIMPLE IZQUIERDA\n");
		assertEquals(true, arbol.addNode(14));
		// System.out.println("A�adimos 14\n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(5));
		System.out.println("Arbol antes de la rotacion\n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(4));
		System.out.println("Al a�adir 4 se produce una rotaci�n simple a la izquierda \n\n" + arbol.toString());

		// ROTACION SIMPLE DERECHA
		System.out.println("---------------------------------------------------------------");
		System.out.println("ROTACION SIMPLE DERECHA\n");
		assertEquals(true, arbol.addNode(16));
		System.out.println("Arbol antes de la rotacion\n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(17));
		System.out.println("Al a�adir 17 se produce una rotaci�n simple a la derecha\n\n" + arbol.toString());

	}

	@Test
	public void rotacionesDobleIzquierdaTest() {

		// ROTACION DOBLE IZQUIERDA
		System.out.println("------------------------------------------------------------------------");
		System.out.println("ROTACION DOBLE IZQUIERDA\n");
		assertEquals(true, arbol.addNode(14));
		// System.out.println("A�adimos 14\n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(15));
		// System.out.println("A�adimos 15\n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(7));
		// System.out.println("A�adimos 7 \n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(6));
		// System.out.println("A�adimos 6 \n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(9));
		System.out.println("Arbol antes de la rotacion \n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(10));
		System.out.println("Al a�adir 10 se produce una doble rotacion a la izquierda\n\n" + arbol.toString());
	}

	@Test
	public void rotacionesDobleDerechaTest() {

		// ROTACION DOBLE DERECHA
		System.out.println("------------------------------------------------------------------------");
		System.out.println("ROTACION DOBLE DERECHA\n");
		assertEquals(true, arbol.addNode(14));
		// System.out.println("A�adimos 14\n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(7));
		// System.out.println("A�adimos 7\n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(18));
		// System.out.println("A�adimos 18 \n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(16));
		// System.out.println("A�adimos 16 \n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(20));
		System.out.println("Arbol antes de la rotacion \n\n" + arbol.toString());
		assertEquals(true, arbol.addNode(15));
		System.out.println("Al a�adir 15 se produce una doble rotacion a la derecha\n\n" + arbol.toString());

	}

	@Test
	public void generalRemoveTest() {

		// Intentamos borrar con un parametro null

		assertEquals(false, arbol.removeNode(null)); // se lanzar�a la excepcion--false

		// Intentamos borrar con un arbol vac�o
		assertEquals(false, arbol.removeNode(3));

		// borramos el �nico elemento del arbol
		assertEquals(true, arbol.addNode(14));
		assertEquals(true, arbol.removeNode(14));
		assertEquals(null, arbol.searchNode(14));
		
		//A�adimos nodos
		assertEquals(true, arbol.addNode(14));
		assertEquals(true, arbol.addNode(5));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		// Borramos un nodo que no existe
		assertEquals(false, arbol.removeNode(78));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());

		// BORRAMOS NODO SIN HIJOS
		System.out.println("Borramos un nodo sin hijos (2) que produce una rotaci�n simple dch\n");
		assertEquals(true, arbol.removeNode(2));
		assertEquals(null, arbol.searchNode(2));
		System.out.println(arbol.toString());

	}

	@Test
	public void removeDoshijosTest() {

		// A�adimos varios nodos al arbol
		assertEquals(true, arbol.addNode(14));
		assertEquals(true, arbol.addNode(5));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE CON DOS HIJOS \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());

		// borramos un nodo con dos hijos
		System.out.println("Borramos un nodo con dos hijos (14) que no produce rotaciones\n");
		assertEquals(true, arbol.removeNode(14));
		assertEquals(null, arbol.searchNode(14));
		System.out.println(arbol.toString());

	}

	@Test
	public void removeHijoIzquierdaTest() {
		// A�adimos varios nodos al arbol
		assertEquals(true, arbol.addNode(14));
		assertEquals(true, arbol.addNode(5));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE CON UN HIJO POR LA IZQUIERDA \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());
		// borramos un nodo con un hijo por la izquierda
		System.out.println("Borramos un nodo con un hijo (4) que produce una rotaci�n simple dch\n");
		assertEquals(true, arbol.removeNode(4));
		assertEquals(null, arbol.searchNode(4));
		System.out.println(arbol.toString());

	}

	@Test
	public void removeHijoDerechaTest() {
		// A�adimos varios nodos al arbol
		assertEquals(true, arbol.addNode(14));
		assertEquals(true, arbol.addNode(6));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(5));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE CON UN HIJO POR LA DERECHA \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());
		// borramos un nodo con un hijo por la izquierda
		System.out.println("Borramos un nodo con un hijo (4) que produce una rotaci�n simple dch\n");
		assertEquals(true, arbol.removeNode(4));
		assertEquals(null, arbol.searchNode(4));
		System.out.println(arbol.toString());

	}
}
