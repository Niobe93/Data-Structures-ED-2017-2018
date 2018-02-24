package p2Grafos;


import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	Graph<Integer> graph = new Graph<Integer>(6);
	Graph<String> graph_string = new Graph<String>(8);

	@Test
	public void testAddNode() {

		// probamos a añadir un nodo null
		assertEquals(-1, graph.addNode(null));

		// probamos a añadir un nodo que ya existe
		assertEquals(0, graph.addNode(1));
		assertTrue(graph.existNode(1));

		assertEquals(-1, graph.addNode(1));

		// comprobamos que se añaden los nodos correctamente
		assertEquals(0, graph.addNode(2));
		assertTrue(graph.existNode(2));
		assertEquals(0, graph.addNode(3));
		assertTrue(graph.existNode(3));
		assertEquals(0, graph.addNode(4));
		assertTrue(graph.existNode(4));

		// llamamos al metodo floyd y generamos matrices
		graph.floyd();

		// añadimos otro nodo y comprobamos que las matrices quedan anuladas puesto que
		// ya no servirían
		assertEquals(0, graph.addNode(5));
		assertTrue(graph.existNode(5));
		assertNull(graph.getAFloyd());
		assertNull(graph.getPFloyd());

		// seguimos añadiendo hasta que no haya mas espacio
		assertEquals(0, graph.addNode(6));
		assertTrue(graph.existNode(6)); // hemos añadido hasta el numNodes
		assertEquals(-1, graph.addNode(7)); // no hay espacio
		
		//FALLO??
		//Borramos el nodo añadido y lo volvemos a añadir
		assertEquals(0, graph.removeNode(2));
		assertEquals(0, graph.addNode(2));
	}

	@Test
	public void testExistEdge() {

		graph.addNode(1);
		graph.addNode(2);

		// Pruebas Positivas
		// Caso 1: existe la arista
		graph.addEdge(1, 2, 10);
		assertEquals(true, graph.existEdge(1, 2));

		// Pruebas Negativas
		// No existe la arista
		assertEquals(false, graph.existEdge(2, 3));

		// No existe arista desde null
		assertEquals(false, graph.existEdge(null, 2));

		// No existe arista hasta null
		assertEquals(false, graph.existEdge(1, null));

		// No existe arista desde null hasta null
		assertEquals(false, graph.existEdge(null, null));
	}

	@Test
	public void testGetEdge() {

		for (int i = 0; i < 4; i++)
			graph.addNode(i);

		graph.addEdge(1, 2, 10);
		graph.addEdge(1, 3, 5);

		// Pruebas Positivas
		// Caso 1: Devuelve el peso que tiene asociado una arista
		assertEquals(10, graph.getEdge(1, 2), 0.1);
		assertEquals(5, graph.getEdge(1, 3), 0.1);

		// Pruebas Negativas
		// Caso 1: Intenta devolver el peso de una arista que no existe
		assertEquals(-1.0, graph.getEdge(2, 1), 0.1);
	}

	@Test
	public void testExistNode() {

		for (int i = 0; i < 4; i++)
			graph.addNode(i);

		// Pruebas Positivas
		// Caso 1: Si un nodo existe
		for (int i = 0; i < 4; i++)
			assertEquals(true, graph.existNode(i));

		// Pruebas Negativas
		// Caso 1: Si un nodo no existe
		assertEquals(false, graph.existNode(5));

		// Caso 2: Si un nulo no existe
		assertEquals(false, graph.existNode(null));
	}

	@Test
	public void testAddEdge() {

		// añadimos varios nodos
		for (int i = 0; i < 4; i++)
			graph.addNode(i);
		// CASOS NEGATIVOS
		// probamos con un nodo null
		assertEquals(-1, graph.addEdge(null, 2, 1));
		assertEquals(-1, graph.addEdge(1, null, 1));

		// probamos con un peso de arista menor o igual que 0
		assertEquals(-1, graph.addEdge(1, 2, -1));
		assertEquals(-1, graph.addEdge(1, 2, 0));
		assertEquals(-1, graph.addEdge(1, 2, -11));

		// probamos con los nodos target y source cuando no existen
		assertEquals(-1, graph.addEdge(10, 2, 7));
		assertEquals(-1, graph.addEdge(1, 72, 4));

		// probamos a añadir una arista cuando ya existe una
		assertEquals(0, graph.addEdge(1, 2, 1));
		assertEquals(-1, graph.addEdge(1, 2, 1));

		// CASOS POSITIVOS
		assertEquals(0, graph.addEdge(1, 3, 10));
		assertEquals(10, graph.getEdge(1, 3), 0.1); // comprobamos que nos devuelve el peso

		// llamamos al metodo floyd y generamos matrices
		graph.floyd();

		// añadimos otra arista y comprobamos que las matrices quedan anuladas puesto
		// que no valen
		assertEquals(0, graph.addEdge(3, 2, 8));
		assertEquals(8, graph.getEdge(3, 2), 0.1); // comprobamos que nos devuelve el peso
		assertNull(graph.getAFloyd());
		assertNull(graph.getPFloyd());

		// probamos a añadir una arista cuando source y target son el mismo nodo
		assertEquals(0, graph.addEdge(3, 3, 5));
		assertEquals(5, graph.getEdge(3, 3), 0.1); // comprobamos que nos devuelve el peso

	}

	@Test
	public void testRemoveEdge() {
		// añadimos varios nodos
		for (int i = 0; i < 4; i++)
			graph.addNode(i);
		// añadimos varias aristas
		graph.addEdge(1, 2, 7);
		graph.addEdge(1, 3, 5);

		// probamos a borrar una arista que no existe
		assertEquals(-1, graph.removeEdge(2, 3));
		
		// probamos a borrar una arista entre dos nodos que no existen
		assertEquals(-1, graph.removeEdge(4, 3));
		assertEquals(-1, graph.removeEdge(2, 5));
		assertEquals(-1, graph.removeEdge(null, 3));

		// llamamos al metodo floyd y generamos matrices
		graph.floyd();
		// probamos a borrar una arista que existe
		// comprobamos que las matrices de floyd quedan a null despues de borrar una
		// arista
		assertEquals(0, graph.removeEdge(1, 2));
		assertFalse(graph.existEdge(1, 2));
		assertNull(graph.getAFloyd());
		assertNull(graph.getPFloyd());

	}

	@Test
	public void testRemoveNode() {
		// añadimos varios nodos
		for (int i = 0; i < 5; i++)
			graph.addNode(i);
		// añadimos varias aristas
		graph.addEdge(1, 2, 7);
		graph.addEdge(1, 3, 5);
		graph.addEdge(2, 3, 4);
		graph.addEdge(2, 4, 3);
		graph.addEdge(4, 3, 8);
		
		//Borramos el ultimo nodo
		assertEquals(0, graph.removeNode(4));
		assertFalse(graph.existEdge(2, 4));
		assertFalse(graph.existEdge(4, 3));
		

		System.out.println(graph.toString());

		// probamos a borrar un nodo que no existe
		assertEquals(-1, graph.removeNode(5));

		// probamos a borrar un nodo null
		assertEquals(-1, graph.removeNode(null));

		// llamamos al metodo floyd y generamos matrices
		graph.floyd();

		// probamos a borrar un nodo que existe
		assertEquals(0, graph.removeNode(1));
		assertFalse(graph.existNode(1));
		
		
		// comprobamos que las matrices de floyd quedan a null despues de borrar un nodo
		assertNull(graph.getAFloyd());
		assertNull(graph.getPFloyd());

		// comprobamos que la matriz de aristas y de pesos ha quedado coherente

		// comprobamos que se han borrado todas las aristas que salían/llegaban al nodo
		assertFalse(graph.existEdge(1, 2));
		assertFalse(graph.existEdge(1, 3));
		// comprobamos que las demas aristas y nodos continuan
		assertTrue(graph.existEdge(2, 3));
		assertTrue(graph.existNode(2));
		assertTrue(graph.existNode(3));

		System.out.println(graph.toString());

	}

	@Test
	public void testDijsktra() {
		// construimos el grafo con los nodos correspondientes
		for (int i = 0; i < 6; i++)
			graph.addNode(i);
		// añadimos las aristas con los pesos correspondientes
		graph.addEdge(0, 1, 11);
		graph.addEdge(0, 3, 13);
		graph.addEdge(0, 5, 5);
		graph.addEdge(1, 4, 9);
		graph.addEdge(2, 1, 1);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 3);
		graph.addEdge(4, 2, 12);
		graph.addEdge(5, 1, 4);
		graph.addEdge(5, 4, 54);

		// CASO CON ORIGEN EN 0
		assertArrayEquals(new double[] { 0.0, 9.0, 28.0, 13.0, 16.0, 5.0 }, graph.dijkstra(0), 0.01);

		// CASO CON ORIGEN EN 1
		assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 0.0, 21.0, 23.0, 9.0, Double.POSITIVE_INFINITY },
				graph.dijkstra(1), 0.01);

		// CASO CON ORIGEN EN 2
		assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 1.0, 0.0, 2.0, 5.0, Double.POSITIVE_INFINITY },
				graph.dijkstra(2), 0.01);

		// CASO CON ORIGEN EN 3
		assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 16.0, 15.0, 0.0, 3.0, Double.POSITIVE_INFINITY },
				graph.dijkstra(3), 0.01);

		// CASO CON ORIGEN EN 4
		assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 13.0, 12.0, 14.0, 0.0, Double.POSITIVE_INFINITY },
				graph.dijkstra(4), 0.01);

		// CASO CON ORIGEN EN 5
		assertArrayEquals(new double[] { Double.POSITIVE_INFINITY, 4.0, 25.0, 27.0, 13.0, 0.0 }, graph.dijkstra(5),
				0.01);

		//PARTE 2 AÑADIMOS LA ARISTA DE 4-0-400 Y COMPROBAMOS LOS RESULTADOS //

		graph.addEdge(4, 0, 400);

		// CASO CON ORIGEN EN 0
		assertArrayEquals(new double[] { 0.0, 9.0, 28.0, 13.0, 16.0, 5.0 }, graph.dijkstra(0), 0.01);

		// CASO CON ORIGEN EN 1
		assertArrayEquals(new double[] { 409.0, 0.0, 21.0, 23.0, 9.0, 414.0 }, graph.dijkstra(1), 0.01);

		// CASO CON ORIGEN EN 2
		assertArrayEquals(new double[] { 405.0, 1.0, 0.0, 2.0, 5.0, 410.0 }, graph.dijkstra(2), 0.01);

		// CASO CON ORIGEN EN 3
		assertArrayEquals(new double[] { 403.0, 16.0, 15.0, 0.0, 3.0, 408.0 }, graph.dijkstra(3), 0.01);

		// CASO CON ORIGEN EN 4
		assertArrayEquals(new double[] { 400.0, 13.0, 12.0, 14.0, 0.0, 405.0 }, graph.dijkstra(4), 0.01);

		// CASO CON ORIGEN EN 5
		assertArrayEquals(new double[] { 413.0, 4.0, 25.0, 27.0, 13.0, 0.0 }, graph.dijkstra(5), 0.01);

		// System.out.println(graph.toString());

		// CASOS NEGATIVOS
		// si el nodo origen no existe
		assertEquals(null, graph.dijkstra(15));
		// si el nodo origen es null
		assertEquals(null, graph.dijkstra(null));

	}

	@Test
	public void floydTest() {

		// Llamamos a floyd sin nodos añadidos
		assertEquals(-1, graph.floyd());

		// construimos el grafo con los nodos correspondientes
		for (int i = 0; i < 6; i++)
			graph.addNode(i);

		// añadimos las aristas con los pesos correspondientes
		graph.addEdge(0, 1, 11);
		graph.addEdge(0, 3, 13);
		graph.addEdge(0, 5, 5);
		graph.addEdge(1, 4, 9);
		graph.addEdge(2, 1, 1);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 3);
		graph.addEdge(4, 2, 12);
		graph.addEdge(5, 1, 4);
		graph.addEdge(5, 4, 54);

		// Hacemos la llamada al metodo
		graph.floyd();

		// Comprobamos que se generan las matrices P y A correctamente
		assertArrayEquals(new int[][] { { -1, 5, 4, -1, 3, -1 }, { -1, -1, 4, 4, -1, -1 }, { -1, -1, -1, -1, 3, -1 },
				{ -1, 4, 4, -1, -1, -1 }, { -1, 2, -1, 2, -1, -1 }, { -1, -1, 4, 4, 1, -1 } }, graph.getPFloyd());

		assertArrayEquals(new double[][] { { 0.0, 9, 28, 13, 16, 5 },
				{ Double.POSITIVE_INFINITY, 0.0, 21, 23, 9, Double.POSITIVE_INFINITY },
				{ Double.POSITIVE_INFINITY, 1, 0.0, 2, 5, Double.POSITIVE_INFINITY },
				{ Double.POSITIVE_INFINITY, 16, 15, 0.0, 3, Double.POSITIVE_INFINITY },
				{ Double.POSITIVE_INFINITY, 13, 12, 14, 0.0, Double.POSITIVE_INFINITY },
				{ Double.POSITIVE_INFINITY, 4, 25, 27, 13,0.0} }, graph.getAFloyd());

	}

	@Test
	public void minCostPathTest() {

		// CASOS NEGATIVOS
		// Llamamos al metodo sin nodos añadidos
		assertEquals(-1, graph.minCostPath(0, 0), 0.1);

		// Llamamos al metodo con origen o destino null
		assertEquals(-1, graph.minCostPath(null, 0), 0.1);
		assertEquals(-1, graph.minCostPath(0, null), 0.1);

		// Llamamos al metodo con nodos que no existen
		assertEquals(-1, graph.minCostPath(0, -1), 0.1);
		assertEquals(-1, graph.minCostPath(80, 0), 0.1);

		// CONSTRUIMOS GRAFO
		// añadimos los nodos correspondientes
		for (int i = 0; i < 6; i++)
			graph.addNode(i);

		// añadimos las aristas con los pesos correspondientes
		graph.addEdge(0, 1, 11);
		graph.addEdge(0, 3, 13);
		graph.addEdge(0, 5, 5);
		graph.addEdge(1, 4, 9);
		graph.addEdge(2, 1, 1);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 3);
		graph.addEdge(4, 2, 12);
		graph.addEdge(5, 1, 4);
		graph.addEdge(5, 4, 54);
		
		 

		// CASOS POSITIVOS

		// Calculamos todos los caminos de coste mínimo desde 0
		assertEquals(0.0, graph.minCostPath(0, 0), 0.1);
		assertEquals(9.0, graph.minCostPath(0, 1), 0.1);
		assertEquals(28.0, graph.minCostPath(0, 2), 0.1);
		assertEquals(13.0, graph.minCostPath(0, 3), 0.1);
		assertEquals(16.0, graph.minCostPath(0, 4), 0.1);
		assertEquals(5.0, graph.minCostPath(0, 5), 0.1);

		// Calculamos todos los caminos de coste mínimo desde 1
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(1, 0), 0.1);
		assertEquals(0.0, graph.minCostPath(1, 1), 0.1);
		assertEquals(21.0, graph.minCostPath(1, 2), 0.1);
		assertEquals(23.0, graph.minCostPath(1, 3), 0.1);
		assertEquals(9.0, graph.minCostPath(1, 4), 0.1);
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(1, 5), 0.1);

		// Calculamos todos los caminos de coste mínimo desde 2
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(2, 0), 0.1);
		assertEquals(1.0, graph.minCostPath(2, 1), 0.1);
		assertEquals(0.0, graph.minCostPath(2, 2), 0.1);
		assertEquals(2.0, graph.minCostPath(2, 3), 0.1);
		assertEquals(5.0, graph.minCostPath(2, 4), 0.1);
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(2, 5), 0.1);

		// Calculamos todos los caminos de coste mínimo desde 3
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(3, 0), 0.1);
		assertEquals(16.0, graph.minCostPath(3, 1), 0.1);
		assertEquals(15.0, graph.minCostPath(3, 2), 0.1);
		assertEquals(0.0, graph.minCostPath(3, 3), 0.1);
		assertEquals(3.0, graph.minCostPath(3, 4), 0.1);
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(3, 5), 0.1);

		// Calculamos todos los caminos de coste mínimo desde 4
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(4, 0), 0.1);
		assertEquals(13.0, graph.minCostPath(4, 1), 0.1);
		assertEquals(12.0, graph.minCostPath(4, 2), 0.1);
		assertEquals(14.0, graph.minCostPath(4, 3), 0.1);
		assertEquals(0.0, graph.minCostPath(4, 4), 0.1);
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(4, 5), 0.1);

		// Calculamos todos los caminos de coste mínimo desde 5
		assertEquals(Double.POSITIVE_INFINITY, graph.minCostPath(5, 0), 0.1);
		assertEquals(4.0, graph.minCostPath(5, 1), 0.1);
		assertEquals(25.0, graph.minCostPath(5, 2), 0.1);
		assertEquals(27.0, graph.minCostPath(5, 3), 0.1);
		assertEquals(13.0, graph.minCostPath(5, 4), 0.1);
		assertEquals(0.0, graph.minCostPath(5, 5), 0.1);
	}

	@Test
	public void recorridoProfundidadTest() {
		// CONSTRUIMOS GRAFO
		// añadimos los nodos correspondientes
		for (int i = 0; i < 6; i++)
			graph.addNode(i);

		// añadimos las aristas con los pesos correspondientes
		graph.addEdge(0, 1, 11);
		graph.addEdge(0, 3, 13);
		graph.addEdge(0, 5, 5);
		graph.addEdge(1, 4, 9);
		graph.addEdge(2, 1, 1);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 3);
		graph.addEdge(4, 2, 12);
		graph.addEdge(5, 1, 4);
		graph.addEdge(5, 4, 54);

		// CASOS POSITIVOS
		// Calculamos el recorrido desde 0
		assertEquals("0	1	4	2	3	5	", graph.recorridoProfundidad(0));
		// Calculamos el recorrido desde 1
		assertEquals("1	4	2	3	", graph.recorridoProfundidad(1));
		// Calculamos el recorrido desde 2
		assertEquals("2	1	4	3	", graph.recorridoProfundidad(2));
		// Calculamos el recorrido desde 3
		assertEquals("3	4	2	1	", graph.recorridoProfundidad(3));
		// Calculamos el recorrido desde 4
		assertEquals("4	2	1	3	", graph.recorridoProfundidad(4));
		// Calculamos el recorrido desde 5
		assertEquals("5	1	4	2	3	", graph.recorridoProfundidad(5));

		// CASOS NEGATIVOS
		// Si no existe el nodo o es null
		assertEquals("", graph.recorridoProfundidad(15));
		assertEquals("", graph.recorridoProfundidad(null));

	}

	@Test
	public void pathTest() {

		// CONSTRUIMOS GRAFO
		// añadimos los nodos correspondientes
		for (int i = 0; i < 6; i++)
			graph.addNode(i);

		// añadimos las aristas con los pesos correspondientes
		graph.addEdge(0, 1, 11);
		graph.addEdge(0, 3, 13);
		graph.addEdge(0, 5, 5);
		graph.addEdge(1, 4, 9);
		graph.addEdge(2, 1, 1);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 3);
		graph.addEdge(4, 2, 12);
		graph.addEdge(5, 1, 4);
		graph.addEdge(5, 4, 54);

		// Llamamos al método floyd
		graph.floyd();

		// CASOS NEGATIVOS
		// Llamamos al metodo con nodos null o con nodos que no existen
		assertNull(graph.path(null, 0));
		assertNull(graph.path(0, null));
		assertNull(graph.path(0, -1));
		assertNull(graph.path(20, 5));

		// CASOS POSITIVOS

		// Calculamos el camino entre 0 y el resto

		assertEquals("0	(5.0)	5	(4.0)	1", graph.path(0, 1));
		assertEquals("0	(13.0)	3	(3.0)	4	(12.0)	2", graph.path(0, 2));
		assertEquals("0	(Infinity)	3", graph.path(0, 3));
		assertEquals("0	(13.0)	3	(3.0)	4", graph.path(0, 4));
		assertEquals("0	(Infinity)	5", graph.path(0, 5));

		// Calculamos el camino entre 1 y el resto
		assertEquals("1	(Infinity)	0", graph.path(1, 0));
		assertEquals("1	(9.0)	4	(12.0)	2", graph.path(1, 2));
		assertEquals("1	(9.0)	4	(12.0)	2	(2.0)	3", graph.path(1, 3));
		assertEquals("1	(Infinity)	4", graph.path(1, 4));
		assertEquals("1	(Infinity)	5", graph.path(1, 5));

		// Calculamos el camino entre 2 y el resto
		assertEquals("2	(Infinity)	0", graph.path(2, 0));
		assertEquals("2	(Infinity)	1", graph.path(2, 1));
		assertEquals("2	(Infinity)	3", graph.path(2, 3));
		assertEquals("2	(2.0)	3	(3.0)	4", graph.path(2, 4));
		assertEquals("2	(Infinity)	5", graph.path(2, 5));

		// Calculamos el camino entre 3 y el resto
		assertEquals("3	(Infinity)	0", graph.path(3, 0));
		assertEquals("3	(3.0)	4	(12.0)	2	(1.0)	1", graph.path(3, 1));
		assertEquals("3	(3.0)	4	(12.0)	2", graph.path(3, 2));
		assertEquals("3	(Infinity)	4", graph.path(3, 4));
		assertEquals("3	(Infinity)	5", graph.path(3, 5));

		// Calculamos el camino entre 4 y el resto
		assertEquals("4	(Infinity)	0", graph.path(4, 0));
		assertEquals("4	(12.0)	2	(1.0)	1", graph.path(4, 1));
		assertEquals("4	(Infinity)	2", graph.path(4, 2));
		assertEquals("4	(12.0)	2	(2.0)	3", graph.path(4, 3));
		assertEquals("4	(Infinity)	5", graph.path(4, 5));

		// Calculamos el camino entre 5 y el resto
		assertEquals("5	(Infinity)	0", graph.path(5, 0));
		assertEquals("5	(Infinity)	1", graph.path(5, 1));
		assertEquals("5	(4.0)	1	(9.0)	4	(12.0)	2", graph.path(5, 2));
		assertEquals("5	(4.0)	1	(9.0)	4	(12.0)	2	(2.0)	3", graph.path(5, 3));
		assertEquals("5	(4.0)	1	(9.0)	4", graph.path(5, 4));

		// Si Origen y Destino coinciden muestra: Origen
		assertEquals("0", graph.path(0, 0));
		assertEquals("1", graph.path(1, 1));
		assertEquals("2", graph.path(2, 2));
		assertEquals("3", graph.path(3, 3));
		assertEquals("4", graph.path(4, 4));
		assertEquals("5", graph.path(5, 5));

		System.out.println(graph.toString());
		
	}
	
	
	 
	@Test
	public void seminario3Test() {

		// CONSTRUIMOS GRAFO
		// añadimos los nodos correspondientes
		
		graph_string.addNode("A");
		graph_string.addNode("B");
		graph_string.addNode("C");
		graph_string.addNode("D");
		graph_string.addNode("E");
		graph_string.addNode("F");
		graph_string.addNode("G");
		graph_string.addNode("H");

		// añadimos las aristas con los pesos correspondientes
		graph_string.addEdge("A","E", 7);
		graph_string.addEdge("A","B", 1);
		graph_string.addEdge("B","A", 9);
		graph_string.addEdge("B","C", 3);
		graph_string.addEdge("B","F", 9);
		graph_string.addEdge("C","G", 3);

		graph_string.addEdge("D","F", 4);

		graph_string.addEdge("D","H", 9);

		graph_string.addEdge("E","H", 9);

		graph_string.addEdge("E","G", 6);

		graph_string.addEdge("G","E", 4);

		graph_string.addEdge("G","H", 8);
		graph_string.addEdge("H","F", 8);

		graph_string.floyd();
		
		System.out.println(graph_string.toString());

		
	
} 
}

