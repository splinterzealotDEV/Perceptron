import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Entrada {

    private int filas=0;
   private int entradas=0;//numero de columnas de entrada
   private int salidas=0;//numero de columnas de salida
    private String[][] parsedCSV;//matriz parseada del CSV
    private double[][] incognitas;//matriz de incognitas creadas a partir de la matriz original
    private double[][] aprendizaje;//matriz de aprendizaje creada a partir de la matriz original
    private double[][] deseados;
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

    /**
     * llena la matriz de aprendizaje con las filas de la matriz csv que no estan en la lista*
     * @param index indices de las filas que son patrones de aprendizaje
     * @param entradas numero de columnas de enradas de la matriz
     * @param salidas numero de columnas de salida de la matriz
     * @param MatrizO matriz del csv parseado
     * @return
     */
    public double[][] crearMAprendizaje(List<Integer> index, int entradas, int salidas,String[][] MatrizO)
    {
        double[][] matriz=new double[index.size()][entradas];
        int cont=0;
        int i=0;
        int k=0;
        System.out.println(matriz.length);
        while(k<matriz.length)
        {
            for(int j=0;j<entradas;j++)
            {
                matriz[k][j] = Float.parseFloat(MatrizO[index.get(cont)][j]);
            }
            k++;
            cont++;
        }

        this.aprendizaje=matriz;
        return matriz;

    }


    public double[][] getDeseados() {
        return deseados;
    }

    /**
     * metodo para crear la matriz de valores deseados
     * @return la matriz de valores deseados
     */
    public double[][] crearMDeseados()
{
    int cont;
    double[][] Matriz=new double[indicesA.size()][salidas];
    for(int i=0;i<indicesA.size();i++)
    {
        cont=this.entradas;
        for(int j=0;j<this.salidas;j++)
        {
            Matriz[i][j]=Double.parseDouble(getParsedCSV()[indicesA.get(i)][cont]);
            cont++;
        }
    }
    this.deseados=Matriz;

    return Matriz;
}

    /**
     * este metodo metodo crea la matriz de incognitas
     * @param index lista con los indices de las filas con salidas incognitas
     * @param entradas numero de columnas de entrada de la matriz
     * @param salidas numero de columnas de salida de la matriz
     * @param MatrizO matriz parseada del csv
     * @return
     */

    public double[][] crearMincognitas(List<Integer> index, int entradas, int salidas,String[][] MatrizO)
    {
        double[][] matriz=new double[index.size()][entradas];
        for(int i=0;i<index.size();i++)
        {
            for(int j=0;j<entradas;j++)
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

    /**
     * este metodo pide al usuario las columnas de entradas y las de salida
     * se asume que las entradas comienzan desde la primera columna seguidas por las salidas.**
     */
    public void ValoresDeEntrada()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("ingresar entradas");
        this.entradas=sc.nextInt();
        System.out.println("ingresar salidas");
        this.salidas=sc.nextInt();
        System.out.println("ingresar filas en el archivo");
        this.filas=sc.nextInt();
    }

    /**
     * este metodo almacena los indices que contienen salidas incognitas**
     * @param Matriz matriz de string la cual se va a revisar si tiene '?' en sus columnas de salida
     * @param entradas numero de columnas de entrada de la matriz
     * @return una lista que contiene los indices de las filas que tienen '?'
     */
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


    /**
     * lee el csv y lo parsea a un arreglo 2D**
     * @param ruta ruta del archivo csv que se va a parsear
     * @param entradas columnas de entradas que tiene el archivo
     * @param salidas columnas de salidas que tiene el archivo
     * @return una matriz de string que va a tener el contenido del archivo csv
     * @throws IOException
     */
    public String[][] ParsearMatriz(String ruta,int entradas, int salidas) throws IOException {
    String[][] data = new String[this.filas][entradas+salidas];
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

   this.parsedCSV=data;
   return data;
    }
    //metodo para parsear las respuestas de la encuesta de g forms
    public double[][] ParsearEncuesta(String ruta,int filas,int columnas) throws IOException {
        String[][] data = new String[filas][columnas];
        //-1 por que la primera columna tiene las fechas
        double[][] respuestas = new double[filas][columnas-1];
        File file = new File(ruta);

        int row = 0;
        int col = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        //String e=reader.readLine();

        while ((line = reader.readLine()) != null && row < data.length) {
            StringTokenizer st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens()&&col<13) {
                //fucking quotes .I.
                data[row][col] = (st.nextToken().toString().replace("\"",""));
                System.out.println(data[row][col]);

                col++;
            }
            col = 0;
            row++;
        }

        for(int i=1;i<filas;i++)
        {
            for(int j=1;j<columnas;j++)
            {
                if(j==1)
                {
                    System.out.println("Rock");
                    System.out.println(data[i][j]);
                    System.out.println(data[i][j].toString().equals("Rock"));
                    if(data[i][j].equals("Rock"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==2)
                {
                    if(data[i][j].equals("Video #1"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==3)
                {
                    if(data[i][j].equals("Video #1"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==4)
                {
                    if(data[i][j].equals("Michael Jackson"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==5)
                {
                    if(data[i][j].equals("Video #1"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==5)
                {
                    if(data[i][j].equals("Video #1"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==6)
                {
                    if(data[i][j].equals("Retro"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i][j-1]=1;
                    }
                }
                if(j==7)
                {
                    if(data[i][j].equals("Corta duración"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==8)
                {
                    if(data[i][j].equals("Bailar"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==9)
                {
                    if(data[i][j].equals("Video #1"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==10)
                {
                    if(data[i][j].equals("Escuchar música y cantar"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==11)
                {
                    if(data[i][j].equals("Escuchar un sólo género musical"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==12)
                {
                    if(data[i][j].equals("Rock/Metal"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else
                    {
                        respuestas[i-1][j-1]=1;
                    }
                }
                if(j==13)
                {
                    if(data[i][j].equals("Acción"))
                    {
                        respuestas[i-1][j-1]=0;
                    }
                    else if(data[i][j].equals("Disparos"))
                    {
                        respuestas[i-1][j-1]=1;
                    }
                    else if(data[i-1][j].equals("Estrategia"))
                    {
                        respuestas[i][j-1]=2;
                    }
                    else if(data[i-1][j].equals("Simulacion"))
                    {
                        respuestas[i-1][j-1]=3;
                    }
                    else if(data[i-1][j].equals("Deportes"))
                    {
                        respuestas[i-1][j-1]=4;
                    }
                    else if(data[i][j].equals("Carreras"))
                    {
                        respuestas[i-1][j-1]=5;
                    }
                    else if(data[i][j].equals("Aventuras"))
                    {
                        respuestas[i-1][j-1]=6;
                    }
                    else if(data[i][j].equals("Rol"))
                    {
                        respuestas[i-1][j-1]=7;
                    }
                    else if(data[i][j].equals("Musical"))
                    {
                        respuestas[i-1][j-1]=8;
                    }
                    else if(data[i-1][j].equals("Arcade"))
                    {
                        respuestas[i-1][j-1]=9;
                    }
                }
            }
        }
        return respuestas;
    }

    /**
     * metodo para escribir una matriz en un archivo de texto separando las columnas con tabuladores.
     * @param matriz La matriz que se va a escribir en el archivo
     * @param nombre nombre del archivo que se va a escribir
     */
    public void escribirArchivo(double[][] matriz,String nombre)
    {
        //ruta del archivo que se va a escribir.
        String ruta="C:\\Users\\taka\\Documents\\9 Semestre\\Computación inteligente\\Files\\"+nombre+".txt";

        BufferedWriter bw = null;
        FileWriter fw = null;
        StringBuffer linea=new StringBuffer();

        try
        {
            fw = new FileWriter(ruta);
            bw = new BufferedWriter(fw);

            for(int i=0;i<matriz.length;i++)
            {
                for(int j=0;j<matriz[0].length;j++)
                {
                    //validando que la ultima columna no agregue un tabulador
                    if (j==matriz[0].length-1) {
                        linea.append(Double.toString(matriz[i][j]));
                    }
                    else
                    {
                        linea.append(Double.toString(matriz[i][j])+"\t");
                    }
                }
                //validando que la ultima linea no añada un salto de linea
                if(i!=matriz.length-1)
                linea.append("\n");
            }
            //escribiendo el contenido del stringBuffer al archivo
            bw.write(linea.toString());

        }
        catch (IOException e)
        {

        }
        //cerrando buffers
        finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

    /**
     * metodo para imprimir una matriz de double
     * @param M matriz que se va a imprimir
     */
    public void imprimirMatriz(double[][] M)
    {
        for(int i=0;i<M.length;i++)
        {
            for(int j=0;j<M[0].length;j++)
            {
                System.out.println(M[i][j]);
            }
            System.out.println("fila numero: "+i);
        }
    }
    public void imprimirMatriz(int[][] M)
    {
        for(int i=0;i<M.length;i++)
        {
            for(int j=0;j<M[0].length;j++)
            {
                System.out.println(M[i][j]);
            }
            System.out.println("fila numero: "+i);
        }

    }
    public double[][] escribirDeseados(double[][] m,int salidas)
    {
        double[][] matriz=new double[m.length][salidas];
        for(int i=0;i<m.length;i++)
        {
            System.out.println((int)m[i][m[0].length-1]);
           matriz[i][(int)m[i][m[0].length-1]]=1;
        }
        return matriz;

    }
    public double[][] quitarColumnas(double[][] m, List<Integer> columnas)
    {
        double[][] matriz=new double[m.length][m[0].length-columnas.size()];
        int i=0,j=0,k=0;
        while(i<m.length)
        {
            j=0;
            k=0;
            while (j < m[0].length)
            {
                if(columnas.contains(j))
                {
                    System.out.println(columnas.contains(j));
                }
                else
                {
                    System.out.println("probando: "+m[i][j]);
                    matriz[i][k]=m[i][j];
                    k++;
                }
                j++;
            }
            i++;
        }
        return matriz;
    }


}

