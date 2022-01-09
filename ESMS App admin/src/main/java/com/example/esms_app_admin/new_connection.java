package com.example.esms_app_admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class new_connection extends loginConntroller implements Initializable {
    @FXML
    private TextField name_field, email_field, occupation_field, date_field, flat_no_field, phone_field, pincode_field, city_field, user_name_field, pass_field;
    @FXML
    private CheckBox check_btn;
    @FXML
    private Button clear_btn, save_btn;
    @FXML
    private Text text_waring;
    @FXML
    private TextArea address_field;
    @FXML
    private ComboBox state_select;
    private Connection c;
    private Statement s;
    private ResultSet r;

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

    // add new connection
    public void save_to_db() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS App admin/src/main/c_id_text/c_id.txt"));
            int c_id;
            c_id = Integer.parseInt(bufferedReader.readLine());
            System.out.println(c_id);
            c_id++;
            bufferedReader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/nithinr/ESMS App/ESMS App admin/src/main/c_id_text/c_id.txt"));
            String c_id_s = String.valueOf(c_id);
            bufferedWriter.write(c_id_s);
            bufferedWriter.close();
            String name_field = this.name_field.getText();
            String email_field = this.email_field.getText();
            String occupation_field = this.occupation_field.getText();
            String date_field = this.date_field.getText();
            String flat_no_field = this.flat_no_field.getText();
            String phone_field = this.phone_field.getText();
            String pincode_field = this.pincode_field.getText();
            String city_field = this.city_field.getText();
            String user_name_field = this.user_name_field.getText();
            String pass_field = this.pass_field.getText();
            String address = this.address_field.getText();
            String state = this.state_select.getSelectionModel().getSelectedItem().toString();
            boolean valid = false;
            Pattern p_alpha = Pattern.compile("^[a-zA-Z ]*$");
            Pattern p_num_phone = Pattern.compile("\\d{10}");
            Pattern p_pin = Pattern.compile("\\d{6}");
            Pattern p_flat_no = Pattern.compile("\\d{2,5}");
            //Pattern p_date = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
            System.out.println(p_alpha.matcher(name_field).find());
            System.out.println(p_alpha.matcher(user_name_field).find());
            System.out.println(p_alpha.matcher(occupation_field).find() );
            System.out.println(p_alpha.matcher(city_field).find());
            System.out.println(p_alpha.matcher(user_name_field).find());
            System.out.println(p_pin.matcher(pincode_field).find());
            System.out.println(p_flat_no.matcher(flat_no_field).find());
            System.out.println(p_num_phone.matcher(phone_field).find());

            if(p_alpha.matcher(name_field).find() &&
                    p_alpha.matcher(user_name_field).find() &&
                    p_alpha.matcher(occupation_field).find() &&
                    p_alpha.matcher(city_field).find() &&
                    p_alpha.matcher(user_name_field).find() &&
                    p_pin.matcher(pincode_field).find() &&
                    p_flat_no.matcher(flat_no_field).find() &&
                    p_num_phone.matcher(phone_field).find()
            ){
                valid = true;
            }
            if(check_btn.isSelected()){
                if(valid){
                    String insert_cus = "INSERT INTO CUSTOMER_DETAILS(c_id,name,email,occupation,phone,dob,flat_no,address,pincode,state,city) " +
                            "VALUES" +
                            "(" + c_id + ",'" + name_field + "','" + email_field + "','" + occupation_field + "'," + phone_field + ",'" + date_field + "'," + flat_no_field + ",'" + address + "'," + pincode_field + ",'" + state + "','" + city_field + "');";
                    String insert_user = "INSERT INTO user_login(c_id, user_name, password)" +
                            "VALUES (" + c_id + ",'" + user_name_field + "','" + pass_field + "');";
                    this.s = this.c.createStatement();
                    this.s.executeUpdate(insert_cus);
                    this.s.executeUpdate(insert_user);
                    text_waring.setText("Successfully added new Connection");
                } else {
                    text_waring.setText("Enter the Fields Correctly!");
                }
            } else {
                text_waring.setText("Select the check box!");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void clear_all_fields(){
        name_field.setText("");
        email_field.setText("");
        occupation_field.setText("");
        date_field.setText("");
        flat_no_field.setText("");
        phone_field.setText("");
        pincode_field.setText("");
        city_field.setText("");
        user_name_field.setText("");
        pass_field.setText("");
        address_field.setText("");
        text_waring.setText("");
        state_select.valueProperty().set(null);
        check_btn.setSelected(false);
    }

    private ObservableList<String> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        list = FXCollections.observableArrayList("Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chhattisgarh",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jharkhand",
                "Karnataka",
                "Kerala","Madhya Pradesh",
                "Maharashtra","Manipur",
                "Meghalaya","Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Rajasthan",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttar Pradesh",
                "Uttarakhand",
                "West Bengal",
                "Andaman and Nicobar",
                "Chandigarh",
                "Dadra Nagar Haveli and Daman Diu",
                "Delhi",
                "Jammu and Kashmir",
                "Ladakh",
                "Lakshadweep",
                "Puducherry");
        state_select.setItems(list);
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
    }
}
