import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase para ordenar acceder a la informacion de datos de un archivo y crear o modificar archivos.
 * @author Aaron Graniel, Daniel Garcia
 * @version 1.0
 */

public class DaoArchivos {
    /**
     * La direccion URL del archivo (.csv o .txt).
     */
    private String direccion;

    /**
     * La lista doblemente ligada que alberga la informacion de cada elemento del Dataset.
     */
    private LinkedList<ArrayList<String>> lista = new LinkedList<ArrayList<String>>();
    
    /**
     * La funcion que accede a la informacion del archivo en particular.
     * @param direccion La direccion URL del archivo (.csv o .txt).
     * @throws IOException si el archivo tiene un error de entrada o salida de texto.
     */
    public DaoArchivos(String direccion) throws IOException{
        this.direccion = direccion;
        
        String separador = ",";
        BufferedReader bufferLectura = null;
        try {
            bufferLectura = new BufferedReader(new FileReader(this.direccion));
            String linea = bufferLectura.readLine();;

            while ((linea = bufferLectura.readLine()) != null){
                String[] datos = linea.split(separador);
                ArrayList<String> fila = new ArrayList<String>();
                for(int i = 0; i<datos.length; i++){

                    fila.add(datos[i]);
                }
                lista.add(fila);
            }

            File archivoSalida = new File("ArchivosSalida/Metricas.txt");
            FileWriter writer = new FileWriter(archivoSalida, false);
            writer.write("Métricas de Ordenamiento\n");
            writer.write("=========================\n\n");

            writer.write("Descripción de las métricas:\n\n");

            writer.write("El tiempo total de ejecución indica la cantidad de tiempo que tomó completar el ordenamiento.\n");
            writer.write("El número total de comparaciones indica la cantidad de veces que se compararon dos elementos para determinar su orden.\n");
            writer.write("El número total de intercambios indica la cantidad de veces que se intercambiaron dos elementos para ordenar la lista.\n\n");
            writer.close();
        }catch(NumberFormatException ex){
            System.out.println("Error al obtener las calificaciones");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Exception: Documento No Encontrado");
            throw fnfe;
        }   catch (IOException ioE) {
            System.out.println("Exception: Entrada o salida de datos Incorrecta");
            throw ioE;
        } finally {
            if (bufferLectura != null){
                bufferLectura.close();
            }
        }
    }

    /**
     * Funcion de retorno de la lista doblemente ligada.
     * @return La lista doblemente ligada con sus elementos asignados.
     */
    public LinkedList<ArrayList<String>> getLista(){
        return this.lista;
    }

    /**
     * Funcion que genera un archivo nuevo de salida con respecto a los elementos otorgados en una lista doblemente ligada.
     * @param lista La lista doblemente ligada con elementos de datos.
     * @param direccion El nombre del archivo a actualizar o crear en una carpeta.
     * @return El numero 0 en caso de ser exitoso la creacion de archivo.
     * @throws IOException En caso de fallo con la escritura de entrada o salida del archivo.
     */
    public int generarArchivoSalida(LinkedList<ArrayList<String>> lista, String direccion) throws IOException{
        try {
            File archivoSalida = new File(direccion);
            FileWriter writer = new FileWriter(archivoSalida, false);
            writer.write("2022 Ranking,Organization Name,Industry,Country,Year Founded,CEO,Revenue (Billions),Profits (Billions),Assets (Billions),Market Value (Billions),Total Employees\n");
            for (int i = 0; i < lista.size(); i++){
                for(int j = 0; j < lista.get(i).size(); j++){
                    if(j==lista.get(i).size()-1){
                        writer.write(lista.get(i).get(j));
                    }
                    else
                        writer.write(lista.get(i).get(j) + ",");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Exception: Documento No Encontrado en: " + direccion);
        }
        System.out.println("Archivo generado con exito");
        return 0;
    }

    /**
     * Funcion para generar un archivo de salida con las metricas de cada algoritmo.
     * <ul>
     * <li>Tiempo de ejecucion del algoritmo
     * <li>Numero de comparaciones
     * <li>Numero de intercambios
     * <ul>
     * @param nombre Es el nombre del algoritmo de ordenamiento a anadir al archivo.
     * @param time Es el tiempo de ejecucion del algoritmo de ordenamiento.
     * @param intercambios Es el numero de intercambios realizado por el algoritmo de ordenamiento.
     * @param comparaciones Es el numero de comparaciones realizado por el algoritmo de ordenamiento.
     * @return El numero 0 en caso de ser exitoso la creacion de archivo.
     * @throws IOException En caso de fallo con la escritura de entrada o salida del archivo.
     */
    public int generarArchivoSalida(String nombre, String time, String intercambios, String comparaciones) throws IOException {
        try {
            File archivoSalida = new File("ArchivosSalida/Metricas.txt");
            FileWriter writer = new FileWriter(archivoSalida, true);
            writer.write("Algoritmo de " + nombre + "\n");
            writer.write("Tiempo de ejecución: " + time + "ms\n");
            writer.write("Número de comparaciones: " + comparaciones + "\n");
            writer.write("Número de intercambios: " + intercambios + "\n\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Exception: Documento No Encontrado en: " + direccion);
        }
        return 0;
    }
    
}

    
