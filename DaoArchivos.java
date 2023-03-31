import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DaoArchivos {
    
    private String direccion;
    private LinkedList<ArrayList<String>> lista = new LinkedList<ArrayList<String>>();
    
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

    public LinkedList<ArrayList<String>> getLista(){
        return this.lista;
    }

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
    
}

    
