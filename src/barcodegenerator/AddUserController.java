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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahr97
 */
public class AddUserController implements Initializable {

    @FXML
    private Label titlelbl;
    @FXML
    private TextField emailtxt;
    @FXML
    private Label lblout;
    @FXML
    private TextField lnametxt;
    @FXML
    private TextField fnametxt;
    @FXML
    private ChoiceBox<String> citycb;
    @FXML
    private PasswordField passtxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        citycb.getItems().addAll("Select User's City","Makkah","Jeddah","AlDammam","AlBaha","AlJubail","Ynbu","Tabuk");
        citycb.getSelectionModel().select("Select User's City");
        
    }    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Users.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
        lblout.setText("");
        if (emailtxt.getText().isEmpty() || passtxt.getText().isEmpty() || fnametxt.getText().isEmpty() || lnametxt.getText().isEmpty() || citycb.getValue().equals("Select User's City")) {
            lblout.setText("one or more fields are missing, please fill in all fields");
        } else {
            Database db = new Database();
            if(!db.lookUser(emailtxt.getText())) {
                db.addUser(emailtxt.getText(), passtxt.getText(), citycb.getValue(),fnametxt.getText(),lnametxt.getText());
                lblout.setText("User Successfully Added");
            } else {
                lblout.setText("Email is Used before");
            }
        }
    }
    
}
