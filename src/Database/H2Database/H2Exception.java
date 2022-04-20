package Database.H2Database;

import javax.swing.JOptionPane;

public class H2Exception extends Exception {

    public H2Exception(int errorCode) {

        System.out.println("Se capturo el codigo de error: " + errorCode);

        switch (errorCode) {
            case 23505:
                JOptionPane.showMessageDialog(null, "YA EXISTE UN ELEMENTO CON ESE ID");
                break;
            case 2000:
                System.out.println("La tabla esta vacia");
                JOptionPane.showMessageDialog(null, "NO HAY INFORMACION");
                break;
            case 42102:
                System.out.println("No existe la tabla");
                JOptionPane.showMessageDialog(null, "NO EXISTE LA TABLA");
                break;
            case 22018:
                System.out.println("Hubo un error al convertir los datos y enviarlos a la BD.");
                JOptionPane.showMessageDialog(null, "CONVERSION DE DATOS FALLIDA");
                break;
            case 42122:
                System.out.println("Una de las columnas no fue encontrada.");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO UNA COLUMNA");
                break;
            case 42001:
                System.out.println("Error de Sintaxis SQL.");
                JOptionPane.showMessageDialog(null, "SINTAXIS SQL MAL ESCRITA");
                break;
            case 22001:
                System.out.println("Valor ingresado es mas largo de lo que la columna soporta");
                JOptionPane.showMessageDialog(null, "INGRESE DATOS VALIDOS");
                break;
            case 50200:
                System.out.println("Tiempo vencido intentando trabar (lock) la tabla");
                /*
                 * Multi-Version Concurrency Control (MVCC)
                 * With default MVStore engine delete, insert and update operations only issue a
                 * shared lock on the table. An exclusive lock is still used when adding or
                 * removing columns or when dropping the table. Connections only 'see' committed
                 * data, and own changes. That means, if connection A updates a row but doesn't
                 * commit this change yet, connection B will see the old value. Only when the
                 * change is committed, the new value is visible by other connections
                 * (read committed).
                 * If multiple connections concurrently try to lock or update the same row,
                 * the database waits until it can apply the change, but at most until the lock
                 * timeout expires.
                 * 
                 * Lock Timeout
                 * If a connection cannot get a lock on an object, the connection waits for
                 * some amount of time (the lock timeout). During this time, hopefully the
                 * connection holding the lock commits and it is then possible to get the lock.
                 * If this is not possible because the other connection does not release the
                 * lock for some time, the unsuccessful connection will get a lock timeout
                 * exception. The lock timeout can be set individually for each connection.
                 */
                JOptionPane.showMessageDialog(null, "Tiempo vencido intentando trabar (lock) la tabla");
                break;
            default:
                break;
        }
    }

    public H2Exception(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, "HUBO UN PROBLEMA: " + message);

    }

    public H2Exception(String message, Throwable cause) {
        super(message, cause);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);

    }

    public H2Exception(Throwable cause) {
        super(cause);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);
    }

    public H2Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);
    }
}
