import java.io.*;
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) throws IOException {


        Entrada e=new Entrada();

        e.ValoresDeEntrada();
        e.ParsearMatriz("C:\\Users\\taka\\Documents\\testcsv.csv",e.getEntradas(),e.getSalidas());
        e.CalcularIncognitas(e.getParsedCSV(),e.getEntradas());
        System.out.println(e.getIndicesA());
        e.crearMincognitas(e.getIndices(),e.getEntradas(),e.getSalidas(),e.getParsedCSV());


        e.crearMAprendizaje(e.getIndicesA(),e.getEntradas(),e.getSalidas(),e.getParsedCSV());



        Aprendizaje a=new Aprendizaje();
        a.ValoresIniciales();

        a.InicializarValores();
        System.out.println("this L: "+a.yh.length);
        a.llenarAleatorio(a.wh);
        //System.out.println(a.wh.length);
        //System.out.println(a.L);
       // System.out.println(a.wh[0].length);
        a.llenarAleatorio(a.wo);
        e.imprimirMatriz(a.wo);






    }
}
