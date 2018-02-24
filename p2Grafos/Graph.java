package p2Grafos;

import java.text.DecimalFormat;

/**
 * @author Gema
 * 
 */
public class Graph<T> {
	private T[] nodes; // Vector de nodos
	private boolean[][] edges; // matriz de aristas
	private double[][] weights; // matriz de pesos
	private double[][] matrizA = null; // matriz A de costes minimos para algoritmo Floyd
	private int[][] matrizP = null; // matriz P de caminos para algoritmo Floyd
	private int numNodes; // número de elementos en un momento dado

	/**
	 * Constructor clase graph
	 * 
	 * @param tam
	 *            Número máximo de nodos del grafo
	 */
	@SuppressWarnings("unchecked")
	public Graph(int tam) {

		weights = new double[tam][tam];
		nodes = (T[]) new Object[tam];
		edges = new boolean[tam][tam];
		this.numNodes = 0;
	}

	/**
	 * Constructor para pruebas
	 * 
	 * @param initialAfloyd
	 *            matriz A de costes minimos para algoritmo Floyd
	 * @param initialWeights
	 *            matriz de pesos
	 * @param initialEdges
	 *            matriz de aristas
	 * @param initialNodes
	 *            Vector de nodos
	 * @param initialPfloyd
	 *            matriz P de caminos para algoritmo Floyd*
	 * @param tam
	 *            Número máximo de nodos del grafo
	 */
	public Graph(int tam, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights,
			double[][] initialAfloyd, int[][] initialPfloyd) {

		// Llama al constructor original
		this(tam);

		// Modifica los atributos con los parametros para hacer pruebas
		numNodes = initialNodes.length;

		for (int i = 0; i < numNodes; i++) {
			nodes[i] = initialNodes[i];
			for (int j = 0; j < numNodes; j++) {
				edges[i][j] = initialEdges[i][j];
				weights[i][j] = initialWeights[i][j];
			}
		}
		if (initialAfloyd != null) {
			matrizA = initialAfloyd;
			matrizP = initialPfloyd;
		}

	}

	/**
	 * Inserta un nuevo nodo que se le pasa como parámetro, en el vector de nodos
	 * 
	 * @param node
	 *            el nodo que se quiere insertar
	 * @return 0 si lo inserta, -1 si no puede insertarlo
	 */
	public int addNode(T node) {

		if ((node == null) || (existNode(node) == true))
			return -1;

		if (numNodes < nodes.length) {
			nodes[numNodes] = node;
			
			// por si después de borrar quedan residuos de nodos anteriores
			for (int i=0; i<=numNodes; i++)
			{
				edges[numNodes][i]=false;
				edges[i][numNodes]=false;
				weights[numNodes][i]=0;
				weights[i][numNodes]=0;
			}
			numNodes = numNodes + 1;
			setFloydNull();
			return 0;
		}
		return -1;
	}
	
	/**
	 * Método privado para poner las matrices de floyd a null
	 */	
	private void setFloydNull() {
		matrizP=null;
		matrizA=null;
	}

	/**
	 * Devuelve un String con la informacion del grafo (incluyendo matrices de
	 * Floyd)
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String cadena = "";

		cadena += "VECTOR NODOS\n";
		for (int i = 0; i < numNodes; i++) {
			cadena += nodes[i].toString() + "\t";
		}
		cadena += "\n\nMATRIZ ARISTAS\n";
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j])
					cadena += "T\t";
				else
					cadena += "F\t";
			}
			cadena += "\n";
		}
		cadena += "\nMATRIZ PESOS\n";
		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {

				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";
			}
			cadena += "\n";
		}

		double[][] aFloyd = getAFloyd();
		if (aFloyd != null) {
			cadena += "\nMATRIZ AFloyd\n";
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					cadena += df.format(aFloyd[i][j]) + "\t";
				}
				cadena += "\n";
			}
		}

		int[][] pFloyd = getPFloyd();
		if (pFloyd != null) {
			cadena += "\nMATRIZ PFloyd\n";
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					cadena += (pFloyd[i][j] >= 0 ? df.format(pFloyd[i][j]) : "-") + "\t";
				}
				cadena += "\n";
			}
		}
		return cadena;
	}

	/**
	 * Obtiene el índice de un nodo en el vector de nodos
	 *
	 * ¡¡¡ OJO que es privado porque depende de la implementación !!!
	 * 
	 * @param node
	 *            el nodo que se busca
	 * @return la posición del nodo en el vector ó -1 si no lo encuentra
	 */
	private int getNode(T node) {

		if (node != null) {

			for (int i = 0; i < numNodes; i++) {
				if (nodes[i].equals(node))
					return i;
			}
		}
		return -1;

	}

