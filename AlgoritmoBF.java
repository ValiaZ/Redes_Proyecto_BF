/**
 *
 * @author LaptopValia
 */
public class algoritmo {
    private double[][] matriz; //Derivada del grafo
    private double[] peso; //Coste del enlace
    private int[] previo; //Aquí se actualizara el nodo
    private Integer nodoL; //Para ubicar al nodo
    
    //Constructor
    public algoritmo(double[][] matriz) {
        this.matriz = matriz;
        this.peso = new double[nodoL];
        this.previo= new int[nodoL];
        this.nodoL = matriz.length;
    }
    
    public void ejecutaBF (int origen){
        //Para el inicio de la tabla, se iniciliza de la sgte manera:
        //los pasos de relajación seran |V| - 1 veces
        for (int i=0; i < nodoL; i++)
        {
            //Para todos los nodos de la tabla
            peso[i] = Double.POSITIVE_INFINITY; //Al inicio, el peso será infinito positivo
            previo[i] = -1; //Al inicio, no hay un nodo 
        }
        //iniciamos en el nodo raiz (origen)
        peso [origen] = (double) 0 ; //Al inicio, la distancia es siempre 0
        
        imprimir();
        
        for (int j = 0; j < nodoL - 1; j++) {
            for (int m = 0; m < matriz.length; m++) {
                for (int n = 0; n < matriz.length; n++) {
                    double pMN = matriz[m][n];
                    if (pMN != 0)
                    {
                        //aplicamos paso de relajación
                        if (peso[m] + pMN < peso[n])
                        {
                            //Se procede a la actualización, luego del analisis
                            peso [n] = peso[m] + pMN;
                            previo[n] = m;
                        }
                    }
                }
            }
            imprimir();
        }
    }
    //Para detectar si existe o no ciclos negativos en el grafo
    public boolean existeCicloNeg()
    {
        boolean resp = false; //Se asume que en un inicio no existen ciclos negativos
        for (int i = 0; i < nodoL - 1; i++) {
            for (int m = 0; m < matriz.length; m++) {
                for (int n = 0; n < matriz.length; n++) {
                    //Sabemos que m,n unen a un enlace E
                    //aplicamos paso de relajación
                    if (peso[m] + matriz[m][n] < peso[n])
                    {
                        resp = true;
                        System.out.println("Existe ciclo negativo");
                    }
                }
            }
        }
        return resp;
    }
    
    public void imprimir() {
        for (int i = 0; i < nodoL; i++) {
            String p = (peso[i] == Double.POSITIVE_INFINITY ? "INF" : String.valueOf(peso[i]));
            System.out.println(i + "-[Previo: " + previo[i] + ", DISTANCIA: "+ p + "] ");
        }
        System.out.println("\n");
    }
    public static void main(String[] args){
        //Agregar implementación los grafos de 5 nodos, 8 nodos y 11 nodos. 
    }
