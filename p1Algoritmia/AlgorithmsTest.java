package p1Algoritmia;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlgorithmsTest {

	Algorithms alg = new Algorithms();

	@Test
	public void pow2IterTest() {
		assertEquals(16, alg.pow2Iter(4));
		assertEquals(1, alg.pow2Iter(0));
		assertEquals(128, alg.pow2Iter(7));
		assertEquals(8, alg.pow2Iter(3));
		assertEquals(4, alg.pow2Iter(2));
		assertEquals(65536, alg.pow2Iter(16));

	}

	@Test
	public void pow2Rec1Test() {
		assertEquals(16, alg.pow2Rec1(4));
		assertEquals(1, alg.pow2Rec1(0));
		assertEquals(128, alg.pow2Rec1(7));
		assertEquals(8, alg.pow2Rec1(3));
		assertEquals(4, alg.pow2Rec1(2));
		assertEquals(65536, alg.pow2Rec1(16));
	}

	@Test
	public void pow2Rec2Test() {
		assertEquals(16, alg.pow2Rec2(4));
		assertEquals(1, alg.pow2Rec2(0));
		assertEquals(128, alg.pow2Rec2(7));
		assertEquals(8, alg.pow2Rec2(3));
		assertEquals(4, alg.pow2Rec2(2));
		assertEquals(65536, alg.pow2Rec2(16));
	}

	@Test
	public void pow2Rec3Test() {
		assertEquals(16, alg.pow2Rec3(4));
		assertEquals(1, alg.pow2Rec3(0));
		assertEquals(128, alg.pow2Rec3(7));
		assertEquals(8, alg.pow2Rec3(3));
		assertEquals(4, alg.pow2Rec3(2));
		assertEquals(65536, alg.pow2Rec3(16));
	}

	@Test
	public void pow2Rec4Test() {
		assertEquals(16, alg.pow2Rec4(4));
		assertEquals(1, alg.pow2Rec4(0));
		assertEquals(128, alg.pow2Rec4(7));
		assertEquals(8, alg.pow2Rec4(3));
		assertEquals(4, alg.pow2Rec4(2));
		assertEquals(65536, alg.pow2Rec4(16));
	}
	
	@Test
	public void factTest() {
		assertEquals(24,alg.fact(4));
		assertEquals(1,alg.fact(0));
		assertEquals(1,alg.fact(1));
		assertEquals(40320,alg.fact(8));
		assertEquals(120,alg.fact(5));
		assertEquals(3628800,alg.fact(10));
		assertEquals(479001600,alg.fact(12));
	}
		
		

}
