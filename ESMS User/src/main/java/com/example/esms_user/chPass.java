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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

public class chPass extends loginController implements Initializable {
    private Connection c;
    private Statement s;
    private ResultSet r;
    @FXML
    private PasswordField old_pass;
    @FXML
    private TextField new_pass;
    @FXML
    private Button gene;
    @FXML
    private PasswordField confirm_pass;
    @FXML
    private Button submit_btn;
    @FXML
    private Text txt;
    @FXML
    private Button my_acc_btn;
    private int c_id;
    private String password = "g";
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

    public void pay_Bill(ActionEvent event) throws IOException{
        super.pay_Bill(event);
    }

    public void complaint(ActionEvent event) throws IOException{
        super.complaint(event);
    }

    public void logout(ActionEvent event){
        super.logout(event);
    }

    public void gene_password(){
        if(old_pass.getText().isEmpty()){
            txt.setText("Enter the Old Password First!");
        } else {
            String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&";
            String password = "";
            int i;
            Random rand = new Random();
            for (i = 0; i < 20; i++)
                password += str1.charAt(rand.nextInt(68));
            new_pass.setText(password);
            confirm_pass.setText(password);
            new FadeIn((confirm_pass)).play();
        }
    }

    public void change_password() throws SQLException {
        if(old_pass.getText().isEmpty() || new_pass.getText().isEmpty() || confirm_pass.getText().isEmpty()){
            txt.setText("Enter All the Fields!");
        } else {
            if(!password.equals(old_pass.getText())){
                txt.setText("Current password is Incorrect!");
            } else {
                String sql = "UPDATE user_login SET password='" + new_pass.getText() + "' WHERE c_id=" + c_id + ";";
                this.s = this.c.createStatement();
                this.s.executeUpdate(sql);
                txt.setText("Password Updated successfully!");
                old_pass.setText("");
                new_pass.setText("");
                confirm_pass.setText("");
            }
        }
    }

    public void getCId(int n) throws SQLException, IOException {
        this.c_id = n;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
        String c_id_s = String.valueOf(c_id);
        bufferedWriter.write(c_id_s);
        bufferedWriter.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        super.initialize(url, resourceBundle);
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
            c_id = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            String sql = "SELECT * FROM user_login WHERE c_id=" + c_id + ";" ;
            this.s = this.c.createStatement();
            this.r = this.s.executeQuery(sql);
            r.next();
            password = r.getString("password");
            old_pass.setText(password);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
