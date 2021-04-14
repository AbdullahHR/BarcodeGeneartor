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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahr97
 */
public class EditTypeController implements Initializable {

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
    @FXML
    private ChoiceBox<String> listcb;
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
            db.getTypeName(al);
        } catch (SQLException ex) {
            Logger.getLogger(EditTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        strArray = al.toArray(new String[al.size()]);
        listcb.getItems().add("Select Type to Edit");
        for (int i = 0; i < strArray.length; i++) {
            listcb.getItems().add(strArray[i]);
        }
        listcb.getSelectionModel().select("Select Type to Edit");
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
    private void edit(ActionEvent event) throws SQLException {
        Database db = new Database();
        if (nametxt.getText().isEmpty() || desctxt.getText().isEmpty() || usagecb.getValue().equals("Select Usage of Barcode") || listcb.getValue().equals("Select Type to Edit")) {
            lblout.setText("one or more fields are missing, please fill in all fields");
        } else {
            db.editType(nametxt.getText(), desctxt.getText(), usagecb.getValue().toString(), listcb.getValue().toString());
            lblout.setText("Type Edited Successfully");
        }
    }       
}
