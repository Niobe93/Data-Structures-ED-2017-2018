package p4Hash;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClosedHashTableTest {

	@Test
	public void testAdd() {

		// LINEAR
		ClosedHashTable<Integer> tabla = new ClosedHashTable<>(3, 0.5, 0.16, (byte) 0);
		assertEquals(3, tabla.getSize());
		assertEquals(0, tabla.getNumOfElems());
		System.out.println(tabla.toString());

		assertTrue(tabla.add(7));
		assertEquals(1, tabla.getNumOfElems());
		assertEquals(3, tabla.getSize());
		System.out.println(tabla.toString());

		assertTrue(tabla.add(14)); // redispersion
		assertEquals(2, tabla.getNumOfElems());
		assertEquals(7, tabla.getSize());
		System.out.println(tabla.toString());

		assertTrue(tabla.add(21));
		assertEquals(3, tabla.getNumOfElems());
		assertEquals(7, tabla.getSize());
		System.out.println(tabla.toString());

		assertTrue(tabla.add(28));
		assertEquals(4, tabla.getNumOfElems());
		assertEquals(17, tabla.getSize());
		System.out.println(tabla.toString());

		// CUADRATICA
		ClosedHashTable<Integer> tabla2 = new ClosedHashTable<>(7, 0.5, 0.16, (byte) 1);
		assertEquals(7, tabla2.getSize());
		assertEquals(0, tabla2.getNumOfElems());

		// redispersion
		assertTrue(tabla2.add(7));
		assertEquals(1, tabla2.getNumOfElems());
		assertEquals(17, tabla.getSize());
		System.out.println(tabla.toString());

		assertTrue(tabla2.add(14));
		assertEquals(2, tabla2.getNumOfElems());
		assertEquals(17, tabla.getSize());
		System.out.println(tabla.toString());

		assertTrue(tabla2.add(21));
		assertEquals(3, tabla2.getNumOfElems());
		assertEquals(17, tabla.getSize());
		System.out.println(tabla.toString());

		assertTrue(tabla2.add(28));
		assertEquals(4, tabla2.getNumOfElems());
		assertEquals(17, tabla.getSize());
		System.out.println(tabla.toString());

		assertFalse(tabla.add(null)); // añadir un null
		assertFalse(tabla2.add(28)); // añadir un repetido

	}

	@Test
	public void testRemove() {

		// linear
		ClosedHashTable<Integer> tabla = new ClosedHashTable<>(11, 0.5, 0.16, (byte) 0);
		tabla.add(0);
		tabla.add(11);
		tabla.add(22);
		System.out.println(tabla.toString());
		assertEquals(11, tabla.getSize());
		assertEquals(3, tabla.getNumOfElems());

		assertTrue(tabla.remove(11));
		assertEquals(2, tabla.getNumOfElems());
		assertEquals(11, tabla.getSize());
		System.out.println(tabla.toString());

		assertTrue(tabla.remove(22));
		assertEquals(1, tabla.getNumOfElems());
		assertEquals(5, tabla.getSize()); // dispersion inversa
		System.out.println(tabla.toString());

		assertFalse(tabla.remove(null)); // borramos un null
		assertFalse(tabla.remove(43)); // borramos uno que no existe

		// Cuadratica
		ClosedHashTable<Integer> tabla1 = new ClosedHashTable<Integer>(10, 0.5, 0.16, (byte) 1);

		tabla1.add(0);
		tabla1.add(3);
		tabla1.add(11);
		tabla1.add(22);
		System.out.println(tabla1.toString());

		assertEquals(11, tabla1.getSize());
		assertEquals(4, tabla1.getNumOfElems());

		assertTrue(tabla1.remove(3));
		assertEquals(3, tabla1.getNumOfElems());
		assertEquals(11, tabla1.getSize());
		System.out.println(tabla1.toString());

		assertTrue(tabla1.remove(11));
		assertEquals(2, tabla1.getNumOfElems());
		assertEquals(11, tabla1.getSize());
		System.out.println(tabla1.toString());

		assertTrue(tabla1.remove(22)); // dispersion inversa
		assertEquals(1, tabla1.getNumOfElems());
		assertEquals(5, tabla1.getSize());
		System.out.println(tabla1.toString());

		assertTrue(tabla1.remove(0)); // dispersion inversa
		assertEquals(0, tabla1.getNumOfElems());
		assertEquals(5, tabla1.getSize());
		System.out.println(tabla1.toString());

	}

	@Test
	public void testGetLF() {

		ClosedHashTable<Integer> tabla = new ClosedHashTable<>(3, 0.5, 0.16, (byte) 0);

		// REDISPERSION
		tabla.add(0);
		assertEquals(3, tabla.getSize());
		assertEquals(0.333333333, tabla.getLF(), 0.01);

		tabla.add(1);
		assertEquals(7, tabla.getSize());
		assertEquals(0.28571, tabla.getLF(), 0.01);

		tabla.add(2);
		tabla.add(3);

		assertEquals(17, tabla.getSize());
		assertEquals(0.23529, tabla.getLF(), 0.01);

		// REDISPERSION INVERSA

		tabla.remove(3);

		assertEquals(17, tabla.getSize());
		assertEquals(0.176470, tabla.getLF(), 0.01);

		tabla.remove(2);
		assertEquals(7, tabla.getSize());
		assertEquals(0.28571, tabla.getLF(), 0.01);

		tabla.remove(1);
		assertEquals(3, tabla.getSize());
		assertEquals(0.333333, tabla.getLF(), 0.01);

	}

	@Test
	public void testFind() {
		ClosedHashTable<Integer> tabla = new ClosedHashTable<Integer>(7, 0.5, 0.16, (byte) 0);
		tabla.add(13);
		assertNotNull(tabla.find(13));

		assertNull(tabla.find(5));
		assertNull(tabla.find(null));

		tabla.add(2);
		tabla.add(1);
		tabla.add(4);
		assertNotNull(tabla.find(2));
		assertNotNull(tabla.find(1));
		assertNotNull(tabla.find(4));
		tabla.remove(2);
		assertNull(tabla.find(2));

		ClosedHashTable<String> string = new ClosedHashTable<String>(7, 0.5, 0.16, (byte) 0);
		string.add("A");
		assertNotNull(string.find("A"));

		assertNull(string.find("B"));
		assertNull(string.find(null));

		string.add("B");
		assertNotNull(string.find("B"));
		string.add("C");
		assertNotNull(string.find("C"));
		string.add("K");
		assertNotNull(string.find("K"));

		string.remove("B");
		assertNull(string.find("B"));

	}

}