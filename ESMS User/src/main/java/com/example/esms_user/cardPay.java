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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class cardPay extends loginController implements Initializable {
    private Connection c;
    private Statement s;
    private ResultSet r;
    @FXML
    private Button cancel_btn, my_acc_btn;
    @FXML
    private ImageView master_img, visa_img;
    @FXML
    private TextField card_num, card_name, card_cvv;
    @FXML
    private Text txt, amt_txt;
    private String watt, amt, bill_no, bill_date, name_bill;
    private int a;
    private BigInteger num;

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

    public void complaint(ActionEvent event) throws IOException{
        super.complaint(event);
    }

    public void logout(ActionEvent event){
        super.logout(event);
    }

    public static void show(ActionEvent event){
        try {
            Scene scene;
            Parent root = FXMLLoader.load(Objects.requireNonNull(payBill.class.getResource("user_payment.fxml")));
            Stage stage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            stage.setScene(scene);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
            new FadeIn(root).play();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void cancelPayment(ActionEvent event){
        try {
            Stage stage1 = (Stage) cancel_btn.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("user_pay.fxml"));
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

    public void saveCard() throws IOException, SQLException {
        if(card_name.getText().equals("") || card_num.getText().equals("") || card_cvv.equals("")){
            txt.setText("Card Name or Card Number or CVV is empty!");
        } else {
            try {
                try {
                    num = BigInteger.valueOf(Long.parseLong(card_num.getText()));
                } catch (Exception e) {
                    txt.setText("Check Number!");
                    System.out.println(e);
                }
                BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
                int a = Integer.parseInt(bufferedReader.readLine());
                bufferedReader.close();
                String sql = "INSERT INTO CARD VALUES(" + a + "," + num + ",'" + card_name.getText() + "');";
                s = c.createStatement();
                s.executeUpdate(sql);
                txt.setText("Card Saved Successfully!");
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void payFinal(ActionEvent event){
        String bill = "\tPositive Voltage Electricity Supply Company Limited\n"
                + "\t\tElectricity Bill\n"
                + "\t\tGSTN No : 29AACCB1412G1Z5\n"
                + "\t\tO/o.AEE(Ele) S8-BOMMANAHALLI\n"
                + "----------------------------------------------------------\n"
                + "\t\tAccount Details : " + a + "\n"
                + "\t\tName : " + name_bill + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tConnection Details\n"
                + "\t\tTariff : " + "1LT2A1-N\n"
                + "\t\tSane Load : " + "1KW+0HP\n"
                + "----------------------------------------------------------\n"
                + "\t\tBilling Details\n"
                + "\t\tBill Date : " + bill_date + "\n"
                + "\t\tBill Number : " + bill_no + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tConsumption Detail\n"
                + "\t\tPower Factor : " + watt + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tNet Amount Due : " + amt + "\n";
        try {
            Random random = new Random();
            String fileName = "Bill" + (random.nextInt(9000) + 1000);
            String filePath = "/Users/nithinr/Desktop/" + fileName + ".txt";
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(bill);
            fileWriter.close();
        } catch (Exception e){
            System.out.println(e);
        }
        String date = String.valueOf(LocalDate.now());
        String sqlinsert = "INSERT INTO BILLS_PAID VALUES(" + a + ",'" +  date + "'," + watt + "," + amt + ",'" + name_bill + "','" + bill_no + "');";
        String sqldelete = "DELETE FROM BILLS WHERE c_id=" + a + " AND billno='" + bill_no + "';";
        try {
            txt.setText("Amount Paid Successfully!");
            s = c.createStatement();
            s.executeUpdate(sqlinsert);
            s = c.createStatement();
            s.executeUpdate(sqldelete);
            Thread.sleep(5000);
            cancelPayment(event);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        super.initialize(url, resourceBundle);
        Image master = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/maestro.png");
        Image visa = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/visa.png");
        master_img.setImage(master);
        visa_img.setImage(visa);
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
            a = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            String sql = "SELECT * FROM CARD WHERE c_id="+ a + ";" ;
            try {
                s = c.createStatement();
                r = s.executeQuery(sql);
                r.next();
                String s = r.getString("cardnum");
                System.out.println(s);
                card_name.setText(r.getString("cardname"));
                card_num.setText(r.getString("cardnum"));
            } catch (Exception e){

            }
            String sql1 = "SELECT * FROM BILLS WHERE c_id=" + a + ";";
            this.s = this.c.createStatement();
            this.r = this.s.executeQuery(sql1);
            r.next();
            this.bill_no = r.getString("billno");
            this.name_bill = r.getString("name");
            this.amt = r.getString("amt");
            this.watt = r.getString("watt");
            this.bill_date = r.getString("billdate");
            this.amt_txt.setText(amt);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
