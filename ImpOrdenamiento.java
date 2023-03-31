import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ImpOrdenamiento {
    
    public static void main(String args[]) throws IOException {

        Scanner entrada = new Scanner(System.in);

        int opc;
        int order;
        int tipo = 2;

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

        bis.binaryInsertionSort(opc - 1, order, tipo);
        LinkedList<ArrayList<String>> listaBinInSort = bis.getLista();
        dao.generarArchivoSalida(listaBinInSort,"ArchivosSalida/BinaryInsertionSort_ordenado.csv");

        quickSort.recQSort(0, lista.size()-1,opc-1,order,tipo);
        LinkedList<ArrayList<String>> listaQSort = quickSort.getLista();
        dao.generarArchivoSalida(listaQSort,"ArchivosSalida/QuickSort_ordenado.csv");

        merge.mergeSort(order, tipo, opc - 1);
        LinkedList<ArrayList<String>> listaMergeSort = merge.getList();
        dao.generarArchivoSalida(listaMergeSort,"ArchivosSalida/MergeSort_ordenado.csv");
        
        radix.radixSort(lista.size(), opc - 1, order, tipo);
        LinkedList<ArrayList<String>> listaRadixSort = radix.getList();
        dao.generarArchivoSalida(listaRadixSort,"ArchivosSalida/RadixSort_ordenado.csv");

        entrada.close();
    }
}
