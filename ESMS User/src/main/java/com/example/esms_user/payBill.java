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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class payBill extends loginController implements Initializable {
    private Connection c;
    private Statement s;
    private ResultSet r;
    @FXML
    private Button my_acc_btn, pay_btn, cancel_btn;
    @FXML
    private ImageView billPay_img;
    @FXML
    private Text amt_txt, bill_txt, name_txt, billdate_txt, kilo_txt, info_txt;
    // switching between windows
    @FXML
    private int c_id;

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

    public void complaint(ActionEvent event) throws IOException{
        super.complaint(event);
    }

    public void logout(ActionEvent event){
        super.logout(event);
    }

    public void toPayment(ActionEvent event) throws InterruptedException {
        if(bill_txt.getText().equals("")){
            info_txt.setText("No Bills to pay!");
        } else {
            cardPay obj = new cardPay();
            pay_btn.setOnAction(e -> obj.show(event));
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        super.initialize(url, resourceBundle);
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
            int a = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            String sql = "SELECT * FROM BILLS WHERE c_id=" + a + ";";
            this.s = this.c.createStatement();
            this.r = this.s.executeQuery(sql);
            r.next();
            bill_txt.setText(r.getString("billno"));
            name_txt.setText(r.getString("name"));
            amt_txt.setText(r.getString("amt"));
            kilo_txt.setText(r.getString("watt"));
            billdate_txt.setText(r.getString("billdate"));
            Image img = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/billPay.png");
            billPay_img.setImage(img);
        } catch (Exception e){
            info_txt.setText("No Bills!");
            System.out.println(e);
        }
    }
}
