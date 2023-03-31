import java.util.ArrayList;
import java.util.LinkedList;

public class QuickSort{

    private LinkedList<ArrayList<String>> list;
    int swapCounter;

    public QuickSort(LinkedList<ArrayList<String>> list)   {
        this.list = list;
        this.swapCounter = 0;
    }

    public LinkedList<ArrayList<String>> getLista(){
        return list;
    }

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

    public void swap(int i, int j){
        ArrayList<String> aux = list.get(i);     
        list.set(i, list.get(j));
        list.set(j,aux);
        this.swapCounter++;
    }
    
    public int partitionASC(int low, int high, int key, int tipo){
        int i = (low - 1);
        if(tipo == 1){
            String pivot = list.get(high).get(key);
        
            for(int j = low; j <= high - 1; j++){
                if (list.get(j).get(key).compareTo(pivot) < 0){
                    i++;
                    swap(i, j);
                }
            }
        } else {
            double pivot = Double.parseDouble(list.get(high).get(key));
        
            for(int j = low; j <= high - 1; j++){
                if (Double.parseDouble(list.get(j).get(key)) < pivot){

                    i++;
                    swap(i, j);
                }
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }


    public int partitionDES(int low, int high, int key, int tipo){
        int i = (low - 1);
        if(tipo == 1){
            String pivot = list.get(high).get(key);
        
            for(int j = low; j <= high - 1; j++){
                if (list.get(j).get(key).compareTo(pivot) > 0){
                    i++;
                    swap(i, j);
                }
            }
        } else {
            double pivot = Double.parseDouble(list.get(high).get(key));
        
            for(int j = low; j <= high - 1; j++){
                if (Double.parseDouble(list.get(j).get(key)) > pivot){

                    i++;
                    swap(i, j);
                }
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    public int getSwapCounter(){
        return swapCounter;
    }
}