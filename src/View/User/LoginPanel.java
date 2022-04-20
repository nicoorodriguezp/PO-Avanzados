package View.User;

import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Database.H2Database.UserDAOH2;
import Model.BusinessException;
import Model.DAO.DAOException;
import Model.DAO.UserDAO;
import View.CustomPanel;
import main.MainHandler;

public final class LoginPanel extends CustomPanel {

    private final MainHandler mainHandler;

    // Fields
    private JTextField tfDNI = new JTextField("", 20);
    private JTextField tfPassword = new JPasswordField("", 20);

    public LoginPanel(MainHandler mainHandler) {
        this.mainHandler = mainHandler;
        initLoginPanel();
    }

    public void initLoginPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // labels
        JLabel lblDNI = new JLabel("DNI:");
        JLabel passwordLbl = new JLabel("Password:");

        // Buttons
        JButton buttonEntrar = new JButton("INGRESAR");
        buttonEntrar.addActionListener((ActionEvent e) -> {
            System.out.println("COMPROBANDO DATOS...");

            try {
                acceder(tfDNI.getText().trim(), tfPassword.getText().trim());
            } catch (BusinessException | DAOException ex) {
            }

        });

        // meto todos los componentes dentro de un JPanel para organizarlos.
        this.add(wrapComponent(lblDNI));
        this.add(wrapComponent(tfDNI));
        this.add(wrapComponent(passwordLbl));
        this.add(wrapComponent(tfPassword));
        this.add(wrapComponent(buttonEntrar));

    }

    private void acceder(String dni, String pass) throws BusinessException, DAOException {

        if (verificarCampos()) {

            UserDAO userDAO;
            userDAO = new UserDAOH2();

            try {
                mainHandler.user = userDAO.getUser(Integer.parseInt(dni));
            } catch (NumberFormatException | DAOException numberFormatException) {
                throw new BusinessException(0);
            }

            /*
             * 
             * Aca chequear mainHandler.user.getPosition
             */

        }

    }

    private boolean verificarCampos() throws BusinessException {
        boolean v;

        if ("".equals(tfDNI.getText())) {
            Integer.parseInt(tfDNI.getText());
            v = false;
            throw new BusinessException(0);
        } else {
            v = true;
        }
        return v;
    }

}