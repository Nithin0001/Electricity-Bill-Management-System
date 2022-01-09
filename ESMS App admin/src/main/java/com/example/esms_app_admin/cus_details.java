package com.example.esms_app_admin;

import animatefx.animation.FadeIn;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class cus_details extends loginConntroller implements Initializable {
    private Connection c;
    private Statement s;
    private ResultSet r;
    @FXML
    private TableView<getCustomerDetails> cus_table;
    @FXML
    private TableColumn<getCustomerDetails, Integer> cid_col;
    @FXML
    private TableColumn<getCustomerDetails, String> name_col;
    @FXML
    private TableColumn<getCustomerDetails, String> email_col;
    @FXML
    private TableColumn<getCustomerDetails, String> occ_col;
    @FXML
    private TableColumn<getCustomerDetails, Integer> phone_col;
    @FXML
    private TableColumn<getCustomerDetails, String> dob_col;
    @FXML
    private TableColumn<getCustomerDetails, Integer> flat_col;
    @FXML
    private TableColumn<getCustomerDetails, String> address_col;
    @FXML
    private TableColumn<getCustomerDetails, String> pincode_col;
    @FXML
    private TableColumn<getCustomerDetails, String> state_col;
    @FXML
    private TableColumn<getCustomerDetails, String> city_col;
    private ObservableList<getCustomerDetails> list = FXCollections.observableArrayList();
    private int count = 0;
    @FXML
    private Text txt_box;
    @FXML
    private TextField name_search;
    @FXML
    private Button ter_conn;

    // switching between windows
    @Override
    public void my_acc_pane(ActionEvent event) throws IOException {
        super.my_acc_pane(event);
    }

    @Override
    public void inbox(ActionEvent event) throws IOException {
        super.inbox(event);
    }

    @Override
    public void bills_pane(ActionEvent event) throws IOException {
        super.bills_pane(event);
    }

    @Override
    public void customer_details(ActionEvent event) throws IOException {
        super.customer_details(event);
    }

    @Override
    public void logout(ActionEvent event) {
        super.logout(event);
    }
    // end of switching windows methods

    public void show_cus(){
        if(count == 0){
            txt_box.setText("No Customers!!!");
        } else {
            list.clear();
            name_search.setText("");
            txt_box.setText("");
            cus_table.getItems().clear();
            try {
                this.s = this.c.createStatement();
                //String s = "SELECT * FROM CUSTOMER_DETAILS";
                //this.r = this.s.executeQuery(s);
                CallableStatement callableStatement = c.prepareCall("{CALL DISPLAY_CUSTOMERS}");
                callableStatement.execute();
                this.r = callableStatement.getResultSet();
                while (r.next()) {
                    list.add(new getCustomerDetails(r.getInt("c_id"), r.getString("name"), r.getString("email"), r.getString("occupation"), r.getBigDecimal("phone"), r.getString("dob"), r.getInt("flat_no"), r.getString("address"), r.getInt("pincode"), r.getString("state"), r.getString("city")));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            try {
                cid_col.setCellValueFactory(new PropertyValueFactory<>("c_id"));
                name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
                email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
                occ_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOcc()));
                phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
                dob_col.setCellValueFactory(new PropertyValueFactory<>("dob"));
                flat_col.setCellValueFactory(new PropertyValueFactory<>("flat_no"));
                address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
                pincode_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPin_code()));
                state_col.setCellValueFactory(new PropertyValueFactory<>("state"));
                city_col.setCellValueFactory(new PropertyValueFactory<>("city"));
                cus_table.setItems(list);
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void search() throws SQLException {
        if(name_search.getText().equals("")){
            txt_box.setText("Enter a name to search!");
        } else {
            cus_table.getItems().clear();
            txt_box.setText("");
            String s = "SELECT * FROM CUSTOMER_DETAILS WHERE name='" + name_search.getText() + "';";
            this.r = this.s.executeQuery(s);
            while (r.next()) {
                list.add(new getCustomerDetails(r.getInt("c_id"), r.getString("name"), r.getString("email"), r.getString("occupation"), r.getBigDecimal("phone"), r.getString("dob"), r.getInt("flat_no"), r.getString("address"), r.getInt("pincode"), r.getString("state"), r.getString("city")));
            }
            if(list.isEmpty()){
                txt_box.setText("Customer Not Found!");
            } else {
            cid_col.setCellValueFactory(new PropertyValueFactory<>("c_id"));
            name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            occ_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOcc()));
            phone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
            dob_col.setCellValueFactory(new PropertyValueFactory<>("dob"));
            flat_col.setCellValueFactory(new PropertyValueFactory<>("flat_no"));
            address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
            pincode_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPin_code()));
            state_col.setCellValueFactory(new PropertyValueFactory<>("state"));
            city_col.setCellValueFactory(new PropertyValueFactory<>("city"));
            cus_table.setItems(list);
            }
        }
    }

    public void clear_table(){
        cus_table.getItems().clear();
        txt_box.setText("");
        name_search.setText("");
    }

    public void delete_conn(ActionEvent event){
            ter_conn.setOnAction(e -> {
                try {
                    deleteConn.show(event);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        try {
            this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
            String count_string = "SELECT COUNT(c_id) FROM user_login";
            this.s = this.c.createStatement();
            this.r = this.s.executeQuery(count_string);
            r.next();
            count = r.getInt(1);
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
    }
}
