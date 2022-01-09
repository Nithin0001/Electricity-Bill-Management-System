package com.example.esms_user;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class complaint extends loginController implements Initializable {
    private Connection c;
    private Statement s;
    private ResultSet r;
    @FXML
    private Button my_acc_btn;
    @FXML
    private TextArea txt_area;
    @FXML
    private Text txt;
    private int c_id;
    // switching between windows
    public void land_page(ActionEvent event) throws IOException {
        try {
            Stage stage1 = (Stage) my_acc_btn.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("user_UI.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void pay_history(ActionEvent event) throws IOException{
        super.pay_history(event);
    }

    public void ch_pass_(ActionEvent event) throws IOException{
        super.ch_pass_(event);
    }

    public void pay_Bill(ActionEvent event) throws IOException{
        super.pay_Bill(event);
    }

    public void logout(ActionEvent event){
        super.logout(event);
    }

    public void submit1(){
        if(txt_area.getText().equals("")){
            txt.setText("Message is empty!");
        } else {
            try{
                String sql = "SELECT name, email FROM CUSTOMER_DETAILS WHERE c_id=" + c_id + ";";
                s = c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                String name = r.getString("name");
                String email = r.getString("email");
                String msg = txt_area.getText();
                String sql_insert = "INSERT INTO inbox VALUES(" + c_id + ",'" + name + "','" + email + "','" + msg + "');";
                s = c.createStatement();
                s.executeUpdate(sql_insert);
                txt.setText("Message sent Successfully!");
                txt_area.setText("");
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        super.initialize(url, resourceBundle);
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
            c_id = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
