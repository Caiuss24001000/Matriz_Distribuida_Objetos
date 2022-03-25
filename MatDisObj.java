public class MatDisObj {
    static int N = 8;

    public static void main(String[] args) {

        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        int[][] C = new int[N][N];

        // INICIALIZA LAS MATRICES A Y B
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = i + 2 * j;
                B[i][j] = 3 * i - j;
            }
        }

        imprimir_matriz(A, "A");
        imprimir_matriz(B, "B");

        B = transpuesta(B, N, N);

        int[][] A1 = separa_matriz(A, 0, N);
        int[][] A2 = separa_matriz(A, N / 2, N);
        int[][] B1 = separa_matriz(B, 0, N);
        int[][] B2 = separa_matriz(B, N / 2, N);

        int[][] C1 = multiplica_matrices(A1, B1, N);
        int[][] C2 = multiplica_matrices(A1, B2, N);
        int[][] C3 = multiplica_matrices(A2, B1, N);
        int[][] C4 = multiplica_matrices(A2, B2, N);

        acomoda_matriz(C, C1, 0, 0, N);
        acomoda_matriz(C, C2, 0, N / 2, N);
        acomoda_matriz(C, C3, N / 2, 0, N);
        acomoda_matriz(C, C4, N / 2, N / 2, N);

        //imprimir_matriz(B, "B transpuesta");
        //imprimir_matriz(C, "C");

        System.out.println("El checksum es -> " + checksum(C));
    }

    static void imprimir_matriz(int[][] M, String titulo) {
        System.out.println("Imprimiendo la matriz ... " + titulo + "\n");
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print("\t" + M[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    // Calcula el checksum
    static int checksum(int[][] m) {
        int s = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                s += m[i][j];
            }
        }
        return s;
    }

    // TRANSPONE UNA MATRIZ
    static int[][] transpuesta(int[][] M, int filas, int columnas) {
        int[][] T = new int[filas][columnas];
        int x;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                x = M[i][j];
                T[i][j] = M[j][i];
                T[j][i] = x;
            }
        }
        return T;
    }

    // Ejecutara en el servidor RMI
    static int[][] multiplica_matrices(int[][] A, int[][] B, int N) {
        int[][] C = new int[N / 2][N / 2];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[j][k];
                }
            }
        }
        return C;
    }

    // Separa las matrices en A1,A2,B1,B2 según se requiera
    static int[][] separa_matriz(int[][] A, int inicio, int N) {
        int[][] M = new int[N / 2][N];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] = A[i + inicio][j];
            }
        }

        return M;
    }

    static void acomoda_matriz(int[][] C, int[][] A, int renglon, int columna, int N) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                C[i + renglon][j + columna] = A[i][j];
            }
        }
    }
}
