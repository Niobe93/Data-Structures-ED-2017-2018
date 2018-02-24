package p1Algoritmia;

/**
 * Clase Algorithms
 * 
 * @author Gema Rico Pozas
 *
 */
public class Algorithms {

	private static final long SLEEP_TIME = 2; // constante de tiempo en ms

	/**
	 * Algoritmo que provoca un delay time con el valor de constante sleep time
	 *
	 */
	public static void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Algoritmo de prueba de complejidad lineal.
	 *
	 */
	public void linear(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println("executing job: " + i);
			doNothing();
		}
	}

	/**
	 * Algoritmo de prueba de complejidad cuadratica
	 *
	 */
	public void quadratic(int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				System.out.println("executing job: " + i);
				doNothing();
			}
	}

	/**
	 * Algoritmo de prueba de complejidad cubica.
	 *
	 */
	public void cubic(int n) {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++) {
					System.out.println("executing job: " + i);
					doNothing();
				}
	}

	/**
	 * Algoritmo que calcula la potencia de 2 para un un exponente dado
	 * iterativamente
	 *
	 */
	public long pow2Iter(int n) {
		long valor = 2;
		if (n > 0) {
			for (int i = 1; i < n; i++) {
				doNothing();
				valor += valor;
			}
			return valor;
		}
		return 1;
	}

	/**
	 * Algoritmo que calcula la potencia de 2 para un un exponente dado mediante una
	 * función recursiva
	 *
	 */

	public long pow2Rec1(int n) {
		if (n > 0) {
			doNothing();
			return 2 * pow2Rec1(n - 1);	}
		return 1;

	}

	/**
	 * Algoritmo que calcula la potencia de 2 para un un exponente dado mediante una
	 * función recursiva
	 *
	 */
	public long pow2Rec2(int n) {
		if (n > 0) {
			doNothing();
			return pow2Rec2(n - 1) + pow2Rec2(n - 1);}
		return 1;
	}

	/**
	 * Algoritmo que calcula la potencia de 2 para un un exponente dado mediante una
	 * función recursiva
	 *
	 */
	public long pow2Rec3(int n) {
		if (n > 0) {
			if (n % 2 == 0) {
				doNothing();
				return pow2Rec3(n / 2) * pow2Rec3(n / 2);
			} else {
				doNothing();
				return 2 * pow2Rec3(n / 2) * pow2Rec3(n / 2);
			}
		}
		return 1;
	}

	/**
	 * Algoritmo que calcula la potencia de 2 para un un exponente dado mediante una
	 * función recursiva
	 *
	 */
	public long pow2Rec4(int n) {

		if (n == 0)
			return 1;

		long valor = pow2Rec4(n / 2);
		if (n % 2 == 0) {
			doNothing();
			return valor * valor;
		}
		doNothing();
		return 2 * valor * valor;
	}

	/**
	 * Algoritmo que calcula el factorial de un numero dado de manera recursiva
	 *
	 */
	public long fact(int n) {

		if (n > 0) {
			doNothing();
			return n * fact(n - 1);	}
		return 1;
	}
}
