package com.example.esms_user;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginController extends Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Connection c;
    private Statement s;
    private ResultSet r;
    private String str = "";
    private int c_id;
    @FXML
    private Button logout_btn, pay_bill, pay_his,ch_pass, com_btn, pay_bill1, pay_btn;
    @FXML
    private ImageView icon2, user_icon, acc_img, bill_img, his_img, pass_ch_img, logout_img, complaint_img, user_c_img;
    @FXML
    protected ImageView c_bill_img;
    @FXML
    private Text no_txt, amt_txt, r_txt, add_txt, name_txt, email_txt;

    // switching between windows
    public void ch_pass_(ActionEvent event) throws IOException{
        try {
            Stage stage1 = (Stage) ch_pass.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("user_pass.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void pay_history(ActionEvent event) throws IOException{
        try {
            Stage stage1 = (Stage) pay_his.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("user_payHis.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void pay_Bill(ActionEvent event) throws IOException{
            Stage stage1 = (Stage) pay_bill.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("user_pay.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            new FadeIn(root).play();
    }

    public void pay_Bill1(ActionEvent event) throws IOException{
        try {
            Stage stage1 = (Stage) pay_btn.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("user_pay.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void complaint(ActionEvent event) throws IOException{
        try {
            Stage stage1 = (Stage) com_btn.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("complaint.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    // end of switching windows

    public void logout(ActionEvent event){
        ButtonType good_btn = new ButtonType("Log Out");
        ButtonType bad_btn = new ButtonType("Cancel");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", good_btn, bad_btn);
        new FadeIn(alert.getDialogPane()).play();
        alert.setTitle("Confirm Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Log out any unsaved changes will be lost!");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().setStyle("-fx-background-color: #2e9cca; -fx-border-color: #2e9cca; -fx-border-radius: 30; -fx-background-radius: 30; -fx-border-width: 10;");
        alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(res -> {
            if (res.equals(good_btn)) {
                Stage stage1 = (Stage) logout_btn.getScene().getWindow();
                stage1.close();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("user_login.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 1280, 720);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                new FadeIn(root).play();
            }
        });
    }

    public void init(int n) throws SQLException, IOException {
        c_id = n;
        chPass ch = new chPass();
        ch.getCId(c_id);
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        Image image = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/icon-modified.png");
        Image image1 = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/Untitled design-modified.png");
        Image acc = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/acc.png");
        Image bill = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/bill.png");
        Image his = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/history.png");
        Image ch_pass = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/ch_pass.png");
        Image logout = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/logout.png");
        Image issue = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/issue.png");
        Image img2 = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/billPay.png");
        Image img3 = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/user.png");
        try {
            user_c_img.setImage(img3);
            c_bill_img.setImage(img2);
        } catch (Exception e){

        }
        acc_img.setImage(acc);
        icon2.setImage(image);
        bill_img.setImage(bill);
        user_icon.setImage(image1);
        his_img.setImage(his);
        pass_ch_img.setImage(ch_pass);
        logout_img.setImage(logout);
        complaint_img.setImage(issue);
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
            c_id = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            String sql = "SELECT name, email, address FROM CUSTOMER_DETAILS WHERE c_id=" + c_id + ";";
            s = c.createStatement();
            r = s.executeQuery(sql);
            r.next();
            try {
                add_txt.setText(r.getString("address"));
                name_txt.setText(r.getString("name"));
                email_txt.setText(r.getString("email"));
            } catch (Exception e){

            }
        } catch (Exception e){

        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
            c_id = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            String sql = "SELECT * FROM BILLS WHERE c_id=" + c_id + ";";
            String bill_no = "";
            String amt_pay = "";
            s = c.createStatement();
            r = s.executeQuery(sql);
            r.next();
            bill_no = r.getString("billno");
            amt_pay = r.getString("amt");
            try {
                no_txt.setText(bill_no);
                amt_txt.setText(amt_pay);
            }catch (Exception e){

            }
        } catch (Exception e){
            try {
                no_txt.setText("All Bills Paid Good Job!");
                r_txt.setVisible(false);
                pay_btn.setVisible(false);
            } catch (Exception e1){

            }
        }
    }
}
