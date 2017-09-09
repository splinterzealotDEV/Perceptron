import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Entrada {

   private int entradas=0;//numero de columnas de entrada
   private int salidas=0;//numero de columnas de salida
    private String[][] parsedCSV;//matriz parseada del CSV
    private double[][] incognitas;//matriz de incognitas creadas a partir de la matriz original
    private double[][] aprendizaje;//matriz de aprendizaje creada a partir de la matriz original
    private List<Integer> indices;//indices para crear seleccionar las filas a incluir en la matriz de incognitas
    private List<Integer> indicesA;//indices para crear seleccionar las filas a incluir en la matriz de aprendizaje

    public int getSalidas() {
        return salidas;
    }

    public String[][] getParsedCSV() {
        return parsedCSV;
    }

    public double[][] getIncognitas() {
        return incognitas;
    }

    public double[][] getAprendizaje() {
        return aprendizaje;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public List<Integer> getIndicesA() {
        return indicesA;
    }

    public int getEntradas() {

        return entradas;
    }
//llena la matriz de aprendizaje con las filas de la matriz csv que no estan en la lista*

    public double[][] crearMAprendizaje(List<Integer> index, int entradas, int salidas,String[][] MatrizO)
    {
        double[][] matriz=new double[index.size()][entradas+salidas];
        int cont=0;
        int i=0;
        int k=0;
        System.out.println(matriz.length);
        while(k<matriz.length)
        {
            for(int j=0;j<MatrizO[0].length;j++)
            {
                matriz[k][j] = Float.parseFloat(MatrizO[index.get(cont)][j]);
            }
            k++;
            cont++;
        }

        this.aprendizaje=matriz;
        return matriz;

    }







//este metodo metodo crea la matriz de incognitas
    public double[][] crearMincognitas(List<Integer> index, int entradas, int salidas,String[][] MatrizO)
    {
        double[][] matriz=new double[index.size()][entradas+salidas];
        for(int i=0;i<index.size();i++)
        {
            for(int j=0;j<entradas+salidas;j++)
            {
                try {
                    if(j<=entradas) {

                        matriz[i][j] = Float.parseFloat(MatrizO[index.get(i)][j]);
                    }
                    else
                    {
                        matriz[i][j]=0;
                    }
                }
                catch (NumberFormatException ex)
                {
                    matriz[i][j]=0;
                }
            }
        }
        this.incognitas=matriz;
        return matriz;
    }
    //este metodo pide al usuario las columnas de entradas y las de salida
    //se asume que las entradas comienzan desde la primera columna seguidas por las salidas.**
    public void ValoresDeEntrada()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("ingresar entradas");
        this.entradas=sc.nextInt();
        System.out.println("ingresar salidas");
        this.salidas=sc.nextInt();
    }
    //este metodo almacena los indices que contienen salidas incognitas**
    public  ArrayList CalcularIncognitas(String[][] Matriz, int entradas)
    {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        ArrayList<Integer> listaA = new ArrayList<Integer>();
        for(int i=0;i<parsedCSV.length;i++)
        {
            if(parsedCSV[i][entradas].equals("?"))
            {
                lista.add(i);
            }
            else
            {
                listaA.add(i);
            }
        }

        this.indices=lista;
        this.indicesA=listaA;


        return lista;
    }

//constructor
    public Entrada()
    {

    }
//lee el csv y lo parsea a un arreglo 2D**
public String[][] ParsearMatriz(String ruta,int entradas, int salidas) throws IOException {
    String[][] data = new String[16][entradas+salidas];
    File file = new File(ruta);

    int row = 0;
    int col = 0;
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line = null;
    //String e=reader.readLine();

    while ((line = reader.readLine()) != null && row < data.length) {
        StringTokenizer st = new StringTokenizer(line, ",");
        while (st.hasMoreTokens()) {
            data[row][col] = (st.nextToken());

            col++;
        }
        col = 0;
        row++;
    }
   /* for(int i=0;i<10;i++)
    {
        for(int j=0;j<7;j++)
        {
            System.out.println(data[i][j]);
        }
    }*/
   this.parsedCSV=data;
   return data;
    }
    public boolean EstaEn(List<Integer> lista, int value)
    {
        for(int i=0;i<lista.size();i++)
        {
            if(lista.get(i)==value)
            {
                return true;
            }
        }
        return false;
    }
    public void imprimirMatriz(double[][] M)
    {
        for(int i=0;i<M.length;i++)
        {
            for(int j=0;j<M[0].length;j++)
            {
                System.out.println(M[i][j]);
            }
        }
    }


}

