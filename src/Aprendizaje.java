import java.util.Scanner;

public class Aprendizaje {


    public Aprendizaje() {
        //Entrada e = new Entrada();

    }


    int L, M, N, Q ;

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

    public void setD(double[][] d) {
        this.d = d;
    }

    double[] y = new double[M];
    double[] deltao = new double[M];
    double[][] d = new double[M][Q];
    double[] deltah = new double[L];
    double alpha = 0.5;
    double error = 0;
//deseados
//double[][] d=new double[M][Q];


    public double getError() {
        return error;
    }

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
        this.neth = new double[this.L];
        this.x = new double[this.N][this.Q];
        this.yh = new double[this.L];
        this.neto = new double[this.M];
        this.y = new double[this.M];
        this.deltao = new double[this.M];
        this.d = new double[this.M][this.Q];
        this.deltah = new double[this.L];
        this.alpha = 0.5;
        this.error = 0;
        this.wh=llenarAleatorioWh(this.wh);
        this.wo=llenarAleatorioWh(this.wo);


    }
//metodo para llenar una matriz con numeros aleatorios entre -1 y 1
   /* public void llenarAleatorioWo(double[][] matriz)
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
        this.wo=matriz;
    }*/

    public double[][] llenarAleatorioWh(double[][] matriz)
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
        return matriz;
    }



public Aprendizaje Aprender()
{
//wh
    do {
        for (int p = 0; p < Q; p++) {
            for (int j = 0; j < L; j++) {
                double suma = 0;
                for (int i = 0; i < N; i++) {

                    suma += x[p][i] * wh[j][i];

                }
                neth[j] = suma;
                //Yh
                yh[j] = 1 / (1 + Math.exp(-neth[j]));
            }


            //yo
            //deltao

            for (int k = 0; k < M; k++) {
                double sumao = 0;
                for (int j = 0; j < L; j++) {
                    sumao = yh[j] * wo[k][j];

                    neto[k] = sumao;
                    sumao = 0;
                    //Yk
                    y[k] = 1 / (1 + Math.exp(-neto[k]));
                    deltao[k] = (d[p][k] - y[k]) * y[k] * (1 - y[k]);
                }
            }
//deltao
            /*for (int k = 0; k < M; k++) {
                deltao[k] = (d[p][k] - y[k]) * y[k] * (1 - y[k]);
            }*/


//deltah
            for (int j = 0; j < L; j++) {
                double sumadelta = 0;
                for (int k = 0; k < M; k++) {
                    sumadelta += deltao[k] * wo[k][j];
                }

                    deltah[j] = yh[j] * (1 - yh[j]) * sumadelta;

            }
//wo
            for (int k = 0; k < M; k++) {
                for (int j = 0; j < L; j++) {
                    wo[k][j] += alpha * deltao[k] * yh[j];
                }
            }
            //wh
            for (int j = 0; j < L; j++) {
                for (int i = 0; i < N; i++) {
                    wh[j][i] += alpha * deltah[j] * x[p][i];
                }
            }
            //calculando error
            double sumaerror=0;
            for (int k = 0; k < M; k++) {
                sumaerror += Math.pow(deltao[k], 2);

            }
            this.error = sumaerror/2;





        }

    }
    while(this.getError()>0.001);




    return this;
}







}
