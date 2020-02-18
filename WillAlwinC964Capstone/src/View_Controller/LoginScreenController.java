/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.DBConnection;
import Model.Emp;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WillA
 */
public class LoginScreenController implements Initializable {

    Stage stage;
    Parent scene;
    //Locale myLocale = Locale.getDefault();
    String fileName = "src/Files/userLog.txt", userLogText;    

    @FXML
    private PasswordField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label errorMsgLabel;

    @FXML
    void onActionEnterProgram(ActionEvent event) throws IOException, ClassNotFoundException {
        //query the database with the username provided, and get password stored in the database
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String userName = usernameTextField.getText();

        try{
            //connect to db
            DBConnection.makeConnection();
            //create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM EMP WHERE  userName= ?";
            //prepare statement
            ps = DBConnection.getConn().prepareStatement(sql);
            //bind the userName to the ?
            ps.setString(1, userName);
            //execute query
            resultSet = ps.executeQuery();
            //extract password from the db
            String dbPassword = null;

            Emp emp = null;
            while(resultSet.next()){
                dbPassword = resultSet.getString("password");
                //int userId, String userName, String password, String createdBy, String lastUpdateBy, String admin, LocalDate createDate, LocalDate lastUpdate
                emp = new Emp(resultSet.getInt("userId"),
                                resultSet.getString("userName"),
                                resultSet.getString("password"),
                                resultSet.getString("createdBy"),
                                resultSet.getString("lastUpdateBy"),
                                resultSet.getString("admin").charAt(0),
                                resultSet.getDate("createDate").toLocalDate(),        
                                resultSet.getDate("lastUpdate").toLocalDate());
            }
            
            if(passwordTextField.getText().equals(dbPassword)){
                try{
                    DBConnection.setLoggedInUser(emp);
                    Calendar myCalendar = Calendar.getInstance();
                    //save login data to file
                    FileWriter fWriter = new FileWriter(fileName, true);
                    try (PrintWriter outputFile = new PrintWriter(fWriter)) {
                        userLogText = "User ID: " + emp.getUserId() + "\nLogged in: " + myCalendar.getTime();
                        outputFile.println(userLogText);
                    }
                     //set new scene and load   
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View_Controller/MaintenanceView.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                } 
                catch(IOException e){
                    System.out.println("File not found!");
                }
            }            
            else{
                errorMsgLabel.setText("User name/password not recognized.");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorMsgLabel.setText("");
    }        
}

