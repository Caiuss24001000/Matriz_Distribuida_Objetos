package rmi;

import java.rmi.Naming;

public class Servidor {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/matriz";
        Clase obj = new Clase();

        // Registra la instancia en el rmiregistry
        Naming.rebind(url, obj);
    }
}
