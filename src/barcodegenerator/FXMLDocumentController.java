/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahr97
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label titlelbl;
    @FXML
    private Label outlbl;

    @FXML
    private PasswordField passtxt;
    @FXML
    private TextField emailtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        outlbl.setText("");
        if(emailtxt.getText().equalsIgnoreCase("admin") && passtxt.getText().equalsIgnoreCase("admin")){
            ((Node)event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("AdminHomeP.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(emailtxt.getText().isEmpty() && passtxt.getText().isEmpty()) {
            outlbl.setText("Please enter your email and password");
        } else if(passtxt.getText().isEmpty()) {
            outlbl.setText("Please enter your password");
        } else if(emailtxt.getText().isEmpty()) {
            outlbl.setText("Please enter your email");
        } else {
            Database db = new Database();
            boolean check = db.login(emailtxt.getText(), passtxt.getText());
            if(check){
            ((Node)event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("UserHomeP.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } else {
                outlbl.setText("Username or password are incorrect");
            }
        }
    }
    
}
