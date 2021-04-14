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
import java.util.Date;
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
public class GenerateAController implements Initializable {

    @FXML
    private Label titlelbl;
    @FXML
    private ChoiceBox<String> categorycb;
    @FXML
    private ChoiceBox<String> typecb;
    @FXML
    private TextField useridtxt;
    @FXML
    private TextField desctxt;
    @FXML
    private ChoiceBox<Integer> countcb;
    @FXML
    private Label lblout;
    ArrayList<String> al = new ArrayList();
    ArrayList<String> al1 = new ArrayList();
    String[] strArray;
    String[] strArray1;
    long millis=System.currentTimeMillis();  
    Date date=new java.sql.Date(millis);  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Database db = new Database();
        try {
            db.getTypeName(al);
            db.getCategory(al1);
        } catch (SQLException ex) {
            Logger.getLogger(EditTypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        strArray = al.toArray(new String[al.size()]);
        typecb.getItems().add("Select Barcode Type");
        for (int i = 0; i < strArray.length; i++) {
            typecb.getItems().add(strArray[i]);
        }
        strArray1 = al1.toArray(new String[al1.size()]);
        categorycb.getItems().add("Select Barcode Category");
        for (int i = 0; i < strArray1.length; i++) {
            categorycb.getItems().add(strArray1[i]);
        }
        for (int i = 0; i < 10; i++) {
            countcb.getItems().add(i);
        }
        countcb.getSelectionModel().select(0);
        typecb.getSelectionModel().select("Select Barcode Type");
        categorycb.getSelectionModel().select("Select Barcode Category");
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
        Parent root = FXMLLoader.load(getClass().getResource("AdminHomeP.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void generate(ActionEvent event) throws SQLException {
        lblout.setText("");
        if (useridtxt.getText().isEmpty() || desctxt.getText().isEmpty() || typecb.getValue().equals("Select Barcode Type") || countcb.getValue()==0 || categorycb.getValue().equals("Select Barcode Category") ) {
            lblout.setText("one or more fields are missing, please fill in all fields");
        } else {
            Database db = new Database();
            if(!db.lookUserID(Integer.parseInt(useridtxt.getText()))) {
                lblout.setText("User ID not Found");
            } else {
                for (int i = 0; i < countcb.getValue(); i++) {
                db.generateBarcode(desctxt.getText(), date.toString() , Integer.parseInt(useridtxt.getText()),categorycb.getValue(),typecb.getValue());
                lblout.setText("Barcode Generated Successfully");
                }
            }
        }
    }
}