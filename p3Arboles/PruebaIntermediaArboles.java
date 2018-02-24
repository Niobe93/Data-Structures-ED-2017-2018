package p3Arboles;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PruebaIntermediaArboles {

	private AVLTree<Integer> tree;
	
	@Before
	public void setUp() {
		tree = new AVLTree<Integer>();
	}

	/**
	 * Método que comprueba que se eliminan correctamente todos los nodos
	 */
    @Test
    public void testRemoveUltimoNodo() {
    	
    	Integer[] array = {50,30,70,20,40,60,80};
    	for (Integer ent: array) { tree.addNode(ent); }
    	
    	for (Integer ent: array) {
    		assertFalse(tree.removeNode(null));
    		assertTrue(tree.removeNode(ent));
    		assertFalse(tree.removeNode(ent));
    	}
    	//assertNull(tree.getRoot());
    }


    /**
     * Método que comprueba que las rotaciones simples por la izquierda al añadir nodos se realizan correctamente 
     */
    @Test
    public void test_10_RotacionesSimplesIzquierdaAdd () {
    	String[] recorridos = {"24:FB=0\t",
    			"24:FB=-1\t23:FB=0\t",
    			"23:FB=0\t22:FB=0\t24:FB=0\t",
    			"23:FB=-1\t22:FB=-1\t21:FB=0\t24:FB=0\t",
    			"23:FB=-1\t21:FB=0\t20:FB=0\t22:FB=0\t24:FB=0\t",
    			"21:FB=0\t20:FB=-1\t19:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"21:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"21:FB=-1\t19:FB=-1\t18:FB=-1\t17:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"21:FB=-1\t19:FB=-1\t17:FB=0\t16:FB=0\t18:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"21:FB=-1\t17:FB=0\t16:FB=-1\t15:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"21:FB=-1\t17:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"17:FB=0\t15:FB=-1\t14:FB=-1\t13:FB=0\t16:FB=0\t21:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"17:FB=0\t15:FB=-1\t13:FB=0\t12:FB=0\t14:FB=0\t16:FB=0\t21:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t",
    			"17:FB=0\t13:FB=0\t12:FB=-1\t11:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t21:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t23:FB=0\t22:FB=0\t24:FB=0\t"};
    	for (int i = 24; i > 10; i--) {
    		tree.addNode(i);
    		assertEquals(recorridos[24-i], tree.preOrder());
    		
    	}
    }
    

    /**
     * Método que comprueba que las rotaciones simples por la derecha al añadir nodos se realizan correctamente
     */
    @Test
    public void test_10_RotacionesSimplesDerechaAdd() {
    	String[] recorridos = {
    		  "10:FB=0\t",
    		  "10:FB=1\t11:FB=0\t",
    		  "11:FB=0\t10:FB=0\t12:FB=0\t",
    		  "11:FB=1\t10:FB=0\t12:FB=1\t13:FB=0\t",
    		  "11:FB=1\t10:FB=0\t13:FB=0\t12:FB=0\t14:FB=0\t",
    		  "13:FB=0\t11:FB=0\t10:FB=0\t12:FB=0\t14:FB=1\t15:FB=0\t",
    		  "13:FB=0\t11:FB=0\t10:FB=0\t12:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t",
    		  "13:FB=1\t11:FB=0\t10:FB=0\t12:FB=0\t15:FB=1\t14:FB=0\t16:FB=1\t17:FB=0\t",
    		  "13:FB=1\t11:FB=0\t10:FB=0\t12:FB=0\t15:FB=1\t14:FB=0\t17:FB=0\t16:FB=0\t18:FB=0\t",
    		  "13:FB=1\t11:FB=0\t10:FB=0\t12:FB=0\t17:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t18:FB=1\t19:FB=0\t",
    		  "13:FB=1\t11:FB=0\t10:FB=0\t12:FB=0\t17:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t",
    		  "17:FB=0\t13:FB=0\t11:FB=0\t10:FB=0\t12:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t19:FB=1\t18:FB=0\t20:FB=1\t21:FB=0\t",
    		  "17:FB=0\t13:FB=0\t11:FB=0\t10:FB=0\t12:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t19:FB=1\t18:FB=0\t21:FB=0\t20:FB=0\t22:FB=0\t",
    		  "17:FB=0\t13:FB=0\t11:FB=0\t10:FB=0\t12:FB=0\t15:FB=0\t14:FB=0\t16:FB=0\t21:FB=0\t19:FB=0\t18:FB=0\t20:FB=0\t22:FB=1\t23:FB=0\t"};
    	for (int i = 10; i < 24; i++) {
    		tree.addNode(i);
    		assertEquals(recorridos[i -10], tree.preOrder());
    	}

    }

    /**
     * Método que comprueba que las rotaciones dobles izquierda al añadir nodos se realizan correctamente 
     */
	@Test
	public void test_10_RotacionesDoblesIzquierdaAdd() {
		Integer[] valores = {265,65,165,215,15,240,40,250,200,50,0,255,205,55,5,257,207,57,260,210,60};
		String[] recorridos = {
				"265:FB=0\t",
				"265:FB=-1\t65:FB=0\t",
				"165:FB=0\t65:FB=0\t265:FB=0\t",
				"165:FB=1\t65:FB=0\t265:FB=-1\t215:FB=0\t",
				"165:FB=0\t65:FB=-1\t15:FB=0\t265:FB=-1\t215:FB=0\t",
				"165:FB=0\t65:FB=-1\t15:FB=0\t240:FB=0\t215:FB=0\t265:FB=0\t",
				"165:FB=0\t40:FB=0\t15:FB=0\t65:FB=0\t240:FB=0\t215:FB=0\t265:FB=0\t",
				"165:FB=1\t40:FB=0\t15:FB=0\t65:FB=0\t240:FB=1\t215:FB=0\t265:FB=-1\t250:FB=0\t",
				"165:FB=1\t40:FB=0\t15:FB=0\t65:FB=0\t240:FB=0\t215:FB=-1\t200:FB=0\t265:FB=-1\t250:FB=0\t",
				"165:FB=0\t40:FB=1\t15:FB=0\t65:FB=-1\t50:FB=0\t240:FB=0\t215:FB=-1\t200:FB=0\t265:FB=-1\t250:FB=0\t",
				"165:FB=0\t40:FB=0\t15:FB=-1\t0:FB=0\t65:FB=-1\t50:FB=0\t240:FB=0\t215:FB=-1\t200:FB=0\t265:FB=-1\t250:FB=0\t",
				"165:FB=0\t40:FB=0\t15:FB=-1\t0:FB=0\t65:FB=-1\t50:FB=0\t240:FB=0\t215:FB=-1\t200:FB=0\t255:FB=0\t250:FB=0\t265:FB=0\t",
				"165:FB=0\t40:FB=0\t15:FB=-1\t0:FB=0\t65:FB=-1\t50:FB=0\t240:FB=0\t205:FB=0\t200:FB=0\t215:FB=0\t255:FB=0\t250:FB=0\t265:FB=0\t",
				"165:FB=0\t40:FB=0\t15:FB=-1\t0:FB=0\t55:FB=0\t50:FB=0\t65:FB=0\t240:FB=0\t205:FB=0\t200:FB=0\t215:FB=0\t255:FB=0\t250:FB=0\t265:FB=0\t",
				"165:FB=0\t40:FB=0\t5:FB=0\t0:FB=0\t15:FB=0\t55:FB=0\t50:FB=0\t65:FB=0\t240:FB=0\t205:FB=0\t200:FB=0\t215:FB=0\t255:FB=0\t250:FB=0\t265:FB=0\t",
				"165:FB=1\t40:FB=0\t5:FB=0\t0:FB=0\t15:FB=0\t55:FB=0\t50:FB=0\t65:FB=0\t240:FB=1\t205:FB=0\t200:FB=0\t215:FB=0\t255:FB=1\t250:FB=0\t265:FB=-1\t257:FB=0\t",
				"165:FB=1\t40:FB=0\t5:FB=0\t0:FB=0\t15:FB=0\t55:FB=0\t50:FB=0\t65:FB=0\t240:FB=0\t205:FB=1\t200:FB=0\t215:FB=-1\t207:FB=0\t255:FB=1\t250:FB=0\t265:FB=-1\t257:FB=0\t",
				"165:FB=0\t40:FB=1\t5:FB=0\t0:FB=0\t15:FB=0\t55:FB=1\t50:FB=0\t65:FB=-1\t57:FB=0\t240:FB=0\t205:FB=1\t200:FB=0\t215:FB=-1\t207:FB=0\t255:FB=1\t250:FB=0\t265:FB=-1\t257:FB=0\t",
				"165:FB=0\t40:FB=1\t5:FB=0\t0:FB=0\t15:FB=0\t55:FB=1\t50:FB=0\t65:FB=-1\t57:FB=0\t240:FB=0\t205:FB=1\t200:FB=0\t215:FB=-1\t207:FB=0\t255:FB=1\t250:FB=0\t260:FB=0\t257:FB=0\t265:FB=0\t",
				"165:FB=0\t40:FB=1\t5:FB=0\t0:FB=0\t15:FB=0\t55:FB=1\t50:FB=0\t65:FB=-1\t57:FB=0\t240:FB=0\t205:FB=1\t200:FB=0\t210:FB=0\t207:FB=0\t215:FB=0\t255:FB=1\t250:FB=0\t260:FB=0\t257:FB=0\t265:FB=0\t",
				"165:FB=0\t40:FB=1\t5:FB=0\t0:FB=0\t15:FB=0\t55:FB=1\t50:FB=0\t60:FB=0\t57:FB=0\t65:FB=0\t240:FB=0\t205:FB=1\t200:FB=0\t210:FB=0\t207:FB=0\t215:FB=0\t255:FB=1\t250:FB=0\t260:FB=0\t257:FB=0\t265:FB=0\t"

		};
		
		for (int i = 0; i < valores.length; i++) {
			tree.addNode(valores[i]);
			assertEquals(recorridos[i], tree.preOrder());
		}
	}

	/**
	 * Método que comprueba que las rotaciones dobles derecha al añadir nodos se realizan correctamente
	 */
	@Test
	public void test_10_RotacionesDoblesDerechaAdd() {
		Integer[] valores = {100,300,200,150,350,125,325,115,165,315,365,110,160,310,360,108,158,308,105,155,305};
		String[] recorridos = {
				"100:FB=0\t",
				"100:FB=1\t300:FB=0\t",
				"200:FB=0\t100:FB=0\t300:FB=0\t",
				"200:FB=-1\t100:FB=1\t150:FB=0\t300:FB=0\t",
				"200:FB=0\t100:FB=1\t150:FB=0\t300:FB=1\t350:FB=0\t",
				"200:FB=0\t125:FB=0\t100:FB=0\t150:FB=0\t300:FB=1\t350:FB=0\t",
				"200:FB=0\t125:FB=0\t100:FB=0\t150:FB=0\t325:FB=0\t300:FB=0\t350:FB=0\t",
				"200:FB=-1\t125:FB=-1\t100:FB=1\t115:FB=0\t150:FB=0\t325:FB=0\t300:FB=0\t350:FB=0\t",
				"200:FB=-1\t125:FB=0\t100:FB=1\t115:FB=0\t150:FB=1\t165:FB=0\t325:FB=0\t300:FB=0\t350:FB=0\t",
				"200:FB=0\t125:FB=0\t100:FB=1\t115:FB=0\t150:FB=1\t165:FB=0\t325:FB=-1\t300:FB=1\t315:FB=0\t350:FB=0\t",
				"200:FB=0\t125:FB=0\t100:FB=1\t115:FB=0\t150:FB=1\t165:FB=0\t325:FB=0\t300:FB=1\t315:FB=0\t350:FB=1\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=0\t100:FB=0\t115:FB=0\t150:FB=1\t165:FB=0\t325:FB=0\t300:FB=1\t315:FB=0\t350:FB=1\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=0\t100:FB=0\t115:FB=0\t160:FB=0\t150:FB=0\t165:FB=0\t325:FB=0\t300:FB=1\t315:FB=0\t350:FB=1\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=0\t100:FB=0\t115:FB=0\t160:FB=0\t150:FB=0\t165:FB=0\t325:FB=0\t310:FB=0\t300:FB=0\t315:FB=0\t350:FB=1\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=0\t100:FB=0\t115:FB=0\t160:FB=0\t150:FB=0\t165:FB=0\t325:FB=0\t310:FB=0\t300:FB=0\t315:FB=0\t360:FB=0\t350:FB=0\t365:FB=0\t",
				"200:FB=-1\t125:FB=-1\t110:FB=-1\t100:FB=1\t108:FB=0\t115:FB=0\t160:FB=0\t150:FB=0\t165:FB=0\t325:FB=0\t310:FB=0\t300:FB=0\t315:FB=0\t360:FB=0\t350:FB=0\t365:FB=0\t",
				"200:FB=-1\t125:FB=0\t110:FB=-1\t100:FB=1\t108:FB=0\t115:FB=0\t160:FB=-1\t150:FB=1\t158:FB=0\t165:FB=0\t325:FB=0\t310:FB=0\t300:FB=0\t315:FB=0\t360:FB=0\t350:FB=0\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=-1\t100:FB=1\t108:FB=0\t115:FB=0\t160:FB=-1\t150:FB=1\t158:FB=0\t165:FB=0\t325:FB=-1\t310:FB=-1\t300:FB=1\t308:FB=0\t315:FB=0\t360:FB=0\t350:FB=0\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=-1\t105:FB=0\t100:FB=0\t108:FB=0\t115:FB=0\t160:FB=-1\t150:FB=1\t158:FB=0\t165:FB=0\t325:FB=-1\t310:FB=-1\t300:FB=1\t308:FB=0\t315:FB=0\t360:FB=0\t350:FB=0\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=-1\t105:FB=0\t100:FB=0\t108:FB=0\t115:FB=0\t160:FB=-1\t155:FB=0\t150:FB=0\t158:FB=0\t165:FB=0\t325:FB=-1\t310:FB=-1\t300:FB=1\t308:FB=0\t315:FB=0\t360:FB=0\t350:FB=0\t365:FB=0\t",
				"200:FB=0\t125:FB=0\t110:FB=-1\t105:FB=0\t100:FB=0\t108:FB=0\t115:FB=0\t160:FB=-1\t155:FB=0\t150:FB=0\t158:FB=0\t165:FB=0\t325:FB=-1\t310:FB=-1\t305:FB=0\t300:FB=0\t308:FB=0\t315:FB=0\t360:FB=0\t350:FB=0\t365:FB=0\t"
		};
		for (int i = 0; i < valores.length; i++) {
			tree.addNode(valores[i]);
			assertEquals(recorridos[i], tree.preOrder());
		}
	}
	
	
	
	/**
	 * Método que comprueba que se realizan correctamente las rotaciones izquierda dobles al borrar
	 */
	@Test
	public void test_3_RotacionesDoblesIzquierdaRemove () {
		
		Integer[] array = {13,4,15,7};
		for (Integer ent: array) {
			tree.addNode(ent);
		}
		
		assertEquals("13:FB=-1\t4:FB=1\t7:FB=0\t15:FB=0\t", tree.preOrder());
		tree.removeNode(15);
		assertEquals("7:FB=0\t4:FB=0\t13:FB=0\t", tree.preOrder());
		tree.addNode(6);
		assertEquals("7:FB=-1\t4:FB=1\t6:FB=0\t13:FB=0\t", tree.preOrder());
		tree.removeNode(13);
		assertEquals("6:FB=0\t4:FB=0\t7:FB=0\t", tree.preOrder());
		tree.addNode(5);
		assertEquals("6:FB=-1\t4:FB=1\t5:FB=0\t7:FB=0\t", tree.preOrder());
		tree.removeNode(7);
		assertEquals("5:FB=0\t4:FB=0\t6:FB=0\t", tree.preOrder());
		
	}

	/**
	 * Método que comprueba que se realizan correctamente las rotaciones derecha dobles al borrar
	 */
	@Test
	public void test_3_RotacionesDoblesDerechaRemove () {
		Integer[] array = {2,11,0,8};
		for (Integer ent: array) {
			tree.addNode(ent);
		}
		
		tree.removeNode(0);
		assertEquals("8:FB=0\t2:FB=0\t11:FB=0\t", tree.preOrder());
		tree.addNode(9);
		assertEquals("8:FB=1\t2:FB=0\t11:FB=-1\t9:FB=0\t", tree.preOrder());
		tree.removeNode(2);
		assertEquals("9:FB=0\t8:FB=0\t11:FB=0\t", tree.preOrder());
		tree.addNode(10);
		assertEquals("9:FB=1\t8:FB=0\t11:FB=-1\t10:FB=0\t", tree.preOrder());
		tree.removeNode(8);
		assertEquals("10:FB=0\t9:FB=0\t11:FB=0\t", tree.preOrder());
	}
	
	/**
	 * Método que comprueba que las rotaciones simples izquierda al borrar un hijo desequilibrado se hacen correctamente
	 */
	@Test
	public void test_3_RotacionesSimplesIzquierdaRemoveHijoDesequilibrado () {
		Integer[] array = {50,30,70,20,40,60,80,15,25,35,45,55,65,75,85,12,32,52};
		
		for(Integer entero: array) { tree.addNode(entero);}
		
		assertEquals("50:FB=0\t30:FB=0\t20:FB=-1\t15:FB=-1\t12:FB=0\t25:FB=0\t40:FB=-1\t35:FB=-1\t32:FB=0\t45:FB=0\t70:FB=-1\t60:FB=-1\t55:FB=-1\t52:FB=0\t65:FB=0\t80:FB=0\t75:FB=0\t85:FB=0\t",tree.preOrder());
		tree.removeNode(25);
		assertEquals("50:FB=0\t30:FB=1\t15:FB=0\t12:FB=0\t20:FB=0\t40:FB=-1\t35:FB=-1\t32:FB=0\t45:FB=0\t70:FB=-1\t60:FB=-1\t55:FB=-1\t52:FB=0\t65:FB=0\t80:FB=0\t75:FB=0\t85:FB=0\t",tree.preOrder());
		tree.removeNode(45);
		assertEquals("50:FB=1\t30:FB=0\t15:FB=0\t12:FB=0\t20:FB=0\t35:FB=0\t32:FB=0\t40:FB=0\t70:FB=-1\t60:FB=-1\t55:FB=-1\t52:FB=0\t65:FB=0\t80:FB=0\t75:FB=0\t85:FB=0\t",tree.preOrder());
		tree.removeNode(65);
		assertEquals("50:FB=0\t30:FB=0\t15:FB=0\t12:FB=0\t20:FB=0\t35:FB=0\t32:FB=0\t40:FB=0\t70:FB=0\t55:FB=0\t52:FB=0\t60:FB=0\t80:FB=0\t75:FB=0\t85:FB=0\t",tree.preOrder());
	}
	
	/**
	 * Método que comprueba que las rotaciones simples derecha al borrar un hijo desequilibrado se hacen correctamente
	 */
	@Test
	public void test_3_RotacionesSimplesDerechaRemoveHijoDesequilibrado () {
		Integer[] array = {35,55,15,65,45,25,5,70,60,50,40,30,20,10,0,73,53,33};
		for(Integer ent: array)  { tree.addNode(ent);}
		
		
		assertEquals("35:FB=0\t15:FB=1\t5:FB=0\t0:FB=0\t10:FB=0\t25:FB=1\t20:FB=0\t30:FB=1\t33:FB=0\t55:FB=0\t45:FB=1\t40:FB=0\t50:FB=1\t53:FB=0\t65:FB=1\t60:FB=0\t70:FB=1\t73:FB=0\t", tree.preOrder());
		tree.removeNode(60);
		assertEquals("35:FB=0\t15:FB=1\t5:FB=0\t0:FB=0\t10:FB=0\t25:FB=1\t20:FB=0\t30:FB=1\t33:FB=0\t55:FB=-1\t45:FB=1\t40:FB=0\t50:FB=1\t53:FB=0\t70:FB=0\t65:FB=0\t73:FB=0\t",tree.preOrder());
		tree.removeNode(40);
		assertEquals("35:FB=-1\t15:FB=1\t5:FB=0\t0:FB=0\t10:FB=0\t25:FB=1\t20:FB=0\t30:FB=1\t33:FB=0\t55:FB=0\t50:FB=0\t45:FB=0\t53:FB=0\t70:FB=0\t65:FB=0\t73:FB=0\t",tree.preOrder());
		tree.removeNode(20);
		assertEquals("35:FB=0\t15:FB=0\t5:FB=0\t0:FB=0\t10:FB=0\t30:FB=0\t25:FB=0\t33:FB=0\t55:FB=0\t50:FB=0\t45:FB=0\t53:FB=0\t70:FB=0\t65:FB=0\t73:FB=0\t",tree.preOrder());
	}
	
	/**
	 * Método que comprueba que las rotaciones simples izquierda se realizan bien en un nodo con hijo equilibrado
	 */
	@Test
	public void test_3_RotacionesSimplesIzquierdaHijoEquilibrado () {		
		Integer[] array = {50,30,70,15,35,55,80,12,20,52,60};
		for(Integer ent: array)  { tree.addNode(ent);}
		
		assertEquals("50:FB=0\t30:FB=-1\t15:FB=0\t12:FB=0\t20:FB=0\t35:FB=0\t70:FB=-1\t55:FB=0\t52:FB=0\t60:FB=0\t80:FB=0\t", tree.preOrder());
		tree.removeNode(35);
		assertEquals("50:FB=0\t15:FB=1\t12:FB=0\t30:FB=-1\t20:FB=0\t70:FB=-1\t55:FB=0\t52:FB=0\t60:FB=0\t80:FB=0\t", tree.preOrder());
		tree.removeNode(80);
		assertEquals("50:FB=0\t15:FB=1\t12:FB=0\t30:FB=-1\t20:FB=0\t55:FB=1\t52:FB=0\t70:FB=-1\t60:FB=0\t", tree.preOrder());
		tree.removeNode(20);
		tree.removeNode(60);
		tree.removeNode(52);
		tree.removeNode(70);
		assertEquals("50:FB=-1\t15:FB=0\t12:FB=0\t30:FB=0\t55:FB=0\t", tree.preOrder());
		tree.removeNode(55);
		assertEquals("15:FB=1\t12:FB=0\t50:FB=-1\t30:FB=0\t", tree.preOrder());
	}
	
	/**
	 * Método que comprueba que las rotaciones simples derecha se realizan bien en un nodo con hijo equilibrado
	 */
	@Test
	public void test_3_RotacionesSimplesDerechaHijoEquilibrado () {
		
		Integer[] array = {30,50,10,65,45,25,0,68,60,28,20};
		for(Integer ent: array)  { tree.addNode(ent);}
		
		assertEquals("30:FB=0\t10:FB=1\t0:FB=0\t25:FB=0\t20:FB=0\t28:FB=0\t50:FB=1\t45:FB=0\t65:FB=0\t60:FB=0\t68:FB=0\t", tree.preOrder());
		tree.removeNode(45);
		assertEquals("30:FB=0\t10:FB=1\t0:FB=0\t25:FB=0\t20:FB=0\t28:FB=0\t65:FB=-1\t50:FB=1\t60:FB=0\t68:FB=0\t", tree.preOrder());
		tree.removeNode(0);
		assertEquals("30:FB=0\t25:FB=-1\t10:FB=1\t20:FB=0\t28:FB=0\t65:FB=-1\t50:FB=1\t60:FB=0\t68:FB=0\t", tree.preOrder());
		tree.removeNode(60);
		tree.removeNode(20);
		tree.removeNode(28);
		tree.removeNode(10);
		assertEquals("30:FB=1\t25:FB=0\t65:FB=0\t50:FB=0\t68:FB=0\t", tree.preOrder());
		tree.removeNode(25);
		assertEquals("65:FB=-1\t30:FB=1\t50:FB=0\t68:FB=0\t", tree.preOrder());
	}


}