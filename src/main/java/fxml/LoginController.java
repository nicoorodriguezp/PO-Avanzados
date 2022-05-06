package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.DAO.DAOException;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@Component
public class LoginController extends Controller implements Initializable {

    @FXML
    private TextField idUserTF;
    @FXML
    private Button loginButton;
    @FXML
    private TextField idUserTF1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void init(MainController mainController) {
        this.m = mainController;
    }

    @FXML
    private void login(ActionEvent event) {

        try {
            this.m.user = m.wc.getUser(41476258);

            if (this.m.user.getActive()) {
                m.showHome();
            } else {
                m.showAlert("El usuario no está activo, por lo tanto, no tiene acceso al sistema.", 2);
            }

        } catch (DAOException e) {
            m.showAlert("No se ha encontrado el usuario ingresado.", 2);
            e.printStackTrace();
        }

    }

}
