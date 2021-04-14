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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahr97
 */
public class DeleteUserController implements Initializable {

    @FXML
    private Label titlelbl;
    @FXML
    private ChoiceBox<String> listcb;
    @FXML
    private Label lblout;
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
        listcb.getItems().add("Select User to Delete");
        for (int i = 0; i < strArray.length; i++) {
            listcb.getItems().add(strArray[i]);
        }
        listcb.getSelectionModel().select("Select User to Delete");
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
    private void delete(ActionEvent event) throws SQLException {
        lblout.setText("");
        if (listcb.getValue().equals("Select User to Delete")) {
            lblout.setText("You need to select User to Proceed");
        } else {
            Database db = new Database();
            db.deleteUser(listcb.getValue());
            lblout.setText("User Deleted Successfully");
        }
    }
    
}
