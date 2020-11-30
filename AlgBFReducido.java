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
         //Siempre la distancia de origen en si mismo es 0
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
                    boolean cambio = false;
                    //pMN representa el peso entre M y N
                    double pMN = A[m][n];
                    //Existe un enlace entre ambos nodos (no es 0)
                    if (pMN != 0) 
                    {
                        //aplicamos paso de relajación
                        if (distancia[m] + pMN < distancia[n]) 
                        {
                            //Al efectuarse la relajación, se procederá a un cambio, por eso cambio será verdadero
                            cambio = true;
                            //Se procede a la actualización, luego del analisis
                            distancia[n] = distancia[m] + pMN; //Actualizamos el peso
                            previo[n] = m+1; //Actualizamos en nodo anterior 
                        }
                    }
                    //Para validad que la tabla ha sido actualizado porque se ha encontrado una distancia más optima
                    if (cambio==true)
                        {
                            printGrafo(A,distancia, previo,j+1);
                        }
                    
                }
            }
            
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
}
