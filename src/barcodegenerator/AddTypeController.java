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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahr97
 */
public class AddTypeController implements Initializable {

    @FXML
    private Label titlelbl;
    @FXML
    private TextField nametxt;
    @FXML
    private TextField desctxt;
    @FXML
    private ChoiceBox<String> usagecb;
    @FXML
    private Label lblout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usagecb.getItems().addAll("Select Usage of Barcode", "Supermarkets", "All types of products", "Links", "Pricing", "Labeling");
        usagecb.getSelectionModel().select("Select Usage of Barcode");
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
        Parent root = FXMLLoader.load(getClass().getResource("Types.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
        lblout.setText("");
        if (nametxt.getText().isEmpty() || desctxt.getText().isEmpty() || usagecb.getValue().equals("Select Usage of Barcode")) {
            lblout.setText("one or more fields are missing, please fill in all fields");
        } else {
            Database db = new Database();
            if(!db.lookTypes(nametxt.getText())) {
                db.addType(nametxt.getText(), desctxt.getText(), usagecb.getValue());
                lblout.setText("Type Successfully Added");
            } else {
                lblout.setText("Type is Already Added Before");
            }
        }
    }
}
