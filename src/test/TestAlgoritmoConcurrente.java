package test;

import java.util.Random;
import modelo.MultiplicacionMatricesConcurrente;

public class TestAlgoritmoConcurrente {

public static void main(String[] args) {
    	
		// GENERADOR de NÚMEROS ALEATORIOS
        Random rand = new Random();
        int n = rand.nextInt(100) + 20; // Tamaño de las matrices --> rand.nextInt(100) genera un número aleatorio entre 0 y 99
        // int n = 10;
        // Sumando 20, el tamaño 'n' va a ser un número aleatorio entre 20 y 119
        
        // INFORMACIÓN de la PC
        Runtime runtime = Runtime.getRuntime();
        int numeroNucleos = runtime.availableProcessors(); // Se OBTIENE el NÚMERO de PROCESADORES LÓGICOS DISPONIBLES en la MÁQUINA DONDE se está EJECUTANDO el PROGRAMA
        System.out.print("Número de procesadores lógicos:"+numeroNucleos +"\n"); // Este VALOR se UTILIZARÁ para DETERMINAR CUÁNTOS HILOS se DEBEN CREAR PARA PARALELIZAR LA TAREA
        
      	double tiempoInicial, tiempoFinal; // VARIABLES utilizadas para CALCULAR el TIEMPO

        int[][] matriz1 = new int[n][n]; // MATRIZ 1
        int[][] matriz2 = new int[n][n]; // MATRIZ 2
        int[][] matrizResultante = new int[n][n]; // MATRIZ Resultante

        // GENERAR NÚMEROS ALEATORIOS para las MATRICES 1 y 2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz1[i][j] = rand.nextInt(10); // Números aleatorios entre 0 y 9
                matriz2[i][j] = rand.nextInt(10); // Números aleatorios entre 0 y 9
            }
        }
        
        // --- MULTIPLICACIÓN de MATRICES de MANERA CONCURRENTE ---
        
        tiempoInicial = System.nanoTime(); // Captura el tiempo actual en nanosegundos antes de comenzar la ejecución del algoritmo
        
        //HILOS como PROCESADORES LÓGICOS
        Thread[] hilos = new Thread[numeroNucleos];
        
        // CALCULAR el NÚMERO de FILAS de la MATRIZ que PROCESARÁ CADA HILO
        int filasPorHilo = (n + numeroNucleos - 1) / numeroNucleos;    
        
        // PARA cada HILO, CALCULAMOS 'filaInicial', que es la FILA INICIAL que PROCESARÁ el HILO, y 'filaFinal', que es la FILA FINAL
        for (int i = 0; i < numeroNucleos; i++) {
            int filaInicial = i * filasPorHilo;
            int filaFinal = Math.min(filaInicial + filasPorHilo, n); // Nos ASEGURAMOS de que 'filaFinal' NO EXCEDA el NÚMERO TOTAL de FILAS n (límite de la matriz)
            
            // NUEVO HILO ASIGNADO a hilos[i] que PROCESA las FILAS desde filaInicial hasta filaFinal
            hilos[i] = new MultiplicacionMatricesConcurrente(matriz1, matriz2, matrizResultante, filaInicial, filaFinal, n);
            hilos[i].start();
        }
        
        
        // ESPERAR a que TODOS los HILOS TERMINEN
        for (int i = 0; i < numeroNucleos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tiempoFinal = System.nanoTime()-tiempoInicial; // Calcula el tiempo transcurrido en nanosegundos restando el tiempo inicial del tiempo final
        
        
        System.out.println("TAMAÑO DE LAS MATRICES: " + n);
        // Imprimir la matriz A
        System.out.println("Matriz 1:");
        MultiplicacionMatricesConcurrente.mostrarMatriz(matriz1);

        // Imprimir la matriz B
        System.out.println("Matriz 2:");
        MultiplicacionMatricesConcurrente.mostrarMatriz(matriz2);

        // Imprimir la matriz resultante
        System.out.println("Resultado de la multiplicación concurrente:");
        MultiplicacionMatricesConcurrente.mostrarMatriz(matrizResultante);
        
        System.out.print("El programa concurrente, demoro: "+ tiempoFinal/1000 +" microSegundos");
        
    }

}
