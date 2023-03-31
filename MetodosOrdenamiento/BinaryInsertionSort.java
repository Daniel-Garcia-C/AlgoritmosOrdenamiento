/**
 * Clase para ordenar LinkedList<ArrayList<String>> mediante el algoritmo de Binary Insertion Sort
 * @author Aaron Graniel, Daniel Garcia
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryInsertionSort {
    /**
     * Lista de los datos que se quieren ordenar
     */
    private LinkedList<ArrayList<String>> lista;
    /**
     * Tamaño de la lista 
     */
    private int size;
    /**
     * Numero de comparaciones que se necesitaron hacer para ordenar la lista, 0 si no se a ordenado
     */
    private int comparaciones;


    /**
     * Crea un objeto BinaryInsertionSort con una lista y su tamanio. 
     * @param lista La lista a ordenar
     * @param size El tamanio de la lista
     */
    public BinaryInsertionSort (LinkedList<ArrayList<String>> lista, int size){
        this.lista = lista;
        this.size = size;
        this.comparaciones = 0;
    }
    
    /**
     * Metodo para obtener la lista de elementos
     * @return <ul>
     *         <li>La lista ordena, si hemos invocamos previamente el metodo binaryInsertionSort</li>
     *         <li>La lista sin ordenar, si no hemos invocamos el metodo binaryInsertionSort</li>
     *         </ul>
     */
    public LinkedList<ArrayList<String>> getLista(){
        return lista;
    }


    /**
     * Metodo para obtener el total de comparaciones
     * @return El numero total de comparaciones
     */
    public int getComparaciones() {
        return comparaciones;
    }


    /**
     * Metodo para encontrar la posicion donde debe ser insertado un elemento en forma ascendente, mediante una busqueda binaria. 
     * @param item Elemento que se quiere insertar
     * @param low Indice mas pequeño
     * @param high Indice mas grande 
     * @param key Indice del arrayList por el cual se va a ordenar toda la lista
     * @param tipo El tipo dato que se va a ordenar, identificado por un numero entero, siendo 1 una String y 2 un numero
     * @return La posision donde debe ser insertado un elemento
     */
    public int binarySearchASC(ArrayList<String> item, int low, int high, int key, int tipo){
        while (low <= high) {
            comparaciones++;
            int mid = low + (high - low) / 2;
            if(tipo == 1){
                if (item.get(key).compareTo(lista.get(mid).get(key))==0){
                    return mid + 1;
                }
                else if (item.get(key).compareTo(lista.get(mid).get(key)) > 0){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            } else {
                if (Double.parseDouble(item.get(key)) == Double.parseDouble(lista.get(mid).get(key))) {
                    return mid + 1;
                }
                else if (Double.parseDouble(item.get(key)) > Double.parseDouble(lista.get(mid).get(key))) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }
        return low;
    }
    /**
     * Metodo para encontrar la posicion donde debe ser insertado un elemento en forma descendente, mediante una busqueda binaria.
     * @param item Elemento que se quiere insertar
     * @param low Indice mas pequeño
     * @param high Indice mas grande 
     * @param key Indice del arrayList por el cual se va a ordenar toda la lista
     * @param tipo El tipo dato que se va a ordenar, identificado por un numero entero, siendo 1 una String y 2 un numero
     * @return La posision donde debe ser insertado un elemento
     */
    public int binarySearchDES(ArrayList<String> item, int low, int high, int key, int tipo){
        while (low <= high) {
            comparaciones++;
            int mid = low + (high - low) / 2;
            if(tipo == 1){
                if (item.get(key).compareTo(lista.get(mid).get(key))==0)
                    return mid + 1;
                else if (item.get(key).compareTo(lista.get(mid).get(key)) < 0)
                    low = mid + 1;
                else
                    high = mid - 1;
            } else {
                if (Double.parseDouble(item.get(key)) == Double.parseDouble(lista.get(mid).get(key)))
                    return mid + 1;
                else if (Double.parseDouble(item.get(key)) < Double.parseDouble(lista.get(mid).get(key)))
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return low;
    }

    /**
     * Metodo que ordena una lista previamente dada en la creacion del objeto BinaryInsertionSort, por el valor de una llave específica usando el algoritmo de ordenamiento Binary Insertion Sort.
     * @param key Indice del arrayList por el cual se va a ordenar toda la lista
     * @param order La forma de ordenamiento, Ascende(1) o descendente(2)
     * @param tipo El tipo dato que se va a ordenar, identificado por un numero entero, siendo 1 una String y 2 un numero
     */
    public void binaryInsertionSort(int key, int order, int tipo) {
        comparaciones = 0;
        int i, loc, j;
        ArrayList<String> selected;
    
        for (i = 1; i < size; ++i) {
            j = i - 1;
            selected = lista.get(i);
    
            if(order == 1)
                loc = binarySearchASC(selected, 0, j, key, tipo);
            else
                loc = binarySearchDES(selected, 0, j, key, tipo);
    
            while (j >= loc) {
                lista.set(j+1,lista.get(j));
                j--;
            }
            lista.set(j+1,selected);
        }
    }
}