import java.io.*;
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) throws IOException {


        Entrada e=new Entrada();
        int[][] matriz=new int[3][3];
        for(int i=0;i<matriz.length;i++)
        {
            for(int j=0;j<matriz[0].length;j++)
            {
                if(Math.random()<0.5)
                {
                    matriz[i][j]= -1;
                    //System.out.println(matriz[i][j]);

                }
                else
                {
                    matriz[i][j]=1;
                    //System.out.println(matriz[i][j]);
                }
            }
        }

        e.escribirArchivo(matriz,"test");

        /*e.ValoresDeEntrada();
        e.ParsearMatriz("C:\\Users\\taka\\Documents\\testcsv.csv",e.getEntradas(),e.getSalidas());
        e.CalcularIncognitas(e.getParsedCSV(),e.getEntradas());
        System.out.println(e.getIndicesA());
        e.crearMincognitas(e.getIndices(),e.getEntradas(),e.getSalidas(),e.getParsedCSV());


        e.crearMAprendizaje(e.getIndicesA(),e.getEntradas(),e.getSalidas(),e.getParsedCSV());


        //
        Aprendizaje a=new Aprendizaje();
        //seteando valores iniciales
        a.ValoresIniciales();
        //inicializando variables
        a.InicializarValores();
        //System.out.println("this L: "+a.yh.length);
        //llenando matrices de los pesos
        //a.llenarAleatorioWh(a.wh);
        //System.out.println(a.wh.length);
        //System.out.println(a.L);
       // System.out.println(a.wh[0].length);
        //a.llenarAleatorioWo(a.wo);
        System.out.println("pesos");
        e.imprimirMatriz(a.wo);
        //e.imprimirMatriz(a);
        a.setX(e.getAprendizaje());
        e.crearMDeseados();
        a.setD(e.getDeseados());
        e.imprimirMatriz(e.getDeseados());

        a.Aprender();

        System.out.println(a.error);
        //Funcionamiento f=new Funcionamiento();
        //set pesos
        //f.setWh(a.wh);
        //f.setWo(a.wo);
        //iniciar funcionamiento

        //f.Calcular();
        */






    }
}
