/**
 *
 * @author LaptopValia
 */

import java.util.Scanner;

public class Trabajo_Redes {
   
   public static void algoritmoBF (int[][]A, int origen)
    {
        //Inicializamos de acuerdo al grafo ingresado
        //Creamos una lista para almacenar los pesos de las distancias entre cada nodo
         double[] distancia = new double[A.length];
         for (int i = 0; i < distancia.length; i++) {
            //Al iniciar, siempre seran numeros grandes
            distancia[i] = Double.POSITIVE_INFINITY;
         }
         
         //Iniciamos una lista previo donde almacenaremos el nodo previo
         int[] previo = new int[A.length];
         for (int i = 0; i < previo.length; i++) {
             previo[i] = -1;
         }
         //Siempre la distancia de origen es 0
         distancia[origen] = 0; 
         //Imprimimos la tabla al inicio (antes de la ejecucion)
         printGrafo(A,distancia, previo,0);
         
         //El j es la cantidad de veces que se va a analizar el grafo (nroNodos - 1)
         for (int j = 0; j < A.length - 1; j++) 
         {
            //Para ubicarnos en el nodo de inicio
            for (int m = 0; m <  A.length; m++) 
            {
                for (int n = 0; n <  A.length; n++) 
                {
                    //pMN representa el peso entre M y N
                    double pMN = A[m][n];
                    //Existe un enlace entre ambos nodos (no es 0)
                    if (pMN != 0) 
                    {
                        //aplicamos paso de relajación
                        if (distancia[m] + pMN < distancia[n]) 
                        {
                            //Se procede a la actualización, luego del analisis
                            distancia[n] = distancia[m] + pMN;
                            previo[n] = m+1;
                        }
                    }
                }
                //En el caso que se quiera ver el paso a paso de cada iteracion
                //printGrafo(A,distancia, previo,j+1);
            }
            //Para imprimir el resultado final de cada iteracion
            printGrafo(A,distancia, previo,j+1);
         }
         if (existeCicloNeg(A,distancia,previo) == true)
            {
                System.out.println("Existe ciclo negativo");
                System.out.println(" ");
            }
                     
    }
    
    public static void printGrafo (int[][] A, double[]d, int[]p, int j)
    {
        System.out.println("----------------ITERACION: " + j + "--------------------------");
        System.out.print("Vertices: ");
        for (int i = 1; i < A.length + 1; i++) {
            System.out.print(i + " | ");
        }        
        System.out.println("");
        System.out.println("_____________________________________________________");
        
        
        System.out.print("Distancia[u]: ");
        for (int i = 0; i < d.length; i++) {
            System.out.print(d[i] + " | ");
        }
        System.out.println("");
        System.out.println("_____________________________________________________");
        
        
        System.out.print("Previo[u]: ");
        for (int i = 0; i < p.length; i++) {
            
            System.out.print(p[i] + " | ");
        }
        System.out.println("");
        System.out.println("_______________________________________________________");
        System.out.println("");
        
    }
    
    public static boolean existeCicloNeg(int[][]A, double[] d, int[] p) {

        boolean resp = false; //Se asume que en un inicio no existen ciclos negativos
        for (int i = 0; i < A.length - 1; i++) {
            for (int m = 0; m < A.length; m++) {
                for (int n = 0; n < A.length; n++) {
                    //Sabemos que m,n unen a un enlace E
                    //aplicamos paso de relajación
                    if (d[m] + A[m][n] < d[n]) {
                        //Si es posivito, hemos detectado un ciclo negativo
                        resp = true;
                    }
                }
            }
        }
        return resp;
    }
    
    public static void main(String[] args) {
        //Agregar implementación los grafos de 5 nodos, 8 nodos y 11 nodos.
        //Grafo de 5 nodos
        int[][]A = {{0,0,0,2,5},
                    {7,0,0,0,0},
                    {0,2,0,0,6},
                    {0,1,8,0,0},
                    {0,0,0,3,0}};
        //Grafo de 8 nodos
        int[][]B = {{0,0,0,2,5,0,0,0},
                    {7,0,0,1,0,0,0,0},
                    {0,2,0,0,6,0,0,0},
                    {0,1,8,0,0,0,5,0},
                    {0,0,0,1,0,3,0,0},
                    {0,0,0,0,0,0,7,0},
                    {0,0,0,0,0,0,0,4},
                    {0,0,3,6,0,0,4,0}};
        //Grafo de 11 nodos
        int[][]C = {{0,0,0,2,5,0,0,0,0,0,0},
                    {7,0,0,0,0,0,0,0,0,0,7},
                    {0,2,0,0,6,0,0,0,0,0,5},
                    {0,1,8,0,0,0,5,0,0,0,0},
                    {0,0,0,1,0,3,0,0,0,0,0},
                    {0,0,0,0,0,0,7,0,1,0,0},
                    {0,0,0,0,0,0,0,4,0,3,0},
                    {0,0,3,6,0,0,0,0,0,6,0},
                    {0,0,0,0,0,0,2,0,0,0,0},
                    {0,0,0,0,0,0,0,0,4,0,0},
                    {0,0,0,0,0,0,0,1,0,0,0}};
              
        //Para que ingrese el nodo origen:
        Scanner entrada1 = new Scanner(System.in);
        int n; //Definimos el numero de nodo a ingresar como entero
        System.out.print("Ingrese nodo origen para el grafo de 5 nodos: ");
        n = entrada1.nextInt();
        //Ya con el nodo origen elegido, procedemos a calcular en los grafos de 5, 8 y 11 nodos.
        //Ejecutar grafo basico de 5 nodos
        System.out.println("|||| ALGORITMO BELLMAN FORD PARA GRAFO DE 5 NODOS ||||");
        algoritmoBF(A,n-1);
        
        Scanner entrada2 = new Scanner(System.in);
        int x; //Definimos el numero de nodo a ingresar como entero
        System.out.print("Ingrese nodo origen para el grafo de 8 nodos: ");
        x = entrada2.nextInt();
        //Ejecutar grafo intermedio de 8 nodos
        System.out.println("|||| ALGORITMO BELLMAN FORD PARA GRAFO DE 8 NODOS ||||");
        algoritmoBF(B,x-1);
                 
        Scanner entrada3 = new Scanner(System.in);
        int y; //Definimos el numero de nodo a ingresar como entero
        System.out.print("Ingrese nodo origen para el grafo de 11 nodos: ");
        y = entrada3.nextInt();
        //Ejecutar grafo avanzado de 11 nodos
        System.out.println("|||| ALGORITMO BELLMAN FORD PARA GRAFO DE 11 NODOS ||||");
        algoritmoBF(C,y-1);
    }
  
}
