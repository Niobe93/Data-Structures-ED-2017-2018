package p3Arboles;

import static org.junit.Assert.*;

import org.junit.Test;

public class EDBinaryHeapTest {

	EDBinaryHeap<Integer> monticulo = new EDBinaryHeap<Integer>(10);

	@Test
	public void addTest() {
		System.out.println("--------------------------------------\n TEST ADD ");

		// CASOS POSITIVOS
		// añadimos varios elementos hasta completar el monticulo
		assertEquals(true, monticulo.add(13));
		assertEquals(true, monticulo.add(21));
		assertEquals(true, monticulo.add(16));
		assertEquals(true, monticulo.add(24));
		assertEquals(true, monticulo.add(31));
		assertEquals(true, monticulo.add(25));
		assertEquals(true, monticulo.add(50));
		assertEquals(true, monticulo.add(65));
		System.out.println(monticulo.toString());

		// añadimos un elemento de mayor prioridad que el de la raiz y comprobamos que
		// toma su lugar
		assertEquals(true, monticulo.add(10));
		System.out.println(monticulo.toString());

		// añadimos un elemento repetido
		assertEquals(true, monticulo.add(10));
		System.out.println(monticulo.toString());

		// intentamos añadir cuando no hay más espacio y cuando el parametro es null
		assertEquals(false, monticulo.add(10));
		assertEquals(false, monticulo.add(null));

	}

	@Test
	public void filtradosTest() {

		System.out.println("--------------------------------------\n TEST FILTRADOS ");

		// añadimos varios elementos
		assertEquals(true, monticulo.add(2));
		assertEquals(true, monticulo.add(3));
		assertEquals(true, monticulo.add(4));
		assertEquals(true, monticulo.add(5));
		assertEquals(true, monticulo.add(6));
		System.out.println(monticulo.toString());

		// Casos de filtrados

		// ADD- filtrado ascendente
		assertEquals(true, monticulo.add(1));
		System.out.println("añadimos 1 para que se produzca filtrado ascendente \n " + monticulo.toString());

		// REMOVE - filtrado descendente
		assertEquals(true, monticulo.remove(3));
		System.out.println("borramos 3 para que se produzca un filtrado descendente \n " + monticulo.toString());

		// REMOVE - filtrado ascendente
		assertEquals(true, monticulo.remove(1));
		System.out.println("borramos 1 para que se produzca un filtrado ascendente \n " + monticulo.toString());

	}

	@Test
	public void pollTest() {

		System.out.println("--------------------------------------\n TEST POLL ");

		// añadimos varios elementos
		assertEquals(true, monticulo.add(1));
		assertEquals(true, monticulo.add(2));
		assertEquals(true, monticulo.add(3));
		assertEquals(true, monticulo.add(4));
		assertEquals(true, monticulo.add(5));
		assertEquals(true, monticulo.add(6));
		System.out.println(monticulo.toString());

		// Vamos sacando los elementos de mayor prioridad del monticulo
		assertTrue(1 == monticulo.poll());
		System.out.println("sacamos el elemento con mayor prioridad (1) \n " + monticulo.toString());
		assertTrue(2 == monticulo.poll());
		System.out.println("sacamos el elemento con mayor prioridad (2) \n " + monticulo.toString());
		assertTrue(3 == monticulo.poll());
		System.out.println("sacamos el elemento con mayor prioridad (3) \n " + monticulo.toString());

		// añadimos un elemento repetido y llamamos a poll

		assertEquals(true, monticulo.add(4));
		System.out.println("añadimos un elemento repetido y llamamos a poll \n " + monticulo.toString());

		assertTrue(4 == monticulo.poll());
		System.out.println("sacamos el elemento con mayor prioridad con repetidos(4) \n " + monticulo.toString());

		assertTrue(4 == monticulo.poll());
		System.out.println("sacamos el elemento con mayor prioridad (4) \n " + monticulo.toString());

		assertTrue(5 == monticulo.poll());
		System.out.println("sacamos el elemento con mayor prioridad (5) \n " + monticulo.toString());

		assertTrue(6 == monticulo.poll());
		System.out.println("sacamos el ultimo elemento y queda un monticulo vacio (6) \n " + monticulo.toString());

		// no quedan mas elementos
		assertNull(monticulo.poll());
	}

	@Test
	public void isEmptyTest() {

		assertTrue(monticulo.isEmpty());
		assertEquals(true, monticulo.add(1));
		assertFalse(monticulo.isEmpty());
		assertEquals(true, monticulo.remove(1));
		assertTrue(monticulo.isEmpty());

	}

	@Test
	public void clearTest() {
		System.out.println("--------------------------------------\n TEST CLEAR ");

		// añadimos varios elementos
		assertEquals(true, monticulo.add(2));
		assertEquals(true, monticulo.add(4));
		assertEquals(true, monticulo.add(3));
		assertEquals(true, monticulo.add(5));
		assertEquals(true, monticulo.add(6));
		assertEquals(true, monticulo.add(4));

		// comprobamos que despues de borrar el monticulo está vacio
		monticulo.clear();
		assertEquals(true, monticulo.isEmpty());
		System.out.println(monticulo.toString());

	}

	@Test
	public void removeTest() {

		System.out.println("--------------------------------------\n TEST REMOVE ");

		// añadimos varios elementos
		assertEquals(true, monticulo.add(1));
		// comprobamos que no podemos borrar un monticulo vacío
		assertTrue(1 == monticulo.poll());
		assertEquals(false, monticulo.remove(1));

		// seguimos añadiendo
		assertEquals(true, monticulo.add(2));
		assertEquals(true, monticulo.add(4));
		assertEquals(true, monticulo.add(3));
		assertEquals(true, monticulo.add(5));
		assertEquals(true, monticulo.add(6));
		assertEquals(true, monticulo.add(4));

		System.out.println(monticulo.toString());

		// borramos un elemento que existe
		assertEquals(true, monticulo.remove(2));
		System.out.println("borramos un elemento que existe (2) \n" + monticulo.toString());
		// borramos un elemento que no existe
		assertEquals(false, monticulo.remove(10));
		// borramos con un parametro null
		assertEquals(false, monticulo.remove(null));
		// borramos un elemento repetido ( debería borrarse el que encuentra primero en
		// el array)
		assertEquals(true, monticulo.remove(4));
		System.out.println("borramos un elemento repetido \n" + monticulo.toString());

	}

}
