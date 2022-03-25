package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote{
    //public void imprimir_matriz(float[][] M, String titulo) throws RemoteException;
    //public float checksum(float[][] m) throws RemoteException;
    //public float[][] transpuesta(float[][] M, int filas, int columnas) throws RemoteException;
    public float[][] multiplica_matrices(float[][] A, float[][] B, int N) throws RemoteException;
    //public float[][] separa_matriz(float[][] A, int inicio, int N) throws RemoteException;
    //public void acomoda_matriz(int[][] C, int[][] A, int renglon, int columna, int N) throws RemoteException;
}
