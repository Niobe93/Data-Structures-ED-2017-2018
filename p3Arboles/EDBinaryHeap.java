package p3Arboles;

/**
 * @author N�stor
 * @version 2017-18
 * @param <T>
 *            Comparable para ordenaci�n vertical
 */
public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T> {
	protected T[] elementos;
	protected int numElementos;

	@SuppressWarnings("unchecked")
	public EDBinaryHeap(int numMaxElementos) {

		elementos = (T[]) new Comparable[numMaxElementos];
		numElementos = 0;

	}

	@Override
	public boolean add(T info) {

		if ((info == null) || (numElementos > elementos.length - 1)) // si estamos intentando meter cuando no hay sitio
			return false;

		else {
			elementos[numElementos] = info; // si hay sitio ponemos el elemento
			filtradoAscendente(numElementos); // pero obviamente hay que reordenarlo
			numElementos++; // aumentamos el numero de elementos
			return true;
		}

	}

	@Override
	public T poll() {
		if (numElementos <= 0)
			return null;

		T primero = elementos[0];
		remove(primero);
		filtradoDescendente(0);
		return primero;

	}

	private int getPos(T info) {
		if (info == null)
			return -1;
		for (int i = 0; i < numElementos; i++) {
			if (elementos[i].equals(info))
				return i;
		}

		return -1;
	}

	@Override
	public boolean remove(T info) {
		int posicion = getPos(info);
		if (posicion == -1)
			return false;

		elementos[posicion] = elementos[numElementos - 1];
		numElementos--;
		filtradoDescendente(posicion);
		filtradoAscendente(posicion);

		return true;
	}

	@Override
	public boolean isEmpty() {
		if (numElementos == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public void clear() {
		numElementos = 0;
	}

	/**
	 * Devuelve una cadena con la informaci�n de los elementos que contiene el
	 * mont�culo en forma visible (recomendado inorden-derecha-izquierda tabulado)
	 */

	public String toString() {
		// Por ejemplo el �rbol tumbado...
		StringBuilder cadena = new StringBuilder();
		cadena.append(inOrdenDerechaTabulado(0, 0));
		return cadena.toString();
	}

	// el �rbol que empieza en �ndice p tumbado con esp tabulaciones...
	private String inOrdenDerechaTabulado(int p, int esp) {
		String cadena = "";
		if (p < numElementos) {
			int izq = Math.abs(2 * p + 1);
			int der = Math.abs(2 * p + 2);
			cadena += inOrdenDerechaTabulado(der, esp + 1);
			for (int i = 0; i < esp; i++)
				cadena += "\t";
			cadena += elementos[p] + "\n";
			cadena += inOrdenDerechaTabulado(izq, esp + 1);
		}
		return cadena;
	}

	/**
	 * Realiza una filtrado ascendente de m�nimos en el mont�culo
	 * 
	 * @param p
	 *            el �ndice del elemento a filtrar
	 */
	protected void filtradoAscendente(int p) {

		int posPadre = (p - 1) / 2; // direcion de la posicion del padre

		if ((elementos[p].compareTo(elementos[posPadre]) < 0)) { // comparar si el padre es mayor
			T referencia = elementos[posPadre]; // guardamos la referencia al padre
			elementos[posPadre] = elementos[p]; // asignamos en la posicion del padre la del hijo
			elementos[p] = referencia; // y la del padre a la del hijo
			filtradoAscendente(posPadre);// se llamar�a con la del "nuevo padre"
		}
	}

	/**
	 * Realiza una filtrado descendente de m�nimos en el mont�culo
	 * 
	 * @param p
	 *            el �ndice del elemento a filtrar
	 */
	protected void filtradoDescendente(int p) {

		int peque�o = (2 * p) + 1; // le asignamos el valor del hijo izq

		if ((2 * p) + 1 < elementos.length && (2 * p) + 2 < elementos.length && elementos[(2 * p) + 1] != null
				&& elementos[(2 * p) + 2] != null) { // comprobamos que est� dentro del array

			if ((elementos[p].compareTo(elementos[peque�o]) > 0)) { // si es mayor que su hijo

				if (elementos[peque�o].compareTo(elementos[(2 * p) + 2]) > 0) {
					peque�o = (2 * p) + 2;
				}
				// los intercambiamos
				T referencia = elementos[peque�o];
				elementos[peque�o] = elementos[p];
				elementos[p] = referencia;
				// llamamos recursivamente
				filtradoDescendente(peque�o);
			}
		} else {
			if (numElementos > 1) {
				if (elementos[p].compareTo(elementos[numElementos - 1]) > 0) {
					T referencia = elementos[p];
					elementos[p] = elementos[numElementos - 1];
					elementos[numElementos - 1] = referencia;
				}
			}
		}
	}

}
