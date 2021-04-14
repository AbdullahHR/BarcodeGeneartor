/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcodegenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Ahr97
 */
public class Database {
    private ResultSet rs;
    private Connection connect() throws SQLException {
        Connection con = null;
        String url ="jdbc:sqlite:src/database/BGProject.db";
        con = DriverManager.getConnection(url);
        System.out.println("connected...");
        return con;
    }
    
    public void addUser(String email, String password, String city, String firstname, String lastname) throws SQLException{
        String sql = "INSERT INTO users(email,password,city,firstname,lastname) VALUES('"+email+"','"+password+"','"+city+"','"+firstname+"','"+lastname+"')";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("User Added successfully");
    }
    
    public void getUsers(ObservableList oblist) throws SQLException {
        String sql = "SELECT * FROM users";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            oblist.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("password"),rs.getString("city"),rs.getString("firstname"),rs.getString("lastname")));
        }
        rs.close();
        con.close();
    }
    
    public void searchUser(ObservableList oblist, String name) throws SQLException {
        String sql = "SELECT * FROM users WHERE firstname LIKE '"+name+"%'";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            oblist.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("password"),rs.getString("city"),rs.getString("firstname"),rs.getString("lastname")));
        }
        rs.close();
        con.close();
    }
    
    public void getUserName(ArrayList<String> al) throws SQLException {
        String sql = "SELECT email FROM users";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            al.add(rs.getString("email"));
        }
        rs.close();
        con.close();    
    }
    
    public void editUser(String email, String password, String city, String firstname, String lastname,String oldEmail) throws SQLException{
        String sql = "UPDATE users set email = '"+email+"', password = '"+password+"', city = '"+city+"',firstname = '"+firstname+"', lastname = '"+lastname+"' WHERE email LIKE '"+oldEmail+"'";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("User Edited successfully");
    }
    
    public boolean lookUser(String email) throws SQLException {
        String sql = "SELECT email FROM users";
        Boolean check = false;
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("email").equalsIgnoreCase(email)){
                check = true;
                break;
            } 
        }
        rs.close();
        con.close();
        return check;
    }
    
    public boolean lookUserID(Integer id) throws SQLException {
        String sql = "SELECT id FROM users";
        Boolean check = false;
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            if(Integer.parseInt(rs.getString("id"))== id){
                check = true;
                break;
            } 
        }
        rs.close();
        con.close();
        return check;
    }
    
    public void deleteUser(String email) throws SQLException{
        String sql = "DELETE FROM users WHERE email LIKE '"+email+"'";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("User Deleted successfully");
    }
    
    public boolean login(String email, String password) throws SQLException {
        boolean check = false;
        String sql = "SELECT * FROM users";
        Connection con = connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            String user = rs.getString("email");
            String pass = rs.getString("password");
            if(email.equals(user)&&password.equals(pass)){
                check = true;
                break;
            } else {
                check = false;
            }
        }
        st.close();
        con.close();
        return check;
    }
    
    public void getTypes(ObservableList oblist) throws SQLException {
        String sql = "SELECT * FROM type";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            oblist.add(new Type(rs.getInt("id"),rs.getString("barcodetype"),rs.getString("typedescription"),rs.getString("usage")));
        }
        rs.close();
        con.close();
    }
    
    public void searchType(ObservableList oblist, String barcodetype) throws SQLException {
        String sql = "SELECT * FROM type WHERE barcodetype LIKE '"+barcodetype+"%'";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            oblist.add(new Type(rs.getInt("id"),rs.getString("barcodetype"),rs.getString("typedescription"),rs.getString("usage")));
        }
        rs.close();
        con.close();
    }
    
    public void addType(String barcodetype, String typedescription, String usage) throws SQLException{
        String sql = "INSERT INTO type(barcodetype, typedescription, usage) VALUES('"+barcodetype+"','"+typedescription+"','"+usage+"')";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Type Added successfully");
    }
    
    public boolean lookTypes(String barcodetype) throws SQLException {
        String sql = "SELECT barcodetype FROM type";
        Boolean check = false;
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("barcodetype").equalsIgnoreCase(barcodetype)){
                check = true;
                break;
            } 
        }
        rs.close();
        con.close();
        return check;
    }
    
    public void getTypeName(ArrayList<String> al) throws SQLException {
        String sql = "SELECT barcodetype FROM type";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            al.add(rs.getString("barcodetype"));
        }
        rs.close();
        con.close();    
    }
    
    public void editType(String barcodetype, String typedescription, String usage, String type) throws SQLException{
        String sql = "UPDATE type set barcodetype = '"+barcodetype+"', typedescription = '"+typedescription+"', usage = '"+usage+"' WHERE barcodetype LIKE '"+type+"'";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Type Edited successfully");
    }
    
    public void deleteType(String type) throws SQLException{
        String sql = "DELETE FROM type WHERE barcodetype LIKE '"+type+"'";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Type Deleted successfully");
    }
    
    public void getCategories(ObservableList oblist) throws SQLException {
        String sql = "SELECT * FROM category";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            oblist.add(new Category(rs.getInt("id"),rs.getString("category"),rs.getString("categorydescription")));
        }
        rs.close();
        con.close();
    }
    
    public void searchCategory(ObservableList oblist, String category) throws SQLException {
        String sql = "SELECT * FROM category WHERE category LIKE '"+category+"%'";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            oblist.add(new Category(rs.getInt("id"),rs.getString("category"),rs.getString("categorydescription")));
        }
        rs.close();
        con.close();
    }
    
    public void addCategory(String category, String categorydescription) throws SQLException{
        String sql = "INSERT INTO category(category, categorydescription) VALUES('"+category+"','"+categorydescription+"')";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Category Added successfully");
    }
    
    public boolean lookCategory(String category) throws SQLException {
        String sql = "SELECT category FROM category";
        Boolean check = false;
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("category").equalsIgnoreCase(category)){
                check = true;
                break;
            } 
        }
        rs.close();
        con.close();
        return check;
    }
    
    public void getCategory(ArrayList<String> al) throws SQLException {
        String sql = "SELECT category FROM category";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            al.add(rs.getString("category"));
        }
        rs.close();
        con.close();    
    }
    
    public void editCategory(String category, String categorydescription, String oldCat) throws SQLException{
        String sql = "UPDATE category set category = '"+category+"', categorydescription = '"+categorydescription+"' WHERE category LIKE '"+oldCat+"'";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Category Edited successfully");
    }
    
    public void deleteCategory(String category) throws SQLException{
        String sql = "DELETE FROM category WHERE category LIKE '"+category+"'";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Category Deleted successfully");
    }
    
    public void getBarcodes(ObservableList oblist) throws SQLException {
        String sql = "SELECT * FROM barcodes";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            oblist.add(new Barcode(rs.getInt("id"),rs.getString("description"),rs.getString("generationdate"),rs.getInt("userid"),rs.getString("category"),rs.getString("type")));
        }
        rs.close();
        con.close();
    }
    
    public void generateBarcode(String description, String generationdate, Integer userid, String category, String type) throws SQLException{
        String sql = "INSERT INTO barcodes(description,generationdate,userid,category,type) VALUES('"+description+"','"+generationdate+"','"+userid+"','"+category+"','"+type+"')";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Barcode Generated successfully");
    }
    
    public void deleteBarcode(String description) throws SQLException{
        String sql = "DELETE FROM barcodes WHERE description LIKE '"+description+"'";
        Connection con = connect();
        Statement st = con.createStatement();
        st.executeUpdate(sql);
        st.close();
        con.close();
        System.out.println("Barcode Deleted successfully");
    }
    
    public void getBarcodeDesc(ArrayList<String> al) throws SQLException {
        String sql = "SELECT description FROM barcodes";
        Connection con = connect();
        Statement st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            al.add(rs.getString("description"));
        }
        rs.close();
        con.close();    
    }
}
