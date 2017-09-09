import java.util.Scanner;

public class Aprendizaje {


    public Aprendizaje() {
        //Entrada e = new Entrada();
    }


    int L, M, N, Q = 0;
    //entrada por peso
    double[] neth = new double[L];
    double[][] x = new double[N][Q];
    double[][] wh = new double[L][N];
    double[] yh = new double[L];
    double[] neto = new double[M];
    double[][] wo = new double[M][L];

    public void setL(int l) {
        L = l;
    }

    public void setM(int m) {
        M = m;
    }

    public void setN(int n) {
        N = n;
    }

    public void setQ(int q) {
        Q = q;
    }

    public void setX(double[][] x) {
        this.x = x;
    }

    double[] y = new double[M];
    double[] deltao = new double[M];
    double[][] d = new double[M][Q];
    double[] deltah = new double[L];
    double alpha = 0.5;
    double error = 0;
//deseados
//double[][] d=new double[M][Q];


    public void ValoresIniciales() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ingresar valor de L");
        this.L = sc.nextInt();
        System.out.println("ingresar valor de Q");
        this.Q = sc.nextInt();
        System.out.println("ingresar valor de M");
        this.M = sc.nextInt();
        System.out.println("ingresar valor de P");
        this.Q = sc.nextInt();

        System.out.println("Valores ingresados: \n" + L + "\n" + Q + "\n" + M + "\n" + Q);

    }

    public void llenarAleatorio(double[][] matriz)
    {

        for(int i=0;i<matriz.length;i++)
        {
            for(int j=0;j<matriz[0].length;j++)
            {
                if(Math.random()<0.5)
                {
                    matriz[i][j]=Math.random()*-1;
                }
                else
                {
                    matriz[i][j]=Math.random();
                }
            }
        }
    }



public Aprendizaje Aprender()
{
//wh
    do {
        for (int p = 0; p < Q; p++) {
            for (int j = 0; j < L; j++) {
                double suma = 0;
                for (int i = 0; i < N; i++) {

                    suma += x[i][p] * wh[j][i];

                }
                neth[j] = suma;
                //Yh
                yh[j] = 1 / (1 + Math.exp(neth[j]));
            }


            //wo

            for (int j = 0; j < L; j++) {
                double sumao = 0;
                for (int k = 0; k < M; k++) {
                    sumao = yh[j] * wo[k][j];
                }
                neto[j] = sumao;
                //Yk
                y[j] = 1 / (1 + Math.exp(neto[j]));
            }
//deltao
            for (int k = 0; k < M; k++) {
                deltao[k] = (d[k][p] - y[k]) * y[k] * (1 - y[k]);
            }


//deltah
            for (int j = 0; j < N; j++) {
                double sumadelta = 0;
                for (int k = 0; k < M; k++) {
                    sumadelta += deltao[k] * wo[k][j];
                }
                deltah[j] = yh[j] * (1 - yh[j]) * sumadelta;
            }
//wo
            for (int k = 0; k < M; k++) {
                for (int j = 0; j < L; k++) {
                    wo[k][j] = alpha * deltao[k] * yh[j];
                }
            }
            //wh
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    wh[j][i] = alpha * deltah[j] * x[i][p];
                }
            }
            //calculando error
            double sumaerror = 0;
            for (int k = 0; k < M; k++) {
                sumaerror += Math.pow(deltao[k], 2);

            }
            error = .5 * sumaerror;





        }

    }
    while(error<.01);




    return this;
}







}
