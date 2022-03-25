package rmi;

import java.rmi.Naming;

public class Cliente {
    static float[][] C;
    static int N;

    public static void main(String[] args) throws Exception {
        // En este caos el objeto remoto se llama "prueba", notar que se utiliza el
        // puerto default 1099
        String url = "rmi://localhost/matriz";

        // Obtiene una referencia que apunta a "matriz" al objeto remoto asociado a la
        // url
        Interface r = (Interface) Naming.lookup(url);

        N = 4000;

        float[][] A = new float[N][N];
        float[][] B = new float[N][N];
        C = new float[N][N];

        // INICIALIZA LAS MATRICES A Y B
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = i + 2 * j;
                B[i][j] = 3 * i - j;
            }
        }

        // TRANSPUESTA DE B
        B = transpuesta(B, N, N);

        // SEPARA LAS MATRICES
        float[][] A1 = separa_matriz(A, 0, N);
        float[][] A2 = separa_matriz(A, N / 2, N);
        float[][] B1 = separa_matriz(B, 0, N);
        float[][] B2 = separa_matriz(B, N / 2, N);
        // r.imprimir_matriz(B1, "B1");

        // INVOCA EL MÉTODO REMOTO multiplica_matrices()
        float[][] C1 = r.multiplica_matrices(A1, B1, N);
        float[][] C2 = r.multiplica_matrices(A1, B2, N);
        float[][] C3 = r.multiplica_matrices(A2, B1, N);
        float[][] C4 = r.multiplica_matrices(A2, B2, N);
        // r.imprimir_matriz(C4, "C4");

        // OBTENER LA MATRIX C A PARTIR DE LAS MATRICES C1..Cn
        /*
         * for (int i = 0; i < N / 2; i++) {
         * for (int j = 0; j < N / 2; j++) {
         * C[i][j] = C1[i][j];
         * C[i][j+N/2] = C2[i][j];
         * C[i+N/2][j] = C3[i][j];
         * C[i+N/2][j+N/2] = C4[i][j];
         * }
         * }
         */

        acomoda_matriz(C, C1, 0, 0, N);
        acomoda_matriz(C, C2, 0, N / 2, N);
        acomoda_matriz(C, C3, N / 2, 0, N);
        acomoda_matriz(C, C4, N / 2, N / 2, N);

        if (N == 8) {

            B = transpuesta(B, N, N);
            // imprimir_matriz(A, "A");
            //imprimir_matriz(B, "B");
            // imprimir_matriz(C, "C");
        }

        // CALCULA EL CHECKSUM
        float checksum = checksum(C);
        System.out.println("El checksum es -> " + checksum);
    }

    // Calcula el checksum
    static float checksum(float[][] m) {
        int s = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                s += m[i][j];
            }
        }
        return s;
    }

    static void acomoda_matriz(float[][] c2, float[][] c1, int renglon, int columna, int N) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                c2[i + renglon][j + columna] = c1[i][j];
            }
        }
    }

    // Separa las matrices en A1,A2,B1,B2,... según se requiera
    public static float[][] separa_matriz(float[][] A, int inicio, int N) {
        float[][] M = new float[N / 2][N];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] = A[i + inicio][j];
            }
        }

        return M;
    }

    // TRANSPONE UNA MATRIZ
    static float[][] transpuesta(float[][] M, int filas, int columnas) {
        float[][] T = new float[filas][columnas];
        float x;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                x = M[i][j];
                T[i][j] = M[j][i];
                T[j][i] = x;
            }
        }
        return T;
    }

    static void imprimir_matriz(float[][] M, String titulo) {
        System.out.println("Imprimiendo la matriz ... " + titulo + "\n");
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print("\t" + M[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
}
