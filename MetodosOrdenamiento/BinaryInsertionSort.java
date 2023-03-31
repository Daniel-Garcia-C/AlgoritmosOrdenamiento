import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryInsertionSort {

    private LinkedList<ArrayList<String>> lista;
    private int size;
    private int comparaciones;

    public BinaryInsertionSort (LinkedList<ArrayList<String>> lista, int size){
        this.lista = lista;
        this.size = size;
        this.comparaciones = 0;
    }
 
    public LinkedList<ArrayList<String>> getLista(){
        return lista;
    }

    public int getComparaciones() {
        return comparaciones;
    }

//  implementacion iterativa 
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

    public void binaryInsertionSort(int key, int order, int tipo) {
        comparaciones = 0;
        int i, loc, j;
        ArrayList<String> selected;
    
        for (i = 1; i < size; ++i) {
            j = i - 1;
            selected = lista.get(i);
    
            // encuentra la posicion donde debe ser insertado el elemento
            if(order == 1)
                loc = binarySearchASC(selected, 0, j, key, tipo);
            else
                loc = binarySearchDES(selected, 0, j, key, tipo);
    
            // Hace un corrimiento a la derecha de los datos
            while (j >= loc) {
                lista.set(j+1,lista.get(j));
                j--;
            }
            lista.set(j+1,selected);
        }
    }
}