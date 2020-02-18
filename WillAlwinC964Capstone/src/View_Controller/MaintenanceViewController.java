/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.DBConnection;
import Model.Emp;
import Model.Pipe;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Will
 */

public class MaintenanceViewController implements Initializable{
    ObservableList<Pipe> pipes = FXCollections.observableArrayList();
    ObservableList<Emp> emps = FXCollections.observableArrayList();
    Stage stage;
    Parent scene;
    
    @FXML
    private TableView<Pipe> pipeTableView;
    
    @FXML
    private TableColumn<?, ?> pipeId;

    @FXML
    private TableColumn<?, ?> featureID;

    @FXML
    private TableColumn<?, ?> nextMaint;

    @FXML
    private TableColumn<?, ?> lastMaint;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> landUse;

    @FXML
    private TableColumn<?, ?> techAssigned;

    @FXML
    private Label currentUserLabel;

    @FXML
    private Button SubmitWorkSchedule;

    @FXML
    private ComboBox<String> TechDropdownMenu;

    @FXML
    private Button ViewReportButton;

    @FXML
    private Label SubmitWorkErrorLabel;
    
    @FXML
    void EditUserButtonClicked(MouseEvent event) {

    }

    @FXML
    void LogoutButtonClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/LoginScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void SubmitWorkButtonClicked(ActionEvent event) {
        if(!pipeTableView.getSelectionModel().isEmpty()){
            if(!TechDropdownMenu.getSelectionModel().isEmpty()){
            
            }   
            else{
                SubmitWorkErrorLabel.setText("Technician must be selected.");
            }     
        }
        else{
            SubmitWorkErrorLabel.setText("Pipe must be selected.");
        }
    }

    @FXML
    void ViewReportsButtonClicked(ActionEvent event) {

    }
    
    public void loadPipes() throws SQLException{
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            //connect to DB
            DBConnection.makeConnection();
            //create a statement object
            statement = DBConnection.getConn().createStatement();            
            //create the sql query
            resultSet = statement.executeQuery("SELECT * FROM U06dWx.WCCSD_PIPE");
            while(resultSet.next()){  
                Pipe newPipe = new Pipe(resultSet.getInt("objectId"),
                                        resultSet.getInt("lengthFt"),
                                        resultSet.getInt("diameter"),
                                        resultSet.getInt("stormwater"),
                                        resultSet.getInt("pci"),
                                        resultSet.getInt("pacp"),
                                        resultSet.getInt("age"),
                                        resultSet.getInt("upstreamCnt"),
                                        resultSet.getInt("slClass"),
                                        resultSet.getInt("directCnt"),
                                        resultSet.getInt("flushPerYear"),
                                        resultSet.getInt("vegScore"),
                                        resultSet.getInt("mhAngle"),
                                        resultSet.getFloat("featureId"),
                                        resultSet.getFloat("fromMH"),
                                        resultSet.getFloat("slope"),
                                        resultSet.getFloat("flowTo"),
                                        resultSet.getFloat("rise"),
                                        resultSet.getFloat("qInf"),
                                        resultSet.getFloat("q"),
                                        resultSet.getFloat("dLeftTarget"),
                                        resultSet.getFloat("dn"),
                                        resultSet.getFloat("nFull"),
                                        resultSet.getFloat("sedVol"),
                                        resultSet.getFloat("sedPerYear"),
                                        resultSet.getFloat("v"),
                                        resultSet.getFloat("adjVar"),
                                        resultSet.getString("material"),
                                        resultSet.getString("cfcc"),
                                        resultSet.getString("landuse"),
                                        resultSet.getString("knownIssues"),
                                        resultSet.getString("upsource"),
                                        resultSet.getString("gdbGeomattrData"),
                                        resultSet.getString("tech"),
                                        resultSet.getDate("lastMaint").toLocalDate(),
                                        resultSet.getDate("nextMaint").toLocalDate());                                 
                pipes.add(newPipe);
                System.out.println(newPipe.getObjectId());
            }
            pipeTableView.getItems().addAll(pipes);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            if(DBConnection.getConn() != null)
                DBConnection.closeConnection();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
    }
        
    public void loadEmps() throws SQLException{
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            //connect to DB
            DBConnection.makeConnection();
            //create a statement object
            statement = DBConnection.getConn().createStatement();            
            //create the sql query
            resultSet = statement.executeQuery("SELECT * FROM U06dWx.EMP");
            while(resultSet.next()){  
                //int userId, String userName, String password, String createdBy, String lastUpdateBy, String admin, LocalDate createDate, LocalDate lastUpdate
                Emp newEmp = new Emp(resultSet.getInt("userID"),
                                resultSet.getString("userName"),
                                resultSet.getString("password"),
                                resultSet.getString("createdBy"),
                                resultSet.getString("lastUpdateBy"),
                                resultSet.getString("admin").charAt(0),
                                resultSet.getDate("createDate").toLocalDate(),        
                                resultSet.getDate("lastUpdate").toLocalDate());                                                           
                emps.add(newEmp);
            }
            //pipeTableView.getItems().addAll(emps);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            if(DBConnection.getConn() != null)
                DBConnection.closeConnection();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
    }    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pipeId.setCellValueFactory(new PropertyValueFactory<> ("objectId"));
        featureID.setCellValueFactory(new PropertyValueFactory<> ("featureId"));
        nextMaint.setCellValueFactory(new PropertyValueFactory<> ("nextMaint"));
        lastMaint.setCellValueFactory(new PropertyValueFactory<> ("lastMaint"));
        type.setCellValueFactory(new PropertyValueFactory<> ("material"));
        landUse.setCellValueFactory(new PropertyValueFactory<> ("landuse"));
        techAssigned.setCellValueFactory(new PropertyValueFactory<> ("tech"));
        SubmitWorkErrorLabel.setText("");
        currentUserLabel.setText(DBConnection.getLoggedInUser().getUserName());
        
        try {
            loadPipes();
            loadEmps();
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Emp emp: emps){
            if(emp.getAdmin() == 'N'){
                TechDropdownMenu.getItems().add(emp.getUserName());
            }    
        }
        System.out.print(DBConnection.getLoggedInUser().getUserName());
    } 
    
    public ObservableList<Emp> getAllEmps(){
        return emps;
    }

}
  

