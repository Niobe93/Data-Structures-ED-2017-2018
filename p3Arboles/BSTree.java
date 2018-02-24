package p3Arboles;

/**
 * @author Gema
 * @version 2017-18
 */
public class BSTree<T extends Comparable<T>> {

	protected BSTNode<T> raiz; // Nodo raiz del árbol

	/**
	 * Método que añade un nodo al árbol
	 * 
	 * @param node
	 *            El objeto comparable que tiene que insertar
	 * 
	 * @return true cuando lo inserta, false cuando no lo hace ya existía o es null.
	 */
	public boolean addNode(T node) {
		if ((node == null) || searchNode(node)!=null)
			return false;

		raiz = addNodeRec(raiz, node);

		return true;

	}
	
	/**
	 * Método que establece el parámetro como nodo raíz del árbol
	 * 
	 * @param nodo
	 *            El objeto a insertar como raiz del arbol
	 */
	// 
	protected void setRoot(BSTNode<T> nodo) {
		raiz = nodo;
	}
	

	/**
	 * Método recursivo que añade el nodo que pasamos como parámetro
	 * 
	 * @param node
	 *            El nodo que tiene que insertar
	 * 
	 * @param theRoot
	 *            Nodo raiz del árbol
	 * 
	 * @return Nodo raiz del árbol
	 */

