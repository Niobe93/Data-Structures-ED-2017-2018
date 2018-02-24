package p1Algoritmia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;


/**
 * Clase AlgorithmsBenchmark
 * 
 * @author Gema Rico Pozas
 *
 */
public class AlgorithmsBenchmark {
	
	

	/**
	 * Método que crea un fichero con los tiempos de ejecución de un algoritmo que
	 * imprime parejas de numeros.
	 *
	 */
	public void test0(String output) {
		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			pw.println("1, 10");
			pw.println("2, 20");
			pw.println("3, 30");
			pw.println("4, 40");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * Método que crea un fichero con los tiempos de ejecución de varios algoritmos
	 *startN: Carga de trabajo mínima n 
	 *endN: Carga de trabajo máxima n 	
	 */

	public void test1(String output, int StartN, int EndN) {

		Algorithms alg = new Algorithms();
		long tInicial = 0, tFinal = 0;

		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			for (int n = StartN; n <= EndN; n++) {

				tInicial = System.currentTimeMillis();

				// ALGORITMOS

				// alg.cubic(n); // n varía desde StartN hasta EndN
				   alg.linear(n); // n varía desde StartN hasta EndN
				// alg.quadratic(n); // n varía desde StartN hasta EndN
				// alg.pow2Iter(n);

				tFinal = System.currentTimeMillis();

				pw.println(n + " , " + (tFinal - tInicial));
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null)
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}}
		}
	
	/**
	 * Algoritmo que calcula el tiempo que tarda en ejecutarse el método indicado, de la clase indicada, con el parámetro n 
	 *
	 */
		public long testAlgorithm(String className, String methodName, int n) {
		    long tInicial = 0, tFinal = 0;
		    Class<?> cl;
		    try {
		        cl = Class.forName(className);
		        Object o = cl.newInstance();
		        Method m = cl.getMethod(methodName, int.class);
		        tInicial = System.currentTimeMillis();
		        m.invoke(o, n);
		        tFinal = System.currentTimeMillis();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return tFinal - tInicial;
		}
		
		
		/**
		 * Algoritmo que crea un fichero con los tiempos que tarda en ejecutarse el método indicado, de la clase indicada, con el parámetro n 
		 * Usando public long testAlgorithm
		 */

		public void test3 (String output, int StartN, int EndN, String nombreClase, String nombreMetodo) { 

			FileWriter file = null;
	        PrintWriter pw;

	        try {
	   			file = new FileWriter(output);
		        pw = new PrintWriter(file);

			for (int n = StartN; n <= EndN; n++) {

				long local = 0;

				local = local + (testAlgorithm(nombreClase, nombreMetodo, n));

				pw.println(n + ", " + (local));
			}

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (file != null)
	                try {
	                    file.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	        }
	    }
		
		
		/**
		 * Algoritmo como el Test3, pero ejecutando times veces cada prueba del algoritmo con cada parámetro de carga y 
	     * almacenando la media en el fichero
		 *
		 */
	
	public void testFinal(String output, int startN, int endN, int times, String nombreClase, String nombreMetodo){
       
		FileWriter file = null;
        PrintWriter pw;

        try {
   			file = new FileWriter(output);
	        pw = new PrintWriter(file);
	        
	        for (int n = startN; n <= endN; n++) {
	        	
	            long local = 0;
	            for (int i = 0; i < times; i++) {
	                 local = local + (testAlgorithm(nombreClase, nombreMetodo, n));
	                 long total = local / times;
	                 pw.println(n + ", " + (total));}
	            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null)
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
	
	

}
