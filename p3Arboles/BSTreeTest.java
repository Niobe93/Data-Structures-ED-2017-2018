package p3Arboles;

import static org.junit.Assert.*;

import org.junit.Test;

public class BSTreeTest<T extends Comparable<T>> {

	BSTree<Integer> arbol = new BSTree<Integer>();

	

	@Test
	public void addNodetest() {

		// CASOS POSITIVOS

		// Añadimos nodos y comprobamos que se crean

		assertEquals(true, arbol.addNode(14));
		assertEquals(true, arbol.addNode(47));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		// Nos muestra el arbol con los nodos añadidos
		System.out.println("ARBOL ORIGINAL (tumbado) \n" + arbol.toString());

		// CASOS NEGATIVOS

		// Añadimos un nodo repetido
		assertEquals(false, arbol.addNode(4));
		
		// Añadimos un nodo null
		assertEquals(false, arbol.addNode(null));
	}

	@Test
	public void searchNodeTest() {

		// CASOS POSITIVOS

		// Al añadir nodos que existen comprobamos que al buscarlos aparecen

		assertEquals(true, arbol.addNode(14));
		assertTrue(arbol.searchNode(14).equals(14));

		assertEquals(true, arbol.addNode(47));
		assertTrue(arbol.searchNode(47).equals(47));
		
		assertEquals(true, arbol.addNode(4));
		assertTrue(arbol.searchNode(4).equals(4));
		
		assertEquals(true, arbol.addNode(2));
		assertTrue(arbol.searchNode(2).equals(2));
		
		assertEquals(true, arbol.addNode(34));
		assertTrue(arbol.searchNode(34).equals(34));
		
		assertEquals(true, arbol.addNode(94));
		assertTrue(arbol.searchNode(94).equals(94));
		
		assertEquals(true, arbol.addNode(7));
		assertTrue(arbol.searchNode(7).equals(7));
		// CASOS NEGATIVOS

		// Buscamos un nodo que existe pero no se ha añadido
		assertEquals(null, arbol.searchNode(8));
		
		// Buscamos un nodo que no existe 
		assertEquals(null, arbol.searchNode(78));

		// Buscamos un nodo null
		assertEquals(null, arbol.searchNode(null));

		// Borramos un nodo e intentamos buscarlo
		assertEquals(true, arbol.removeNode(7));
		assertEquals(null, arbol.searchNode(7));

	}

	@Test
	public void removeNode() {

		// Intentamos borrar con un arbol vacío

		assertEquals(false, arbol.removeNode(14)); // se lanzaría la excepcion--false

		
		// Añadimos nodos
		assertEquals(true, arbol.addNode(14));
		// Borramos un nodo que no existe 
		assertEquals(false, arbol.removeNode(78));
		
		
		
		

		// borramos el único elemento del arbol
		assertEquals(true, arbol.removeNode(14));
		assertEquals(null, arbol.searchNode(14));

		// Añadimos mas nodos
		assertEquals(true, arbol.addNode(14));
		assertEquals(true, arbol.addNode(47));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		// CASOS POSITVOS

		// Borramos un nodo que existe por la izquierda de la rama derecha

		assertEquals(true, arbol.removeNode(34));
		assertEquals(null, arbol.searchNode(34));
		System.out
				.println("Borramos un nodo que existe por la izquierda de la rama derecha (34) \n" + arbol.toString());
		arbol.addNode(34);

		// Borramos un nodo que existe por la izquierda de la rama izquierda

		assertEquals(true, arbol.removeNode(2));
		assertEquals(null, arbol.searchNode(2));
		System.out
				.println("Borramos un nodo que existe por la izquierda de la rama izquierda(2) \n" + arbol.toString());
		arbol.addNode(2);

		// Borramos un nodo que existe por la derecha de la rama izquierda

		assertEquals(true, arbol.removeNode(8));
		assertEquals(null, arbol.searchNode(8));
		System.out.println("Borramos un nodo que existe por la derecha de la rama izquierda (8) \n" + arbol.toString());
		arbol.addNode(8);

		// Borramos un nodo que existe por la derecha de la rama derecha

		assertEquals(true, arbol.removeNode(94));
		assertEquals(null, arbol.searchNode(94));
		System.out.println("Borramos un nodo que existe por la derecha de la rama derecha (94)  \n" + arbol.toString());
		arbol.addNode(94);

		// Borramos un nodo de la rama derecha con dos hijos

		assertEquals(true, arbol.removeNode(47));
		assertEquals(null, arbol.searchNode(47));
		System.out.println("Borramos un nodo que existe por la derecha con dos hijos (47)  \n" + arbol.toString());
		arbol.addNode(47);

		// Borramos un nodo de la rama izquierda con dos hijos

		assertEquals(true, arbol.removeNode(4));
		assertEquals(null, arbol.searchNode(4));
		System.out.println("Borramos un nodo que existe por la izquierda con dos hijos (4)  \n" + arbol.toString());

		// Borramos un nodo que no existe

		assertEquals(false, arbol.removeNode(4));
		assertEquals(null, arbol.searchNode(4));
	}

	@Test
	public void preOrderTest() {

		// Realizamos el recorrido con el arbol vacío
		String recorrido = ("");
		assertEquals(recorrido, arbol.preOrder());

		// Añadimos un nodo al árbol
		assertEquals(true, arbol.addNode(14));

		// Probamos el recorrido con un arbol sin hijos
		recorrido = ("14	");
		assertEquals(recorrido, arbol.inOrder());

		// Añadimos los nodos al árbol

		assertEquals(true, arbol.addNode(47));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		// Realizamos el recorrido preOrden y comprobamos
		recorrido = ("14	4	2	8	7	47	34	94	");
		assertEquals(recorrido, arbol.preOrder());
		System.out.println("RECORRIDO  PREORDEN " + arbol.preOrder());
	}

	@Test
	public void inOrderTest() {

		// Realizamos el recorrido con el arbol vacío
		String recorrido = ("");
		assertEquals(recorrido, arbol.inOrder());

		// Añadimos un nodo al árbol
		assertEquals(true, arbol.addNode(14));

		// Probamos el recorrido con un arbol sin hijos
		recorrido = ("14	");
		assertEquals(recorrido, arbol.inOrder());

		// Añadimos mas hijos al arbol

		assertEquals(true, arbol.addNode(47));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		// Realizamos el recorrido inOrden y comprobamos
		recorrido = ("2	4	7	8	14	34	47	94	");
		assertEquals(recorrido, arbol.inOrder());
		System.out.println("RECORRIDO  INORDEN " + arbol.inOrder());
	}

	@Test
	public void postOrderTest() {

		// Realizamos el recorrido con el arbol vacío
		String recorrido = ("");
		assertEquals(recorrido, arbol.postOrder());

		// Añadimos un nodo al árbol
		assertEquals(true, arbol.addNode(14));

		// Probamos el recorrido con un arbol sin hijos
		recorrido = ("14	");
		assertEquals(recorrido, arbol.inOrder());

		// Añadimos los nodos al árbol

		assertEquals(true, arbol.addNode(47));
		assertEquals(true, arbol.addNode(4));
		assertEquals(true, arbol.addNode(2));
		assertEquals(true, arbol.addNode(8));
		assertEquals(true, arbol.addNode(34));
		assertEquals(true, arbol.addNode(94));
		assertEquals(true, arbol.addNode(7));

		System.out.println("RECORRIDO  POSTORDEN " + arbol.postOrder());

		// Realizamos el recorrido postOrden y comprobamos
		recorrido = ("2	7	8	4	34	94	47	14	");
		assertEquals(recorrido, arbol.postOrder());

	}
}