	/**
	 * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y
	 * otro destino. Devuelve 0 si la inserta y -1 si no lo hace (si existe no la
	 * inserta)
	 * 
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @param edgeWeight
	 *            peso de la arista, debe ser > 0
	 * @return 0 si lo hace y -1 si no lo hace (también si el peso es <= 0)
	 */
	public int addEdge(T source, T target, double edgeWeight) {

		int i = getNode(source);
		int j = getNode(target);

		if ((i != -1) && (j != -1) && (edgeWeight > 0) && (existEdge(source, target) == false)) {
			edges[i][j] = true;
			weights[i][j] = edgeWeight;
			setFloydNull();
			return 0;
		}
		return -1;
	}

	/**
	 * Metodo que determina si existe un nodo que se pasa como parametro
	 * 
	 * @param node
	 *            Nodo que se quiere consultar
	 * @return si existe o no el nodo
	 */
	public boolean existNode(T node) {

		if (getNode(node) == -1)
			return false;
		return true;

	}

	/**
	 * Comprueba si existe una arista entre dos nodos que se pasan como parámetro
	 * 
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @return verdadero o falso si existe o no, si alguno de los nodos no existe
	 *         también falso
	 */
	public boolean existEdge(T source, T target) {

		if ((existNode(source)) && (existNode(target)) && (edges[getNode(source)][getNode(target)]))
			return true;

		return false;

	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos, si no existe la arista,
	 * devuelve -1 (también si no existe alguno de los nodos)
	 * 
	 * @param source
	 * @param target
	 * @return El peso de la arista o -1 si no existe
	 */
	public double getEdge(T source, T target) {

		if ((existNode(source)) && (existNode(target)) && (existEdge(source, target)))
			return weights[getNode(source)][getNode(target)];
		return -1;
	}

	/**
	 * Borra el nodo deseado del vector de nodos así como las aristas de las que
	 * forma parte
	 * 
	 * @param node
	 *            que se quiere borrar
	 * @return 0 si lo borra y -1 si no lo hace
	 */
	public int removeNode(T node) {

		if (existNode(node) == true) { // comprobar que el nodo existe
			int indexNode = getNode(node);
			numNodes = numNodes - 1; // disminuir el numero de nodos por que vamos a borrar uno
			nodes[indexNode] = nodes[numNodes]; // reemplaza el nodo que queremos borrar con el ultimo nodo

				// reemplazar todos los pesos y las aristas del borrado con el ultimo
				for (int i = 0; i <= numNodes; i++) {

					weights[indexNode][i] = weights[numNodes][i];
					weights[i][indexNode] = weights[i][numNodes];
					edges[i][indexNode] = edges[i][numNodes];
					edges[indexNode][i] = edges[numNodes][i];
					edges[i][numNodes] = false; 
				    edges[numNodes][i] = false; 
				    weights[i][numNodes] = 0; 
				    weights[numNodes][i] = 0; 

				}
				// diagonal
				edges[indexNode][indexNode] = edges[numNodes][numNodes];
				weights[indexNode][indexNode] = weights[numNodes][numNodes];
				setFloydNull();
			
			return 0;
		}
		return -1;

	}

	/**
	 * Borra una arista del grafo que conecta dos nodos
	 * 
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @return 0 si la borra y -1 si no lo hace (también si no existe alguno de sus
	 *         nodos)
	 */
	public int removeEdge(T source, T target) {

		if ((existNode(source) == true) && (existNode(target) == true) && (existEdge(source, target) == true)) {
			edges[getNode(source)][getNode(target)] = false;
			weights[getNode(source)][getNode(target)] = 0;
			setFloydNull();
			return 0;
		}
		return -1;
	}

	/**
	 * Algoritmo de Dijkstra para encontrar el camino de coste mínimo desde
	 * nodoOrigen hasta el resto de nodos
	 * 
	 * @param nodoOrigen
	 * @return vector D de dijkstra para comprobar funcionamiento
	 */
	public double[] dijkstra(T nodoOrigen) {

		// Declaramos los vectores
		double[] vectorD = new double[numNodes]; // vector de costes minimos
		int[] vectorP = new int[numNodes];// vector que acumula la ruta/ indices del coste minimo
		boolean[] conjuntoS = new boolean[numNodes]; // conjunto donde vamos añadiendo los nodos visitados

		// inicializar variable con la posicion del nodo origen y comprobamos
		if ((getNode(nodoOrigen) == -1) || (nodoOrigen == null))
			return null;

		int origen = getNode(nodoOrigen);
		conjuntoS[origen] = true; // Añadimos al conjunto el nodo origen como visitado

		// Inicializar los vectores
		for (int i = 0; i < numNodes; i++) {

			if (edges[origen][i] == false) { // si no hay arista
				vectorD[i] = Double.POSITIVE_INFINITY; // ponemos infinito
				vectorP[i] = -1;

			} else {
				vectorD[i] = weights[origen][i]; // si hay arista ponemos su peso
				vectorP[i] = origen;
			}
		}

		// 0 porque desde un nodo asi mismo no tiene coste
		vectorD[origen] = 0.0;

		// Algoritmo
		int w = minCost(vectorD, conjuntoS); // nodo con menor coste en D

		while (w != -1) { // minCost devuelve -1 si el grafo es no conexo o no quedan nodos sin visitar
			conjuntoS[w] = true; // lo agregamos al conjunto de visitados

			for (int i = 0; i < numNodes; i++) {
				if (edges[w][i] == true) { // si hay arista
					// comparamos que lo que hay desde el nodo de coste minimo + el coste desde
					//el de menor hacia todos es menor que lo que ya hay en vectorD
					if (vectorD[w] + weights[w][i] < vectorD[i]) { 
						vectorD[i] = vectorD[w] + weights[w][i];
						vectorP[i] = w; //actualizamos la ruta de coste minimo
					}
				}
			}
			w = minCost(vectorD, conjuntoS); //volvemos a llamar con el vectorD modificado
		}

		return vectorD;
	}

	/**
	 * Busca el nodo con distancia mínima en D al resto de nodos
	 * 
	 * @param dist
	 *            vector d
	 * @param v
	 *            vector con visitados (conjunto S)
	 * @return índice del nodo buscado o -1 si el grafo es no conexo o no quedan
	 *         nodos sin visitar
	 */
	private int minCost(double[] dist, boolean[] v) {

		int valor = -1;
		double min = Double.POSITIVE_INFINITY;

		for (int i = 0; i < v.length; i++) {
			if (v[i] == false) { // si no está visitado
				if (dist[i] <= min) { // comparamos que la distancia es menor que el minimo actual encontrado
					min = dist[i];
					valor = i;
				}
			}
		}

		return valor;
	}

	/**
	 * Aplica el algoritmo de Floyd al grafo
	 * 
	 * @return 0 si lo aplica y genera matrices A y P; y –1 si no lo hace
	 */
	public int floyd() {

		if (numNodes == 0) // solo devolverá -1 cuando no haya nodos
			return -1;

		// Inicializar las matrices
		matrizA = new double[numNodes][numNodes];
		matrizP = new int[numNodes][numNodes];

		for (int i = 0; i < numNodes; i++) {
			
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j] == true) { // si hay arista ponemos su peso
					matrizA[i][j] = weights[i][j];
					matrizP[i][j] = -1; // la matriz p la ponems a -1 siempre
				} else {
					matrizA[i][j] = Double.POSITIVE_INFINITY; // si no hay arista ponemos infinito
					matrizP[i][j] = -1; // la matriz p la ponems a -1 siempre
				}
			}matrizA[i][i] = 0.0; // diagonal a 0 porque desde un nodo a si mismo no hay coste
		}
		// Algoritmo teoría
		for (int k = 0; k < numNodes; k++) {
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					if (matrizA[i][k] + matrizA[k][j] < matrizA[i][j]) {
						matrizA[i][j] = matrizA[i][k] + matrizA[k][j];
						matrizP[i][j] = k;
					}
				}
			}
		}
		return 0;

	}

	/**
	 * Devuelve la matriz A de Floyd, con infinito si no hay camino
	 * 
	 * Si no se ha invocado a Floyd debe devolver null
	 * 
	 * @return la matriz A de Floyd
	 */
	protected double[][] getAFloyd() {
		return matrizA;
	}

	/**
	 * Devuelve la matriz P de Floyd, con -1 en las posiciones en las que no hay
	 * nodo intermedio
	 * 
	 * Si no se ha invocado a Floyd debe devolver null
	 * 
	 * @return la matriz P de Floyd
	 */
	protected int[][] getPFloyd() {
		return matrizP;
	}

	/**
	 * Devuelve un String con el camino de coste mínimo y los costes de las aristas
	 * que atraviesa a través de un método privado que indica el camino entre los
	 * nodos que se le pasan como parámetros de esta forma:
	 * Origen<tab>(coste0)<tab>Intermedio1<tab>(coste1)….IntermedioN<tab>(costeN)
	 * Destino
	 * 
	 * Si no hay camino muestra: Origen<tab>(Infinity)<tab>Destino
	 * 
	 * Si Origen y Destino coinciden muestra: Origen
	 * 
	 * @param origen,
	 *            nodo origen
	 * @param destino,
	 *            nodo destino
	 */
	public String path(T origen, T destino) {
		
		if ((getNode(origen) == -1) || (getNode(destino) == -1)) // comprobamos parámetros
			return null;

		// Si Origen y Destino coinciden muestra: Origen
		if (origen.equals(destino))
			return origen.toString();

		// Si no hay camino muestra: Origen<tab>(Infinity)<tab>Destino
		if (matrizP[getNode(origen)][getNode(destino)] == -1)
			return origen.toString() + "\t" + "(" + Double.POSITIVE_INFINITY + ")"+ "\t" + destino.toString();

		return (origen.toString() + buildingPath(getNode(origen), getNode(destino)));
	}

	/**
	 * Método privado recursivo que indica el camino entre los nodos que se le pasan
	 * como parámetros de esta forma:
	 * Origen<tab>(coste0)<tab>Intermedio1<tab>(coste1)….IntermedioN<tab>(costeN)
	 * Destino
	 * 
	 * @param visitados,
	 *            vector de nodos visitados
	 * @param i,
	 *            el nodo por el que se quiere empezar el recorrido en profundidad
	 */
	private String buildingPath(int i, int j) {

		int k = matrizP[i][j];
		String cadena = "";

		if (k != -1) { // mientras haya un intermedio se llama recursivamente
			cadena += (buildingPath(i, k) + (buildingPath(k, j)));}
		else {
			cadena += ("\t" + "(" + matrizA[i][j] + ")" + "\t" + nodes[j].toString());}

		return cadena;
	}

	/**
	 * Lanza el recorrido en profundidad de un grafo desde un nodo determinado No
	 * necesariamente recorre todos los nodos. Al recorrer cada nodo añade el
	 * toString del nodo. Usa un método privado recursivo Que recorran vecinos
	 * empezando por el principio del vector de nodos (antes índices bajos)
	 * 
	 * @param nodo
	 *            El nodo por el que se quiere empezar el recorrido en profundidad
	 * @return un String con el toString de los nodos del recorrido separados por
	 *         tabulaciones Si no existe el nodo devuelve un String vacio
	 */
	public String recorridoProfundidad(T nodo) {

		boolean[] visitados = new boolean[numNodes]; // vector de visitados

		if ((nodo == null) ||(getNode(nodo) == -1)) // Si no existe el nodo devuelve un String vacio
			return "";

		return buildingRecorrido(getNode(nodo), visitados);

	}

	/**
	 * Método privado recursivo Que recorran vecinos empezando por el principio del
	 * vector de nodos (antes índices bajos)
	 * 
	 * @param visitados,
	 *            vector de nodos visitados
	 * @param i,
	 *            el nodo por el que se quiere empezar el recorrido en profundidad
	 * 
	 */
	private String buildingRecorrido(int i, boolean[] visitados) {

		visitados[i] = true;// añadimos a visitados el nodo origen

		String recorrido = nodes[i].toString() + "\t";

		for (int j = 0; j < numNodes; j++) {
			if ((edges[i][j] == true) && (visitados[j] == false))// si hay arista y no esta visitado
				recorrido += buildingRecorrido(j, visitados);
		} // recursivo con el nodo y añadimos al string
		return recorrido;
	}

	/**
	 * Devuelve el coste del camino de coste mínimo entre origen y destino según
	 * Floyd Si no están generadas las matrices de floyd, las genera. Si no puede
	 * obtener el valor por alguna razón, devuelve –1 (OJO que es distinto de
	 * infinito)
	 * 
	 **/
	public double minCostPath(T origen, T destino) {

		if ((numNodes == 0) || (getNode(origen) == -1) || (getNode(destino) == -1)) // comprobamos parámetros
			return -1;
		if (matrizA == null)
			this.floyd(); // Si no están generadas las matrices de floyd, las genera.

		return matrizA[getNode(origen)][getNode(destino)];
	}
	
	
	
		
		
	

}













		