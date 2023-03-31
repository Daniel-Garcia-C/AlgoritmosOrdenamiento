public class Metrica {
    private long startTime;
    private long endTime;
    private int compare_changes;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long getTiempoTotal() {
        return this.endTime - this.startTime;
    }

    public void setCount(int contador){
        this.compare_changes = contador;
    }
    public int getCount(){
        return this.compare_changes;
    }

    public void displayMetrics(){
        System.out.println("--------------------------\n");
        System.out.println("Tiempo de ejecución \n");
        System.out.println(this.getCount() + " Milisegundos.\n");
        System.out.println("Numero de comparaciones/intercambios");
        System.out.println(this.compare_changes + " comparaciones/intercambios");
        System.out.println("--------------------------\n");
    }
}
