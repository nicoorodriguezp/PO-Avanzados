/*
 * Trabajo Practico Integrador POA
 * Trabajo desarrollado por Nicolas Gaston Rodriguez Perez y Matias Ezequiel Romero para la Universidad de Palermo.  * 
 */
package main;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import Model.UserModel.User;
import View.User.LoginPanel;

public final class MainHandler extends JFrame {

    // Declaring Frames
    private LoginPanel login;

    // Authenticated User
    public User user;

    public MainHandler() {
        // Init Frame
        setTitle("Sistema de Control de Insumos");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        getContentPane().setVisible(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        showLoginPanel();

    }

    public void showLoginPanel() {

        login = new LoginPanel(this);
        getContentPane().removeAll();
        getContentPane().add(login, BorderLayout.CENTER);

        getContentPane().revalidate();
        getContentPane().repaint();
        pack();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
