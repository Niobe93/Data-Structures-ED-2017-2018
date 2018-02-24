package p3Arboles;

/**
 * Clase derivada de BSTNode a�adiendo funcionalidad de AVL
 * 
 * @author Gema
 * @version 2017-18
 * 
 */
public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {
	/**
	 * Para almacenar la altura del �rbol
	 */
	protected int height;


	/**
	 * Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a
	 * partir de la altura de los hijos.
	 */
	protected byte balanceFactor;

	/**
	 * Llama al padre y a�ade la informaci�n propia
	 * 
	 * @param info
	 *            la informaci�n que se mete en el nuevo nodo
	 */
	public AVLNode(T info) {

		super(info);
		this.balanceFactor = 0;
		this.height = 1;

	}

	/**
	 * @return devuelve la altura del �rbol del cual es ra�z el nodo en cuesti�n
	 */
	public int getHeight() {

		return height;

	}

	/**
	 * @return Devuelve el factor de balance seg�n equilibrio del �rbol del cual es
	 *         ra�z
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
	 * Actualiza la altura del nodo en el �rbol utilizando la altura de los hijos
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
	 * @see BSTNode#toString() A�ade factor de balance
	 */
	public String toString() {
		return super.toString() + ":FB=" + getBF();
	}
}
