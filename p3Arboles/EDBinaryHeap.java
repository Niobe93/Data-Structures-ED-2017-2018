package p3Arboles;

/**
 * @author Néstor
 * @version 2017-18
 * @param <T>
 *            Comparable para ordenación vertical
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
	 * Devuelve una cadena con la información de los elementos que contiene el
	 * montículo en forma visible (recomendado inorden-derecha-izquierda tabulado)
	 */

	public String toString() {
		// Por ejemplo el árbol tumbado...
		StringBuilder cadena = new StringBuilder();
		cadena.append(inOrdenDerechaTabulado(0, 0));
		return cadena.toString();
	}

	// el árbol que empieza en índice p tumbado con esp tabulaciones...
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
	 * Realiza una filtrado ascendente de mínimos en el montículo
	 * 
	 * @param p
	 *            el índice del elemento a filtrar
	 */
	protected void filtradoAscendente(int p) {

		int posPadre = (p - 1) / 2; // direcion de la posicion del padre

		if ((elementos[p].compareTo(elementos[posPadre]) < 0)) { // comparar si el padre es mayor
			T referencia = elementos[posPadre]; // guardamos la referencia al padre
			elementos[posPadre] = elementos[p]; // asignamos en la posicion del padre la del hijo
			elementos[p] = referencia; // y la del padre a la del hijo
			filtradoAscendente(posPadre);// se llamaría con la del "nuevo padre"
		}
	}

	/**
	 * Realiza una filtrado descendente de mínimos en el montículo
	 * 
	 * @param p
	 *            el índice del elemento a filtrar
	 */
	protected void filtradoDescendente(int p) {

		int pequeño = (2 * p) + 1; // le asignamos el valor del hijo izq

		if ((2 * p) + 1 < elementos.length && (2 * p) + 2 < elementos.length && elementos[(2 * p) + 1] != null
				&& elementos[(2 * p) + 2] != null) { // comprobamos que está dentro del array

			if ((elementos[p].compareTo(elementos[pequeño]) > 0)) { // si es mayor que su hijo

				if (elementos[pequeño].compareTo(elementos[(2 * p) + 2]) > 0) {
					pequeño = (2 * p) + 2;
				}
				// los intercambiamos
				T referencia = elementos[pequeño];
				elementos[pequeño] = elementos[p];
				elementos[p] = referencia;
				// llamamos recursivamente
				filtradoDescendente(pequeño);
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
