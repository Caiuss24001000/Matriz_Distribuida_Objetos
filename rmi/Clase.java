package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Clase extends UnicastRemoteObject implements Interface{

    public Clase() throws RemoteException{
        super();
    }

    // Ejecutara en el servidor RMI
    public float[][] multiplica_matrices(float[][] A, float[][] B, int N) {
        float[][] C = new float[N / 2][N / 2];

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
    public float[][] separa_matriz(float[][] A, int inicio, int N) {
        float[][] M = new float[N / 2][N];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] = A[i + inicio][j];
            }
        }

        return M;
    }
}
