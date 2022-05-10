package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Exception.DAOException;

import com.poa.POAvanzados.Exception.LoginUserException;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.security.auth.login.LoginException;

@Component
public class LoginController extends Controller implements Initializable {

    @FXML
    private TextField dniTF;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordTF;

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
            //TODO arreglar lo de que no se puede sacar el value de los textfields y arreglar el login del dao que se rompe
        //Esto es temporal hasta que arregle el login
            this.m.user = m.wc.getUser(Integer.parseInt(dniTF.getText()),passwordTF.getText());
            if (this.m.user.getActive()) {
                m.showHome();
            } else {
                m.showAlert("El usuario no está activo, por lo tanto, no tiene acceso al sistema.", 2);
            }

            //TODO cambiar el daoexception por otra excepcion mas especifica
       } catch (DAOException e) {
            m.showAlert("No se ha encontrado el usuario ingresado.", 2);
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            m.showAlert("Ingrese un numero de dni valido", 2);
            e.printStackTrace();
        }
        catch (LoginUserException e){
            m.showAlert(e.getMessage(), 2);
            e.printStackTrace();
        }


    }

}
