package p3Arboles;

/**
 * Clase derivada de BSTree añadiendo funcionalidad de AVL
 * 
 * @author Gema
 * @version 2017-18
 */
public class AVLTree<T extends Comparable<T>> extends BSTree<T> {
	/**
	 * Constructor
	 */
	// public AVLTree() {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTree#add(java.lang.Comparable) Redefine inserción para funcionalidad
	 * AVL
	 */

	public boolean addNode(T info) {

		if (info == null)
			return false;

		if (this.raiz == null) {
			setRoot(new AVLNode<T>(info));
			return true;
		}

		try {
			this.raiz = add((AVLNode<T>) this.raiz, info);
		}

		catch (Exception e) {
			return false;
		}

		return true;
	}

	private AVLNode<T> add(AVLNode<T> theRoot, T node) {

		if (theRoot == null)
			return new AVLNode<T>(node);

		if (node.compareTo(theRoot.getInfo()) == 0)
			throw new RuntimeException("El nodo ya existe");

		if (node.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(add(theRoot.getLeft(), node));
		else
			theRoot.setRight(add(theRoot.getRight(), node));

		return (updateAndBalanceIfNecesary(theRoot));
	}

	/**
	 * @param nodo
	 *            el árbol que se quiere actualizar Height, BF y balancear si fuese
	 *            necesario
	 * @return la raíz del árbol por si ha cambiado
	 */
	private AVLNode<T> updateAndBalanceIfNecesary(AVLNode<T> nodo) {

		nodo.updateHeight();

		if (nodo.getBF() == -2) {
			if (nodo.getLeft().getBF() == -1) {
				nodo = this.singleLeftRotation(nodo);
				System.out.println("SingleLeftRotation [-2][-1]");
			}

			else if (nodo.getLeft().getBF() == 0) {
				nodo = this.singleLeftRotation(nodo);
				System.out.println("singleLeftRotation [-2][0]");
			}

			else {
				nodo = this.doubleLeftRotation(nodo);
				System.out.println("DoubleLeftRotation [-2][1]");
			}
		}

		if (nodo.getBF() == 2) {

			if (nodo.getRight().getBF() == 1) {
				nodo = this.singleRightRotation(nodo);
				System.out.println("singleRightRotation [2][1]");
			}

			else if (nodo.getRight().getBF() == 0) {
				nodo = this.singleRightRotation(nodo);
				System.out.println("singleRightRotation [2][0]");
			}

			else {
				nodo = this.doubleRightRotation(nodo);
				System.out.println("doubleRightRotation[2][-1]");
			}
		}

		nodo.updateHeight();

		return nodo;

	}

	/**
	 * Metodo de rotacion simple a la izquierda
	 * 
	 * @param nodo
	 *            la raíz del árbol a balancear con rotación simple
	 * @return la raíz del nuevo árbol que ha cambiado
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> nodo) {

		AVLNode<T> referencia = nodo.getLeft();
		nodo.setLeft(referencia.getRight());
		referencia.setRight(nodo);
		nodo.updateHeight();
		nodo = referencia;
		return nodo;
	}

	/**
	 * Metodo de rotacion doble a la izquierda
	 * 
	 * @param nodo
	 *            la raíz del árbol a balancear con rotación doble
	 * @return la raíz del nuevo árbol que ha cambiado
	 */

	private AVLNode<T> doubleLeftRotation(AVLNode<T> nodo) {
		nodo.setLeft(singleRightRotation(nodo.getLeft()));
		return singleLeftRotation(nodo);

	}

	/**
	 * Metodo de rotacion simple a la derecha
	 * 
	 * @param nodo
	 *            la raíz del árbol a balancear con rotación simple
	 * @return la raíz del nuevo árbol que ha cambiado
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> nodo) {

		AVLNode<T> referencia = nodo.getRight();
		nodo.setRight(referencia.getLeft());
		referencia.setLeft(nodo);
		nodo.updateHeight();
		nodo = referencia;
		return nodo;
	}

	/**
	 * @param nodo
	 *            la raíz del árbol a balancear con rotación doble
	 * @return la raíz del nuevo árbol que ha cambiado
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> nodo) {
		nodo.setRight(singleLeftRotation(nodo.getRight()));
		return singleRightRotation(nodo);
	}

	public boolean removeNode(T info) {

		if ((info == null) || (raiz == null))
			return false;

		if (super.searchNode(info) == null)
			return false;

		try {
			this.raiz = (remove((AVLNode<T>) raiz, info));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private AVLNode<T> remove(AVLNode<T> theRoot, T node) {
		if (theRoot == null)
			throw new RuntimeException("El elemento no puede ser null");

		else if (node.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(remove(theRoot.getLeft(), node));

		else if (node.compareTo(theRoot.getInfo()) > 0)
			theRoot.setRight(remove(theRoot.getRight(), node));

		else if (theRoot.getLeft() == null)

			return theRoot.getRight();
		else if (theRoot.getRight() == null)
			return theRoot.getLeft();

		else {
			theRoot.setInfo(super.getMax(theRoot.getLeft()));
			theRoot.setLeft(remove(theRoot.getLeft(), theRoot.getInfo()));
		}

		return (updateAndBalanceIfNecesary(theRoot));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTree#remove(java.lang.Comparable) Redefinición para incluir
	 * características AVL
	 */
	// public boolean remove (T info){
	// return false;

	// }
}
