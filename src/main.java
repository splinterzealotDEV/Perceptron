import java.io.*;
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) throws IOException {


        Entrada e=new Entrada();

        e.ValoresDeEntrada();
        e.ParsearMatriz("C:\\Users\\taka\\Documents\\testcsv.csv",e.entradas,e.salidas);
        e.CalcularIncognitas(e.parsedCSV,e.entradas);
        System.out.println(e.indicesA);
        e.crearMincognitas(e.indices,e.entradas,e.salidas,e.parsedCSV);
        /*for(int i=0;i<e.incognitas.length;i++)
        {
            for(int j=0;j<e.incognitas[0].length;j++)
            {
                System.out.println(e.incognitas[i][j]);
            }
            System.out.println("row:"+i);
        }
        */

        e.crearMAprendizaje(e.indicesA,e.entradas,e.salidas,e.parsedCSV);
        /*for(int i=0;i<e.parsedCSV.length;i++)
        {
            for(int j=0;j<e.parsedCSV[0].length;j++)
            {
                System.out.println(e.parsedCSV[i][j]);
            }
            System.out.println("row:"+i);
        }*/

        for(int i=0;i<e.aprendizaje.length;i++)
        {
            for(int j=0;j<e.aprendizaje[0].length;j++)
            {
                System.out.println(e.aprendizaje[i][j]);
            }
            System.out.println("row:"+i);
        }





    }
}
