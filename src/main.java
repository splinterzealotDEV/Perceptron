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
        /*for(int i=0;i<e.incognitas.length;i++)
        {
            for(int j=0;j<e.incognitas[0].length;j++)
            {
                System.out.println(e.incognitas[i][j]);
            }
            System.out.println("row:"+i);
        }
        */

        e.crearMAprendizaje(e.getIndicesA(),e.getEntradas(),e.getSalidas(),e.getParsedCSV());
        /*for(int i=0;i<e.parsedCSV.length;i++)
        {
            for(int j=0;j<e.parsedCSV[0].length;j++)
            {
                System.out.println(e.parsedCSV[i][j]);
            }
            System.out.println("row:"+i);
        }*/

        for(int i=0;i<e.getAprendizaje().length;i++)
        {
            for(int j=0;j<e.getAprendizaje()[0].length;j++)
            {
                System.out.println(e.getAprendizaje()[i][j]);
            }
            System.out.println("row:"+i);
        }





    }
}
