
import java.util.ArrayList;
import java.util.LinkedList;

class RadixSort {
  
	private LinkedList<ArrayList<String>> array = new LinkedList<ArrayList<String>>();

	public RadixSort(LinkedList<ArrayList<String>> lista){
		for(int i = 0; i < lista.size();i++) {
			array.add(i, lista.get(i));
		}
	}

	public LinkedList<ArrayList<String>> getList(){
		return array;
	}

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

	public void countingSortNumASC(int size, int place, int key) {
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
	
		for (int i = size - 1; i >= 0; i--) {
			if(Double.parseDouble(array.get(i).get(key))<0){
				output.set(count[-1*(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]-1, array.get(i));
		  		count[-1*(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]--;
			} else {
				output.set(count[(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]-1, array.get(i));
		  		count[(int)((Double.parseDouble(array.get(i).get(key)) / place) % 10)]--;
			}
		  	
		}

		for (int i = 0; i < size; i++)
			array.set(i, output.get(i));

	}

	public double getMax(int n, int key) {
		double max = Double.parseDouble(array.get(0).get(key));
		for (int i = 1; i < n; i++)
		  if (Double.parseDouble(array.get(i).get(key)) > max)
			max = Double.parseDouble(array.get(i).get(key));
		return max;
	}

	public void radixSort(int size, int key,int order, int tipo) {

		if(tipo == 2){
			size = array.size();
			double max = getMax(size, key);

			for (double place = 1; max / place > 0; place *= 10){
				countingSortNumASC(size, (int)place, key);
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
