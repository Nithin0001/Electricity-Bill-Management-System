package com.example.esms_app_admin;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Bills extends loginConntroller implements Initializable {
    @FXML
    private Text watt_txt, txt_show;
    @FXML
    private TextField watt_field, c_id_field;
    @FXML
    private Button submit_btn;
    private Connection c;
    private Statement s;
    private ResultSet r;
    int count1 = 0, c_id = 0, watt = 0;
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

    public void search() throws SQLException {
        try {
            Pattern p = Pattern.compile("\\d\\d\\d");
            String s = c_id_field.getText();
            if (p.matcher(s).find()) {
                String count_string = "SELECT COUNT(c_id) FROM user_login WHERE c_id=" + Integer.parseInt(s) + ";";
                this.s = this.c.createStatement();
                this.r = this.s.executeQuery(count_string);
                r.next();
                count1 = r.getInt(1);
                if(count1 == 1) {
                    c_id = Integer.parseInt(s);
                    watt_field.setVisible(true);
                    watt_txt.setVisible(true);
                    submit_btn.setVisible(true);
                    new FadeIn(watt_field).play();
                    new FadeIn(watt_txt).play();
                    new FadeIn(submit_btn).play();
                    txt_show.setText("");
                } else {
                    txt_show.setText("Customer Id Invalid!");
                }
            } else {
                txt_show.setText("Enter a number!");
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void insert_watt(){
        try {
            if(watt_field.getText().isEmpty()){
                txt_show.setText("Enter Kilo Watt!");
            } else {
                try {
                    try {
                        watt = Integer.parseInt(watt_field.getText());
                    } catch (Exception e){
                        txt_show.setText("Wattage should be a number!");
                    }
                    int amt = watt * 6;
                    String sql2 = "SELECT name FROM CUSTOMER_DETAILS WHERE c_id=" + c_id + ";";
                    this.s = this.c.createStatement();
                    this.r = this.s.executeQuery(sql2);
                    String name = "";
                    Random random = new Random();
                    String date = String.valueOf(LocalDate.now());
                    String billno = date + random.nextInt(900) + 100;
                    while (r.next()) {
                        name = r.getString("name");
                    }
                    String sql = "INSERT INTO BILLS VALUES(" + c_id + ",'" + name + "'," + amt + "," + watt + ",'" + date + "','" + billno +"');";
                    this.s = this.c.createStatement();
                    this.s.executeUpdate(sql);
                    txt_show.setText("Bill Generated for " + c_id);
                } catch (Exception e){
                    System.out.println(e);
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        watt_field.setVisible(false);
        watt_txt.setVisible(false);
        submit_btn.setVisible(false);
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
    }
}
