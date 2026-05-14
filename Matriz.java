package Examen;

public class Matriz implements Runnable {

    public String[][] tabla = {
            {"Java", "Python", "Java"},
            {"C++", "Java", "Go"},
            {"Java", "Rust", "Java"}
    };

    public static final String buscado = "Java";

    public int fila;

    public int encontrados = 0;

    public Matriz(int fila) {
        this.fila = fila;
    }

    @Override
    public void run() {

        for (int j = 0; j < tabla[fila].length; j++) {

            if (tabla[fila][j].equals(buscado)) {
                encontrados++;
            }
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " encontro "
                        + encontrados
                        + " veces la palabra en la fila "
                        + fila
        );
    }

    public int getEncontrados() {
        return encontrados;
    }

    public static void main(String[] args) {

        System.out.println("Palabra buscada: " + buscado);

        Matriz fila0 = new Matriz(0);
        Matriz fila1 = new Matriz(1);
        Matriz fila2 = new Matriz(2);

        Thread hilo1 = new Thread(fila0, "Hilo 1");
        Thread hilo2 = new Thread(fila1, "Hilo 2");
        Thread hilo3 = new Thread(fila2, "Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {

            hilo1.join();
            hilo2.join();
            hilo3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total =
                fila0.getEncontrados()
                        + fila1.getEncontrados()
                        + fila2.getEncontrados();

        System.out.println("\nTotal encontrado: " + total);
    }
}