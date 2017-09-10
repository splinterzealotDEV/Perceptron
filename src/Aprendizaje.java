import java.util.Scanner;

public class Aprendizaje {


    public Aprendizaje() {
        //Entrada e = new Entrada();
    }


    int L, M, N, Q ;
    //entrada por peso
    double[] neth = new double[L];
    double[][] x = new double[N][Q];
    double[][] wh ;
    double[] yh = new double[L];
    double[] neto = new double[M];
    double[][] wo ;

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
        System.out.println("ingresar valor de N");
        this.N = sc.nextInt();

        System.out.println("Valores ingresados: \n" + L + "\n" + Q + "\n" + M + "\n" + Q);

    }
    //Inicialzando valores
    public void InicializarValores()
    {

        this.wh=new double[this.L][this.N];
        this.wo=new double[this.M][this.L];
        this.neth = new double[L];
        this.x = new double[N][Q];
        this.yh = new double[L];
        this.neto = new double[M];
        this.y = new double[M];
        this.deltao = new double[M];
        this.d = new double[M][Q];
        this.deltah = new double[L];
        this.alpha = 0.5;
        this.error = 0;


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
                    System.out.println(matriz[i][j]);

                }
                else
                {
                    matriz[i][j]=Math.random();
                    System.out.println(matriz[i][j]);
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
