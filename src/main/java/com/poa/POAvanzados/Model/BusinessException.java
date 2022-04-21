package com.poa.POAvanzados.Model;

import javax.swing.JOptionPane;

public class BusinessException extends Exception {

    public BusinessException(int errorCode) {

        System.out.println("Se capturo el codigo de error: " + errorCode);

        switch (errorCode) {
            case 0:
                JOptionPane.showMessageDialog(null,
                        "Informacion ingresada no valida. Verifique su User ID.");
                break;
            case 1:
                System.out.println("Nombre o Apellido no ingresados");
                JOptionPane.showMessageDialog(null,
                        "Informacion ingresada no valida. Verifique su Nombre Completo.");
                break;

            case 8:
                System.out.println("No se selecciono un item de la tabla. INDEX: -1");
                JOptionPane.showMessageDialog(null,
                        "Por favor seleccione un item antes de continuar.");
                break;

            default:
                break;
        }
    }

    public BusinessException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, "HUBO UN PROBLEMA: " + message);

    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);

    }

    public BusinessException(Throwable cause) {
        super(cause);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        JOptionPane.showMessageDialog(null, "ERROR: " + cause);
    }
}
