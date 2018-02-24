package p3Arboles; 
  
/** 
* @author Néstor 
* @version 2017-18 
* 
*/ 
public interface EDPriorityQueue<T extends Comparable<T>> { 
  
    /** 
     * @param elemento Elemento que se quiere insertar en la cola 
     * @return true si consigue insertarlo, false en caso contrario 
     */ 
    public boolean add(T elemento); 
  
    /**  
     * Devuelve y elimina el elemento con más prioridad 
     * @return El elemento con mayor prioridad, o null si no hay elementos 
     */ 
    public T poll(); 
     
    /** 
     * Borra un elemento de la cola 
     * @param elemento El elemento que se quiere eliminar de la cola 
     * @return true si estaba y lo elimina, false en caso contrario 
     */ 
    public boolean remove (T elemento); 
  
    /** 
     * @return true si no hay ningún elemento 
     */ 
    public boolean isEmpty(); 
     
    /** 
     * Elimina todos los elementos de la cola 
     */ 
    public void clear(); 
     
    /** 
     * @return Un string con la cola de alguna forma visible 
     */ 
    public String toString(); 
} 