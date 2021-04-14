/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EditUserController implements Initializable {

    @FXML
    private Label titlelbl;
    @FXML
    private Label lblout;
    @FXML
    private ChoiceBox<String> listcb;
    @FXML
    private TextField emailtxt;
    @FXML
    private TextField lnametxt;
    @FXML
    private TextField fnametxt;
    @FXML
    private ChoiceBox<String> citycb;
    @FXML
    private PasswordField passtxt;
    ArrayList<String> al = new ArrayList();
    String[] strArray;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Database db = new Database();
        try {
            db.getUserName(al);
        } catch (SQLException ex) {
            Logger.getLogger(EditTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        strArray = al.toArray(new String[al.size()]);
        listcb.getItems().add("Select User to Edit info");
        for (int i = 0; i < strArray.length; i++) {
            listcb.getItems().add(strArray[i]);
        }
        listcb.getSelectionModel().select("Select User to Edit info");
        citycb.getItems().addAll("Select User's City","Makkah","Jeddah","AlDammam","AlBaha","AlJubail","Ynbu","Tabuk");
        citycb.getSelectionModel().select("Select User's City");
    }    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Users.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void edit(ActionEvent event) throws SQLException {
        lblout.setText("");
        if (emailtxt.getText().isEmpty() || passtxt.getText().isEmpty() || fnametxt.getText().isEmpty() || lnametxt.getText().isEmpty() || citycb.getValue().equals("Select User's City")) {
            lblout.setText("one or more fields are missing, please fill in all fields");
        } else {
            Database db = new Database();
            db.editUser(emailtxt.getText(), passtxt.getText(), citycb.getValue(), fnametxt.getText(), lnametxt.getText(), listcb.getValue());
            lblout.setText("User Info Edited Successfully");
        }
    }   
}
