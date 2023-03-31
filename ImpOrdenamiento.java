import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Calendar;


/**
 * Comparación de algoritmos de ordenamiento en un Dataset.
 * @author Aarón Isaac Graniel Arzat
 * @author Daniel García Cetina
 * 
 * @version 1.0
 */
public class ImpOrdenamiento {
    
    public static void main(String args[]) throws IOException {

        Scanner entrada = new Scanner(System.in);

        int opc;
        int order;
        int tipo = 2;
        long millis = 0;

        DaoArchivos dao = new DaoArchivos("datashed.csv");
        LinkedList<ArrayList<String>> lista = dao.getLista();

        QuickSort quickSort = new QuickSort(lista);
        BinaryInsertionSort bis = new BinaryInsertionSort(lista,lista.size());
        MergeSort merge = new MergeSort(lista);
        RadixSort radix = new RadixSort(lista);

        System.out.println("Forma de Ordenamiento:\n1.Ascendente\n2.Descendente\nSeleccione una: ");
        order = entrada.nextInt();

        if(order != 1 && order != 2){
            System.out.println("Orden invalido, se ordenara de forma Ascendente");
            order = 1;
        }

        System.out.println("Ordenar de acuerdo a:\n1.2022 Ranking\n2.Organization Name\n3.Industry\n4.Country\n5.Year Founded\n6.CEO"+
        "\n7.Revenue (Billions)\n8.Profits (Billions)\n9.Assets (Billions)\n10.Market Value (Billions)\n11.Total Employees\nSeleccione una: ");
        opc = entrada.nextInt();

        if (opc == 2 || opc == 3 || opc == 4 || opc == 6){
            tipo = 1;
        } else if((opc == 1 || opc == 5 || opc == 7 || opc == 8 || opc == 9 || opc == 10 || opc == 11)){
            tipo = 2;
        } else 
            opc = 1;

        
        millis = Calendar.getInstance().getTimeInMillis() - millis;
        bis.binaryInsertionSort(opc - 1, order, tipo);
        millis = Calendar.getInstance().getTimeInMillis() - millis;
        System.out.println("Tiempo" + millis);
        LinkedList<ArrayList<String>> listaBinInSort = bis.getLista();
        dao.generarArchivoSalida(listaBinInSort,"ArchivosSalida/BinaryInsertionSort_ordenado.csv");
        dao.generarArchivoSalida("Binary Insertion Sort", String.valueOf(millis), "No vale para este algoritmo", String.valueOf(bis.getComparaciones()));

        millis = 0;

        millis = Calendar.getInstance().getTimeInMillis() - millis;
        quickSort.recQSort(0, lista.size()-1,opc-1,order,tipo);
        millis = Calendar.getInstance().getTimeInMillis() - millis;
        System.out.println("Tiempo" + millis);
        LinkedList<ArrayList<String>> listaQSort = quickSort.getLista();
        dao.generarArchivoSalida(listaQSort,"ArchivosSalida/QuickSort_ordenado.csv");
        dao.generarArchivoSalida("Quick Sort", String.valueOf(millis), String.valueOf(quickSort.getIntercambios()), String.valueOf(quickSort.getComparaciones()));

        millis = 0;

        millis = Calendar.getInstance().getTimeInMillis() - millis;
        merge.mergeSort(order, tipo, opc - 1);
        millis = Calendar.getInstance().getTimeInMillis() - millis;
        System.out.println("Tiempo" + millis);
        LinkedList<ArrayList<String>> listaMergeSort = merge.getList();
        dao.generarArchivoSalida(listaMergeSort,"ArchivosSalida/MergeSort_ordenado.csv");
        dao.generarArchivoSalida("Merge Sort", String.valueOf(millis), "No vale para este algoritmo", String.valueOf(merge.getComparaciones()));
        
        millis = 0;
        
        millis = Calendar.getInstance().getTimeInMillis() - millis;
        radix.radixSort(lista.size(), opc - 1, order, tipo);
        millis = Calendar.getInstance().getTimeInMillis() - millis;
        LinkedList<ArrayList<String>> listaRadixSort = radix.getList();
        System.out.println("Tiempo" + millis);
        dao.generarArchivoSalida(listaRadixSort,"ArchivosSalida/RadixSort_ordenado.csv");
        dao.generarArchivoSalida("Radix Sort", String.valueOf(millis), "No vale para este algoritmo", "No vale para este algoritmo");


        entrada.close();
    }
}
