package modelo;

public class MultiplicacionMatricesConcurrente extends Thread{ // CLASE que EXTIENDE de THREAD para REALIZAR la MULTIPLICACIÓN de una POSICIÓN ESPECÍFICA de la MATRIZ
	private int[][] matriz1;
    private int[][] matriz2;
    private int[][] matrizResultante;
    private int filaInicial;
    private int filaFinal;
    private int n;
    
    // CONSTRUCTOR para INICIALIZAR el HILO con la FILA y COLUMNA a procesar
    public MultiplicacionMatricesConcurrente(int[][] matriz1, int[][] matriz2, int[][] matrizResultante, int filaInicial, int filaFinal, int n) {
    	this.matriz1 = matriz1; // MATRIZ 1
        this.matriz2 = matriz2; // MATRIZ 2
        this.matrizResultante = matrizResultante; // MATRIZ RESULTANTE 
        this.filaInicial = filaInicial; // FILA específica a procesar
        this.filaFinal = filaFinal; // COLUMNA específica a procesar
        this.n = n; // TAMAÑO de las MATRICES
    }
    
    // MÉTODO que se EJECUTA cuando el HILO empieza
    @Override
    public void run() {
        for(int i = filaInicial; i < filaFinal; i++) { // -- PRIMER BUCLE que ITERA sobre las FILAS que este HILO debe PROCESAR, desde filaInicial hasta filaFinal
         	
         	try {
				sleep(1); // Se duerme/espera 1 milisegundo para simular una carga mínima
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
          
        	for(int j = 0; j < n; j++) { // -- SEGUNDO BUCLE que ITERA sobre las COLUMNAS de la MATRIZ 2
        		
        		matrizResultante[i][j] = 0; // INICIALIZA el ELEMENTO matrizResultante[i][j] a 0 ANTES de COMENZAR la SUMA de PRODUCTOS
        		
        		for(int k = 0; k < n; k++) { // -- TERCER BUCLE que REALIZA la SUMA de PRODUCTOS para CALCULAR el VALOR de matrizResultante[i][j]
        			matrizResultante[i][j] += matriz1[i][k] * matriz2[k][j];
        		}
        	}
        	
        }
    }
    
    // MÉTODO que es utilizado para MOSTRAR una MATRIZ
    public static void mostrarMatriz(int[][] matriz) { 
    	for(int i=0;i<matriz.length;i++) {
    		for(int j=0;j<matriz.length;j++) {
    			System.out.print(matriz[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}