package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import com.poa.POAvanzados.Model.UserModel.User;

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

        this.m.user = new User(
                41476258,
                0,
                0,
                "Nicolas",
                "Rodriguez",
                "nicogrodriguezp@gmail.com");

        m.showHome();

    }

}
