import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase para ordenar Lista doblemente ligada de arreglos de Strings mediante el algoritmo de MergeSort
 * @author Aaron Graniel, Daniel Garcia
 * @version 1.0
 */

public class MergeSort{
   /**
    * Atributo de la lista doblemente ligado con una ArrayList de elementos.
    */
   private LinkedList<ArrayList<String>> lista;
   /**
    * Atributo que almacena las comparaciones realizadas en el algoritmo de ordenamiento.
    */
   private int comparaciones;          

   /**
    * Constructor de la clase.
    * @param lista Lista doblemente ligada con ArrayList de elementos de un Dataset.
    */
   public MergeSort(LinkedList<ArrayList<String>> lista)   {
      this.lista = lista;
      this.comparaciones = 0;
   }

   /**
    * Funcion para obtener la lista doblemente ligada con ArrayList de elementos de un Dataset.
    * @return Obtener la lista doblemente ligada con ArrayList de elementos de un Dataset
    */
   public LinkedList<ArrayList<String>> getList(){
      return lista;
   }

   /**
    * Funcion para obtener el numero de comparaciones realizados en el algoritmo de busqueda.
    * @return Numero entero de comparaciones realizadas en el algoritmo de busqueda.
    */
   public int getComparaciones() {
      return comparaciones;
   }

   /**
    * Funcion para generar el espacio de trabajo para el algoritmo de ordenamiento: MergeSort
    * @param order El tipo de orden que se requiere: Ascendente (1) | Descendente (Si es distinto de 1)
    * @param tipo El tipo de dato (String o Entero) a ordenar del Dataset con respecto a las columnas.
    * @param key Es un parametro que indica la clave o campo de lista doblemente ligada por el cual se van a ordenar los datos. 
    */
   public void mergeSort(int order, int tipo, int key) {
      LinkedList<ArrayList<String>> workSpace = new LinkedList<ArrayList<String>>();
      recMergeSort(workSpace, 0, lista.size()-1, order, tipo, key);
   }

   private void recMergeSort(LinkedList<ArrayList<String>> workSpace, int lowerBound, int upperBound, int order, int tipo, int key){
      if(lowerBound == upperBound)            
         return;                              
      else {                                    
         int mid = (lowerBound+upperBound) / 2;    
         recMergeSort(workSpace, lowerBound, mid,order,tipo,key); 
         recMergeSort(workSpace, mid+1, upperBound,order,tipo,key); 
         if(order == 1){
            mergeASC(workSpace, lowerBound, mid+1, upperBound,tipo,key); 
         } else {   
            mergeDES(workSpace, lowerBound, mid+1, upperBound,tipo,key); 
         }
      }  
   }  

   /**
    * Funcion para hacer Merge en forma <b>Ascendente</b> del algoritmo de busqueda MergeSort.
    * @param workSpace Es el array temporal que se utiliza para almacenar los valores ordenados durante el proceso de fusion.
    * @param lowPtr Es un indice que apunta al primer elemento del subarreglo a fusionar.
    * @param highPtr Es un indice que apunta al ultimo elemento del subarreglo a fusionar.
    * @param upperBound Es un indice que apunta al ultimo elemento del arreglo a ordenar.
    * @param tipo Es un parametro que indica el tipo de dato a ordenar con respecto al Dataset.
    * @param key Es un parametro que indica la clave o campo de lista doblemente ligada por el cual se van a ordenar los datos.
    */
   private void mergeASC(LinkedList<ArrayList<String>> workSpace, int lowPtr, int highPtr, int upperBound, int tipo,int key) {
      int j = 0;                             
      int lowerBound = lowPtr;
      int mid = highPtr-1;
      int n = upperBound-lowerBound+1;       
      if(tipo == 2){

         while(lowPtr <= mid && highPtr <= upperBound){
            comparaciones++;
            if( Double.parseDouble(lista.get(lowPtr).get(key)) < Double.parseDouble(lista.get(highPtr).get(key)) )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));
         }

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      } else {

         while(lowPtr <= mid && highPtr <= upperBound){
            comparaciones++;
            if( lista.get(lowPtr).get(key).compareTo(lista.get(highPtr).get(key)) < 0 )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));
         }

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      }
   }

   /**
    * Funcion para hacer Merge en forma <b>Descendente</b> del algoritmo de búsqueda MergeSort.
    * @param workSpace Es el array temporal que se utiliza para almacenar los valores ordenados durante el proceso de fusion.
    * @param lowPtr Es un indice que apunta al primer elemento del subarreglo a fusionar.
    * @param highPtr Es un indice que apunta al último elemento del subarreglo a fusionar.
    * @param upperBound Es un indice que apunta al último elemento del arreglo a ordenar.
    * @param tipo Es un parametro que indica el tipo de dato a ordenar con respecto al Dataset.
    * @param key Es un parametro que indica la clave o campo de lista doblemente ligada por el cual se van a ordenar los datos.
    */
   private void mergeDES(LinkedList<ArrayList<String>> workSpace, int lowPtr, int highPtr, int upperBound, int tipo, int key) {
      int j = 0;                             
      int lowerBound = lowPtr;
      int mid = highPtr-1;
      int n = upperBound-lowerBound+1;       
      if(tipo == 2){

         while(lowPtr <= mid && highPtr <= upperBound){
            comparaciones++;
            if( Double.parseDouble(lista.get(lowPtr).get(key)) > Double.parseDouble(lista.get(highPtr).get(key)) )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));
         }

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      } else {

         while(lowPtr <= mid && highPtr <= upperBound) {
            comparaciones++;
            if( lista.get(lowPtr).get(key).compareTo(lista.get(highPtr).get(key)) > 0 )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));
         }

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      }
   }
}