	private BSTNode<T> addNodeRec(BSTNode<T> theRoot, T node) {
		if (theRoot == null)
			return new BSTNode<T>(node);

		if (node.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(addNodeRec(theRoot.getLeft(), node));

		if (node.compareTo(theRoot.getInfo()) > 0)
			theRoot.setRight(addNodeRec(theRoot.getRight(), node));

		return theRoot;

	}

	/**
	 * Método que busca un nodo
	 * 
	 * @param node
	 *            El objeto comparable que se busca
	 * @return El objeto que se busca (completo) si lo encuentra - (null) si no lo
	 *         encuentra
	 */
	public T searchNode(T node) {
		if (node == null)
			return null;
		
		else if (search(raiz, node) != null) {
			
			return (search(raiz, node));
		}
		
		return null;
	}
	
	
	
	
	/**
	 * Método recursivo que busca un nodo
	 * 
	 * @param node
	 *            El objeto comparable que se busca
	 * 
	 * @param theRoot
	 *            Nodo raiz del árbol
	 * 
	 * @return true si lo encuentra, false si no lo encuentra
	 */

	private T search(BSTNode<T> raiz, T nodo) {
		if (raiz == null)
			return null;
		
		else {
			if (nodo.compareTo(raiz.getInfo()) < 0)
				return search(raiz.getLeft(), nodo);
			else if (nodo.compareTo(raiz.getInfo()) > 0)
				return search(raiz.getRight(), nodo);
			else
				return raiz.getInfo();
		}
		
	}
	/**
	 * Método que muestra por pantalla el recorrido en pre-orden
	 * (izquierda-derecha). Lo devuelve en un String (separados por tabuladores)
	 */
	public String preOrder() {
		return preOrderRec(raiz);

	}

	/**
	 * Método que genera un String con el recorrido en pre-orden (izquierda-derecha)
	 * 
	 * @param theRoot
	 *            Nodo raiz del árbol
	 * 
	 * @return un string con el recorrido pre-orden
	 */

	private String preOrderRec(BSTNode<T> theRoot) {
		if (theRoot == null)
			return "";
		
			String recorrido = theRoot.toString() + "\t"+ preOrderRec(theRoot.getLeft())
					+ preOrderRec(theRoot.getRight());
			return recorrido;
		
	}

	/**
	 * Muestra por pantalla el recorrido en post-orden (izquierda-derecha) y lo
	 * devuelve en un String (separados por tabuladores)
	 */
	public String postOrder() {
		return postOrderRec(raiz);

	}

	/**
	 * Método que genera un String con el recorrido en post-orden
	 * (izquierda-derecha)
	 * 
	 * @param theRoot
	 *            Nodo raiz del árbol
	 * 
	 * @return un string con el recorrido post-orden
	 */
	private String postOrderRec(BSTNode<T> theRoot) {
		if (theRoot == null)
			return "";
		
			String recorrido = postOrderRec(theRoot.getLeft()) + postOrderRec(theRoot.getRight()) + theRoot + "\t";
			return recorrido;
		
	}

	/**
	 * Muestra por pantalla el recorrido en in-orden (izquierda-derecha) y lo
	 * devuelve en un String (separados por tabuladores)
	 */
	public String inOrder() {
		return inOrderRec(raiz);

	}

	/**
	 * Método que genera un String con el recorrido en in-orden (izquierda-derecha)
	 * 
	 * @param theRoot
	 *            Nodo raiz del árbol
	 * 
	 * @return un string con el recorrido in orden
	 */
	private String inOrderRec(BSTNode<T> theRoot) {
		if (theRoot == null)
			return "";
		
			String recorrido = inOrderRec(theRoot.getLeft()) + theRoot + "\t" + inOrderRec(theRoot.getRight());
			return recorrido;
		
	}

	/**
	 * Método que borra un nodo
	 * 
	 * @param node
	 *            El objeto que se quiere borrar
	 * 
	 * @return true si lo ha borrado, false en caso contrario (no existía)
	 */
	public boolean removeNode(T node) {
		
		if ((node == null)||(raiz == null))
			return false;

		if (this.searchNode(node)==null)
			return false;


		try {
			raiz = (remove(raiz, node));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Método recursivo que borra un nodo
	 * 
	 * @param node
	 *            El objeto comparable que se quiere borrar
	 * 
	 * @return nodo raiz del arbol despues del borrado, si lo ha borrado o lanza una
	 *         excepcion si el node es null
	*/

	
	private BSTNode<T> remove (BSTNode<T> theRoot, T element){
		if (theRoot== null)
			throw new RuntimeException("element does not exist!");
		else
			if (element.compareTo(theRoot.getInfo()) < 0)
				theRoot.setLeft(remove (theRoot.getLeft(), element));
			else
				if (element.compareTo(theRoot.getInfo()) > 0)
					theRoot.setRight(remove (theRoot.getRight(), element));
				else {
					if (theRoot.getLeft() == null) return theRoot.getRight();
					else
						if (theRoot.getRight() == null) return theRoot.getLeft();
						else {
							theRoot.setInfo(getMax(theRoot.getLeft()));
							theRoot.setLeft(remove (theRoot.getLeft(), theRoot.getInfo())); }}
		return theRoot;}

	/**
	 * Método recursivo que devuelve el nodo máximo
	 * 
	 * @param nodo
	 *            raiz del arbol
	 * @return el máximo
	 */

	protected T getMax(BSTNode<T> theRoot) {
		if (theRoot == null)
			return null;
		
		if (theRoot.getRight() == null) // si el de la derecha que se supone que es el mayor es null es que es 
										// el mayor, pues lo devolvemos
			return theRoot.getInfo();
		
		else
			return getMax(theRoot.getRight());  // si no seguimos buscando por la derecha
	}

	/**
	 * Devuelve un String con la información del árbol del metodo tumbarArbol
	 * 
	 */

	public String toString() {
		return tumbarArbol(raiz, 0);
	}

	/**
	 * Genera un String con el árbol "tumbado" (la raíz a la izquierda y las ramas
	 * hacia la derecha) Es un recorrido InOrden-derecha-izquierda, tabulando para
	 * mostrar los distintos niveles Utiliza el toString de la información
	 * almacenada
	 * 
	 * @param p
	 *            La raíz del árbol a mostrar tumbado
	 * @param esp
	 *            El espaciado en número de tabulaciones para indicar la profundidad
	 * 
	 * @return El String generado
	 */
	protected String tumbarArbol(BSTNode<T> p, int esp) {
		StringBuilder cadena = new StringBuilder();

		if (p != null) {
			cadena.append(tumbarArbol(p.getRight(), esp + 1));
			for (int i = 0; i < esp; i++)
				cadena.append("\t");
			cadena.append(p + "\n");
			cadena.append(tumbarArbol(p.getLeft(), esp + 1));
		}
		return cadena.toString();
	}

}