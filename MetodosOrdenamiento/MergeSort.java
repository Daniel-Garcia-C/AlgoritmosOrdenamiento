import java.util.ArrayList;
import java.util.LinkedList;

public class MergeSort{
   private LinkedList<ArrayList<String>> lista;          // ref to array theArray

   public MergeSort(LinkedList<ArrayList<String>> lista)   {
      this.lista = lista;
   }

   public LinkedList<ArrayList<String>> getList(){
      return lista;
   }

   public void mergeSort(int order, int tipo, int key) {
      LinkedList<ArrayList<String>> workSpace = new LinkedList<ArrayList<String>>();
      recMergeSort(workSpace, 0, lista.size()-1, order, tipo, key);
   }

   private void recMergeSort(LinkedList<ArrayList<String>> workSpace, int lowerBound, int upperBound, int order, int tipo, int key){
      if(lowerBound == upperBound)            // if range is 1,
         return;                              // no use sorting
      else {                                    
         int mid = (lowerBound+upperBound) / 2; // find midpoint    
         recMergeSort(workSpace, lowerBound, mid,order,tipo,key); // sort low half
         recMergeSort(workSpace, mid+1, upperBound,order,tipo,key); // sort high half
         if(order == 1){
            mergeASC(workSpace, lowerBound, mid+1, upperBound,tipo,key); // merge them
         } else {   
            mergeDES(workSpace, lowerBound, mid+1, upperBound,tipo,key); // merge them
         }
      }  // end else
   }  // end recMergeSort()

   private void mergeASC(LinkedList<ArrayList<String>> workSpace, int lowPtr, int highPtr, int upperBound, int tipo,int key) {
      int j = 0;                             // workspace index
      int lowerBound = lowPtr;
      int mid = highPtr-1;
      int n = upperBound-lowerBound+1;       // # of items
      if(tipo == 2){

         while(lowPtr <= mid && highPtr <= upperBound)
            if( Double.parseDouble(lista.get(lowPtr).get(key)) < Double.parseDouble(lista.get(highPtr).get(key)) )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      } else {

         while(lowPtr <= mid && highPtr <= upperBound)
            if( lista.get(lowPtr).get(key).compareTo(lista.get(highPtr).get(key)) < 0 )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      }
   }

   private void mergeDES(LinkedList<ArrayList<String>> workSpace, int lowPtr, int highPtr, int upperBound, int tipo, int key) {
      int j = 0;                             // workspace index
      int lowerBound = lowPtr;
      int mid = highPtr-1;
      int n = upperBound-lowerBound+1;       // # of items
      if(tipo == 2){

         while(lowPtr <= mid && highPtr <= upperBound)
            if( Double.parseDouble(lista.get(lowPtr).get(key)) > Double.parseDouble(lista.get(highPtr).get(key)) )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      } else {

         while(lowPtr <= mid && highPtr <= upperBound)
            if( lista.get(lowPtr).get(key).compareTo(lista.get(highPtr).get(key)) > 0 )
               workSpace.add(j++,lista.get(lowPtr++));
            else
               workSpace.add(j++,lista.get(highPtr++));

         while(lowPtr <= mid)
            workSpace.add(j++,lista.get(lowPtr++));

         while(highPtr <= upperBound)
         workSpace.add(j++,lista.get(highPtr++));

         for(j=0; j<n; j++)
            lista.set(lowerBound+j, workSpace.get(j));

      }
   }
}