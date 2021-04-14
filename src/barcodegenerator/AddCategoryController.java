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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahr97
 */
public class AddCategoryController implements Initializable {

    @FXML
    private Label titlelbl;
    @FXML
    private TextField nametxt;
    @FXML
    private TextField desctxt;
    @FXML
    private Label lblout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        Parent root = FXMLLoader.load(getClass().getResource("Categories.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
        lblout.setText("");
        if (nametxt.getText().isEmpty() || desctxt.getText().isEmpty()) {
            lblout.setText("one or more fields are missing, please fill in all fields");
        } else {
            Database db = new Database();
            if(!db.lookCategory(nametxt.getText())) {
                db.addCategory(nametxt.getText(), desctxt.getText());
                lblout.setText("Category Successfully Added");
            } else {
                lblout.setText("Category is Already Added Before");
            }
        }
    }
    
}
