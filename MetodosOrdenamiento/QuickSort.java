import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase para ordenar Lista doblemente ligada de arreglos de Strings mediante el algoritmo de QuickSort
 * @author Aaron Graniel, Daniel Garcia
 * @version 1.0
 */

public class QuickSort{

    /**
     * Atributo de lista doblemente ligada con un ArrayList.
     */
    private LinkedList<ArrayList<String>> list = new LinkedList<ArrayList<String>>();
    /**
     * Atributo que almacena las comparaciones realizadas en el algoritmo de ordenamiento.
     */
    private int comparaciones;
    /**
     * Atributo que almacena los intercambios realizados en el algoritmo de ordenamiento.
     */
    private int intercambios;

    /**
     * Constructor de la clase.
     * @param list Lista doblemente ligada con un ArrayList con al información del Dataset a usar en el algoritmo de ordenamiento.
     */
    public QuickSort(LinkedList<ArrayList<String>> list)   {
        this.list.addAll(list);
        this.comparaciones = 0;
        this.intercambios = 0;
    }

    /**
     * Obtiene la lista doblemente ligada de los elementos almacenados en el.
     * @return Lista doblemente ligada con sus elementos almacenados.
     */
    public LinkedList<ArrayList<String>> getLista(){
        return list;
    }

    /**
     * Obtiene el numero de comparaciones realizadas en el algoritmo de ordenamiento: MergeSort.
     * @return Un numero entero de comparaciones realizadas en el algoritmo MergeSort.
     */
    public int getComparaciones() {
        return comparaciones;
    }

    /**
     * Obtiene el numero de intercambios realizados en el algoritmo de ordenamiento: MergeSort.
     * @return Un numero entero de intercambios realizados en el algoritmo MergeSort.
     */
    public int getIntercambios() {
        return intercambios;
    }

    /**
     * Metodo que ordena una lista recursivamente utilizando el algoritmo QuickSort.
     * @param low Indice del primer elemento en la lista a ordenar.
     * @param high Indice del ultimo elemento en la lista a ordenar.
     * @param key Indice del atributo por el cual se va a ordenar la lista.
     * @param order Indica si la lista debe ser ordenada en orden ascendente (valor 1) o descendente (valor diferente de 1).
     * @param tipo Indica el tipo de dato del atributo por el cual se va a ordenar la lista.
     */
    public void recQSort(int low, int high, int key, int order, int tipo){
        int pi;
        if (low < high){
            if(order == 1)
                pi = partitionASC(low, high, key, tipo);
            else 
                pi = partitionDES(low, high, key, tipo);
            
            recQSort(low, pi - 1, key, order, tipo);
            recQSort(pi + 1, high, key, order, tipo);
        }
    }

    /**
     * @param i
     * @param j
     */
    public void swap(int i, int j){
        ArrayList<String> aux = list.get(i);     
        list.set(i, list.get(j));
        list.set(j,aux);
    }
    
    /**
     * Realiza la particion de una sublista en orden ascendente.
     * @param low Indice del primer elemento en la sublista a ordenar.
     * @param high Indice del ultimo elemento en la sublista a ordenar.
     * @param key Indice del atributo por el cual se va a ordenar la sublista.
     * @param tipo Indica el tipo de dato del atributo por el cual se va a ordenar la sublista.
     * @return El Indice del pivote nuevo ascendente.
     */
    public int partitionASC(int low, int high, int key, int tipo){
        int i = (low - 1);
        if(tipo == 1){
            String pivot = list.get(high).get(key);
        
            for(int j = low; j <= high - 1; j++){
                comparaciones++;
                if (list.get(j).get(key).compareTo(pivot) < 0){
                    i++;
                    swap(i, j);
                    intercambios++;
                }
            }
        } else {
            double pivot = Double.parseDouble(list.get(high).get(key));
        
            for(int j = low; j <= high - 1; j++){
                comparaciones++;
                if (Double.parseDouble(list.get(j).get(key)) < pivot){
                    i++;
                    swap(i, j);
                    intercambios++;
                }
            }
        }
        swap(i + 1, high);
        intercambios++;
        return (i + 1);
    }


    /**
     * Realiza la particion de una sublista en orden <b>Descendente</b>.
     * @param low Indice del primer elemento en la sublista a ordenar.
     * @param high Indice del ultimo elemento en la sublista a ordenar.
     * @param key Indice del atributo por el cual se va a ordenar la sublista.
     * @param tipo Indica el tipo de dato del atributo por el cual se va a ordenar la sublista.
     * @return El Indice del pivote nuevo descendente.
     */
    public int partitionDES(int low, int high, int key, int tipo){
        int i = (low - 1);
        if(tipo == 1){
            String pivot = list.get(high).get(key);
        
            for(int j = low; j <= high - 1; j++){
                comparaciones++;
                if (list.get(j).get(key).compareTo(pivot) > 0){
                    i++;
                    swap(i, j);
                    intercambios++;
                }
            }
        } else {

            double pivot = Double.parseDouble(list.get(high).get(key));
        
            for(int j = low; j <= high - 1; j++){
                comparaciones++;
                if (Double.parseDouble(list.get(j).get(key)) > pivot){

                    i++;
                    swap(i, j);
                    intercambios++;
                }
            }
        }
        swap(i + 1, high);
        intercambios++;
        return (i + 1);
    }
}