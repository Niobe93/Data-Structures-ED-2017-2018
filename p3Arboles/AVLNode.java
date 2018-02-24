package p3Arboles;

/**
 * Clase derivada de BSTNode añadiendo funcionalidad de AVL
 * 
 * @author Gema
 * @version 2017-18
 * 
 */
public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {
	/**
	 * Para almacenar la altura del árbol
	 */
	protected int height;


	/**
	 * Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a
	 * partir de la altura de los hijos.
	 */
	protected byte balanceFactor;

	/**
	 * Llama al padre y añade la información propia
	 * 
	 * @param info
	 *            la información que se mete en el nuevo nodo
	 */
	public AVLNode(T info) {

		super(info);
		this.balanceFactor = 0;
		this.height = 1;

	}

	/**
	 * @return devuelve la altura del árbol del cual es raíz el nodo en cuestión
	 */
	public int getHeight() {

		return height;

	}

	/**
	 * @return Devuelve el factor de balance según equilibrio del árbol del cual es
	 *         raíz
	 */
	public byte getBF() { 
		
		this.updateHeight();
		
		int alturaDerecha = 0;
		int alturaIzquierda = 0;
		
		if (this.getLeft() != null) //si es null no hay altura
			alturaIzquierda = getLeft().getHeight();

		if (getRight() != null)
			alturaDerecha = getRight().getHeight();

		balanceFactor = (byte) (alturaDerecha - alturaIzquierda);

		return balanceFactor;

	}

	/**
	 * Actualiza la altura del nodo en el árbol utilizando la altura de los hijos
	 */
	protected void updateHeight() {

		//cuando no tiene hijos
		if (this.getLeft() == null && this.getRight() == null)
			height = 1;
		
		//cuando tiene un hijo por la izquierda la altura es la del hijo izq + 1
		else if (this.getRight() == null && this.getLeft()!=null)
			height = 1 + this.getLeft().height;
		
		//cuando tiene un hijo por la derecha la altura es la del hijo dch + 1
		else if (this.getLeft() == null && this.getRight()!=null)
			height = 1 + this.getRight().height;
		
		//Si tiene ambos, altura = la altura mayor de los dos + 1
		else {
			height = 1 + Math.max(getRight().height, getLeft().height);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getLeft()
	 */
	public AVLNode<T> getLeft() {
		return (AVLNode<T>) super.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getRight()
	 */
	public AVLNode<T> getRight() {
		return (AVLNode<T>) super.getRight();
	}
	// No se necesitan los setters, valen los heredados

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#toString() Añade factor de balance
	 */
	public String toString() {
		return super.toString() + ":FB=" + getBF();
	}
}
