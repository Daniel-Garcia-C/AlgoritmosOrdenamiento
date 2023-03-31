/**
 * Clase para ordenar Lista doblemente ligada de arreglos de Strings mediante el algoritmo de Radix Sort
 * @author Aaron Graniel, Daniel Garcia
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.LinkedList;

class RadixSort {
	
	/**
	 *  * lista de los datos que se quieren ordenar
	 */
	private LinkedList<ArrayList<String>> array = new LinkedList<ArrayList<String>>();

	/**
	 * Crea un objeto RadixSort. 
	 * @param lista La lista de elementos que se quiere ordenar.
	 */
	public RadixSort(LinkedList<ArrayList<String>> lista){
		this.array = lista;
	}

	/**
	 * Metodo para obtener la list de los elementos.
	 * @return <ul>
     *         <li>La lista ordena, si hemos invocamos previamente el metodo radixSort</li>
     *         <li>La lista sin ordenar, si no hemos invocamos el metodo radixSort</li>
     *         </ul>
	 */
	public LinkedList<ArrayList<String>> getList(){
		return array;
	}

	/**
	 * Ordena una lista de cadenas utilizando el algoritmo de ordenación de counting sort en orden ascendente, según el caracter en la posicion dada. 
	 * @param place la posición del caracter en cada cadena a tener en cuenta para la ordenacion
	 * @param key el indice del arreglo que se usara para la ordenacion
	 */
	public void countingSortStrASC(int place, int key) {
		LinkedList<ArrayList<String>> temp = new LinkedList<ArrayList<String>>();
        for(int i = 0; i < array.size();i++){
            temp.add(new ArrayList<String>());
        }

		int[] count = new int[256];
        for (int i = 0; i < array.size();i++) {
            String s = array.get(i).get(key);
            int index = (place < s.length()) ? (int) s.charAt(place) : 0;
            count[index]++;
        }
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }
        for (int i = array.size() - 1; i >= 0; i--) {
            int index = (place < array.get(i).get(key).length()) ? (int) array.get(i).get(key).charAt(place) : 0;
            temp.set(--count[index], array.get(i));
        }
        array.clear();
        array.addAll(temp);
	}

	/**
	 * Ordena una lista de cadenas utilizando el algoritmo de ordenacion de counting sort en orden descendente, según el carácter en la posición dada. 
	 * @param place la posición del caracter en cada cadena a tener en cuenta para la ordenacion
	 * @param key el indice del arreglo que se usara para la ordenación
	 */
	public void countingSortStrDES(int place, int key) {
		LinkedList<ArrayList<String>> temp = new LinkedList<ArrayList<String>>();
        for(int i = 0; i < array.size();i++){
            temp.add(new ArrayList<String>());
        }

		int[] count = new int[256];
        for (int i = 0; i < array.size();i++) {
            String s = array.get(i).get(key);
            int index = (place < s.length()) ? (int) s.charAt(place) : 0;
            count[index]++;
        }
        for (int i = 254; i >= 0; i--) {
            count[i] += count[i + 1];
        }
        for (int i = array.size() - 1; i >= 0; i--) {
            int index = (place < array.get(i).get(key).length()) ? (int) array.get(i).get(key).charAt(place) : 0;
            temp.set(--count[index], array.get(i));
        }
        array.clear();
        array.addAll(temp);
	}


	/**
	 * Obtiene la longitud mas larga de una String de la lista de elementos
	 * @param key el indice del arreglo que se usará para la ordenacion
	 * @return  la longitud mas larga de una String de la lista
	 */
	public int getMaxLength(int key) {
		int maxLen = 0;
        for (ArrayList<String> arrayList : array) {
            for (int i = 0; i<arrayList.size();i++) {
                if (arrayList.get(0).length() > maxLen) {
                    maxLen = arrayList.get(0).length();
                }
            }
        }
        return maxLen;
	}

	/**
	 * Ordena una lista de numeros utilizando el algoritmo de ordenación de counting sort, segun una posición de un digito a considerar.
	 * @param place la posicion del digito a considerar en el ordenamiento
	 * @param key el indice del arreglo que se usara para la ordenacion
	 */
	public void countingSortNum(int place, int key) {
		LinkedList<ArrayList<String>> output = new LinkedList<ArrayList<String>>();
		for (int i = 0; i < 1999; i++) {
			output.add(new ArrayList<String>());
		}
		int[] count = new int[10];
		for (int i = 0; i < 1999; i++){
			if(Double.parseDouble(array.get(i).get(key))<0){
				count[-1*(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]++;
			} else {
				count[(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]++;
			}
		}

		for (int i = 1; i < 10; i++)
		  count[i] += count[i - 1];
	
		for (int i = array.size() - 1; i >= 0; i--) {
			if(Double.parseDouble(array.get(i).get(key))<0){
				output.set(count[-1*(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]-1, array.get(i));
		  		count[-1*(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]--;
			} else {
				output.set(count[(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]-1, array.get(i));
		  		count[(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]--;
			}
		  	
		}

		for (int i = 0; i < array.size(); i++)
			array.set(i, output.get(i));

	}

	/**
	 * Obtiene el numero mas grande de lista de elementos
	 * @param n el tamano del lista
	 * @param key el indice del arreglo que se usara para la ordenacion
	 * @return  el numero mas grande de lista de elementos
	 */
	public double getMax(int n, int key) {
		double max = Double.parseDouble(array.get(0).get(key));
		for (int i = 1; i < n; i++)
		  if (Double.parseDouble(array.get(i).get(key)) > max)
			max = Double.parseDouble(array.get(i).get(key));
		return max;
	}

	/**
	 * Ordena la lista, previamente proporcionada durante la creacion del objeto RadixSort, por el valor de una llave especifica usando el algoritmo de ordenamiento Radix Sort.
	 * @param key el indice del arrayList que se usará para la ordenacion de toda la lista
	 * @param order el orden en que se desea ordenar el ArrayList (1 para ascendente, 2 para descendente).
	 * @param tipo el tipo de datos que se quiere ordenar (1 para String, 2 para Double).
	 */
	public void radixSort(int key,int order, int tipo) {

		if(tipo == 2){
			double max = getMax(array.size(), key);

			for (double place = 1; max / place > 0; place *= 10){
				countingSortNum((int)place, key);
			}
			if(order == 2) {
				LinkedList<ArrayList<String>> temp = new LinkedList<ArrayList<String>>();
				for(int i = array.size()-1; i>=0;i--) 
					temp.add(array.get(i));
				
				for(int i = 0; i<1999;i++) 
					array.set(i,temp.get(i));	
				
			}
			
		} else {
			int maxLeg = getMaxLength(key);
			if(order == 1){
				for (int place = maxLeg - 1; place >= 0; place--){
					countingSortStrASC(place, key);
				}
			} else {
				for (int place = maxLeg - 1; place >= 0; place--)
					countingSortStrDES(place, key);
			}
		}
	}
}
