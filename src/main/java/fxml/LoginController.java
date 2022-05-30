package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.exception.LoginUserException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    }

    public void init(MainController mainController) {
        this.m = mainController;
        dniTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    dniTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void login(ActionEvent event) {

        try {
        //Esto es temporal hasta que arregle el login
            this.m.user = m.wc.getUser(Integer.parseInt(dniTF.getText()),passwordTF.getText());
            if (this.m.user.getActive()) {
                m.showHome();
            } else {
                m.showAlert("El usuario no está activo, por lo tanto, no tiene acceso al sistema.", 2);
            }
        } catch (NumberFormatException e){
            m.showAlert("Ingrese un numero de dni valido", 2);
            e.printStackTrace();
        }
        catch (LoginUserException e){
            m.showAlert(e.getMessage(), 2);
            e.printStackTrace();
        }


    }

}
