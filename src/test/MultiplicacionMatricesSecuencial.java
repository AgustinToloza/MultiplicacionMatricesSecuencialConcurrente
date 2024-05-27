package test;

import java.util.Random;

public class MultiplicacionMatricesSecuencial {
	
	// MÉTODO que es utilizado para MOSTRAR una MATRIZ
	public static void mostrarMatriz(int[][] matriz) { 
    	for(int i=0;i<matriz.length;i++) { 
    		for(int j=0;j<matriz.length;j++) {
    			System.out.print(matriz[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
	
	public static void main(String[] args) {
	
		// GENERADOR de NÚMEROS ALEATORIOS
        Random rand = new Random(); 
        int n = rand.nextInt(100) + 20; // Tamaño de las matrices --> rand.nextInt(100) genera un número aleatorio entre 0 y 99
        // Sumando 20, el tamaño 'n' va a ser un número aleatorio entre 20 y 119
        // int n = 10;
        
      	double tiempoInicial, tiempoFinal; // VARIABLES utilizadas para CALCULAR el TIEMPO

        int[][] matriz1 = new int[n][n]; // MATRIZ 1
        int[][] matriz2 = new int[n][n]; // MATRIZ 2
        int[][] matrizResultante = new int[n][n]; // MATRIZ RESULTANTE
		
        // GENERAR NÚMEROS ALEATORIOS para las MATRICES 1 y 2
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				matriz1[i][j] = rand.nextInt(10); // Números aleatorios entre 0 y 9 para la Matriz 1
				matriz2[i][j] = rand.nextInt(10); // Números aleatorios entre 0 y 9 para la Matriz 2
			}
		}
		 
		// --- MULTIPLICACIÓN de MATRICES de MANERA SECUENCIAL ---
		
		tiempoInicial = System.nanoTime(); // Captura el tiempo actual en nanosegundos antes de comenzar la ejecución del algoritmo
		
		// -- PRIMER BUCLE que ITERA sobre las FILAS de la MATRIZ 1
        for (int i = 0; i < n; i++) {
        	try {
				Thread.sleep(1); // Se duerme/espera 1 milisegundo para simular una carga mínima
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        	// -- SEGUNDO BUCLE que ITERA sobre las COLUMNAS de la MATRIZ 2
            for (int j = 0; j < n; j++) {
            	
                matrizResultante[i][j] = 0; // INICIALIZAR el ELEMENTO matrizResultante[i][j] a 0
                
                // -- TERCER BUCLE que REALIZA la SUMA de PRODUCTOS para CALCULAR matrizResultante[i][j]
                for (int k = 0; k < n; k++) {
                	
                	// MULTIPLICA el ELEMENTO de Matriz 1 en la FILA i y COLUMNA k
                	// con el ELEMENTO de Matriz 2 en la FILA k y COLUMNA j
                    matrizResultante[i][j] += matriz1[i][k] * matriz2[k][j];  
                    
                }
            }
        }
        
        tiempoFinal = System.nanoTime()-tiempoInicial; // Calcula el tiempo transcurrido en nanosegundos restando el tiempo inicial del tiempo final
        
        System.out.println("TAMAÑO DE LAS MATRICES: " + n);
        // Imprimir la matriz 1
        System.out.println("Matriz 1:");
        mostrarMatriz(matriz1);

        // Imprimir la matriz 2
        System.out.println("Matriz 2:");
        mostrarMatriz(matriz2);

        // Imprimir la matriz resultante
        System.out.println("Resultado de la multiplicación secuencial:");
        mostrarMatriz(matrizResultante);
		
        System.out.println("El programa secuencial, demoró: "+ tiempoFinal/1000 +" microSegundos");
		
	}
}
