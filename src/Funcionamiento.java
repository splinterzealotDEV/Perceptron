public class Funcionamiento {


    public Funcionamiento()
    {
        //Entrada e=new Entrada();
    }
    int L,M,N,R;
    double[] neth=new double[L];
    double[][] x2 =new double[N][R];
    double[][] wh =new double[L][N];
    double[] yh=new double[L];
    double[] neto=new double[M];
    double[][] wo=new double[M][L];
    double[][] y2=new double[M][R];


    public double[][] Calcular(Aprendizaje A)
    {



        for(int p=0;p<R;p++)
        {
            for(int j=0;j<L;j++)
            {
                double suma=0;
                for(int i=0;i<N;i++)
                {
                    suma+=x2[i][p]*wh[j][i];
                }
                neth[j]=suma;
                yh[j]=1/(1+Math.exp(neth[j]));
            }
            for(int j=0;j<L;j++)
            {
                double sumao=0;
                for (int k=0;k<M;k++)
                {
                    sumao+=yh[j]*wo[k][j];

                }
                neto[j]=sumao;

            }
            for(int k=0;k<M;k++)
            {
                y2[k][p]=1/(1+Math.exp(neth[k]));
            }
        }




        return y2;
    }





}
