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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

public class payHis extends loginController implements Initializable {
    private Connection c;
    private Statement s;
    private ResultSet r;
    @FXML
    private Button my_acc_btn;
    @FXML
    private ImageView card_img1, card_img2, card_img3, card_img4, card_img5;
    @FXML
    private Rectangle his_rect1, his_rect2, his_rect3, his_rect4;
    @FXML
    private Text billno_txt, billamt_txt, billdate_txt, billno_txt1, billamt_txt1, billdate_txt1, billno_txt2, billamt_txt2, billdate_txt2, billno_txt3, billamt_txt3, billdate_txt3, billno_txt4, billamt_txt4, billdate_txt4;
    @FXML
    private Button dI_btn1, dI_btn2, dI_btn3, dI_btn4, dI_btn;
    private int c_id;
    private String[] bill_no, amt, bill_date;
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

    public void pay_Bill(ActionEvent event) throws IOException{
        super.pay_Bill(event);
    }

    public void complaint(ActionEvent event) throws IOException{
        super.complaint(event);
    }

    public void ch_pass_(ActionEvent event) throws IOException{
        super.ch_pass_(event);
    }

    public void logout(ActionEvent event){
        super.logout(event);
    }

    public void dI0(){
        String bill = "\tPositive Voltage Electricity Supply Company Limited\n"
                + "\t\tElectricity Bill\n"
                + "\t\tGSTN No : 29AACCB1412G1Z5\n"
                + "\t\tO/o.AEE(Ele) S8-BOMMANAHALLI\n"
                + "----------------------------------------------------------\n"
                + "\t\tConnection Details\n"
                + "\t\tTariff : " + "1LT2A1-N\n"
                + "\t\tSane Load : " + "1KW+0HP\n"
                + "----------------------------------------------------------\n"
                + "\t\tBilling Details\n"
                + "\t\tBill Date : " + bill_date[0] + "\n"
                + "\t\tBill Number : " + bill_no[0] + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tNet Amount Paid : " + amt[0] + "\n";
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
    }

    public void dI1(){
        String bill = "\tPositive Voltage Electricity Supply Company Limited\n"
                + "\t\tElectricity Bill\n"
                + "\t\tGSTN No : 29AACCB1412G1Z5\n"
                + "\t\tO/o.AEE(Ele) S8-BOMMANAHALLI\n"
                + "----------------------------------------------------------\n"
                + "\t\tConnection Details\n"
                + "\t\tTariff : " + "1LT2A1-N\n"
                + "\t\tSane Load : " + "1KW+0HP\n"
                + "----------------------------------------------------------\n"
                + "\t\tBilling Details\n"
                + "\t\tBill Date : " + bill_date[1] + "\n"
                + "\t\tBill Number : " + bill_no[1] + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tNet Amount Paid : " + amt[1] + "\n";
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
    }

    public void dI2(){
        String bill = "\tPositive Voltage Electricity Supply Company Limited\n"
                + "\t\tElectricity Bill\n"
                + "\t\tGSTN No : 29AACCB1412G1Z5\n"
                + "\t\tO/o.AEE(Ele) S8-BOMMANAHALLI\n"
                + "----------------------------------------------------------\n"
                + "\t\tConnection Details\n"
                + "\t\tTariff : " + "1LT2A1-N\n"
                + "\t\tSane Load : " + "1KW+0HP\n"
                + "----------------------------------------------------------\n"
                + "\t\tBilling Details\n"
                + "\t\tBill Date : " + bill_date[2] + "\n"
                + "\t\tBill Number : " + bill_no[2] + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tNet Amount Paid : " + amt[2] + "\n";
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
    }

    public void dI3(){
        String bill = "\tPositive Voltage Electricity Supply Company Limited\n"
                + "\t\tElectricity Bill\n"
                + "\t\tGSTN No : 29AACCB1412G1Z5\n"
                + "\t\tO/o.AEE(Ele) S8-BOMMANAHALLI\n"
                + "----------------------------------------------------------\n"
                + "\t\tConnection Details\n"
                + "\t\tTariff : " + "1LT2A1-N\n"
                + "\t\tSane Load : " + "1KW+0HP\n"
                + "----------------------------------------------------------\n"
                + "\t\tBilling Details\n"
                + "\t\tBill Date : " + bill_date[3] + "\n"
                + "\t\tBill Number : " + bill_no[3] + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tNet Amount Paid : " + amt[3] + "\n";
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
    }

    public void dI4(){
        String bill = "\tPositive Voltage Electricity Supply Company Limited\n"
                + "\t\tElectricity Bill\n"
                + "\t\tGSTN No : 29AACCB1412G1Z5\n"
                + "\t\tO/o.AEE(Ele) S8-BOMMANAHALLI\n"
                + "----------------------------------------------------------\n"
                + "\t\tConnection Details\n"
                + "\t\tTariff : " + "1LT2A1-N\n"
                + "\t\tSane Load : " + "1KW+0HP\n"
                + "----------------------------------------------------------\n"
                + "\t\tBilling Details\n"
                + "\t\tBill Date : " + bill_date[4] + "\n"
                + "\t\tBill Number : " + bill_no[4] + "\n"
                + "----------------------------------------------------------\n"
                + "\t\tNet Amount Paid : " + amt[4] + "\n";
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
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        super.initialize(url, resourceBundle);
        try{
            Image img = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/credit-card.png");
            card_img1.setImage(img);
            card_img2.setImage(img);
            card_img3.setImage(img);
            card_img4.setImage(img);
            card_img5.setImage(img);
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new FileReader("/Users/nithinr/ESMS App/ESMS User/src/main/java/com/example/esms_user/c_Id"));
            c_id = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            String sql = "SELECT * FROM BILLS_PAID WHERE c_id=" + c_id + ";";
            s = c.createStatement();
            r = s.executeQuery(sql);
            int i = 0;
            bill_date = new String[100];
            bill_no = new String[100];
            amt = new String[100];
            while (r.next()){
                bill_no[i] = r.getString("billno");
                bill_date[i] = r.getString("paid_date");
                amt[i] = r.getString("amt");
                i++;
            }
            // history display logic max 5 transactions
            if(i > 0){
                billno_txt.setText(bill_no[0]);
                billamt_txt.setText(amt[0]);
                billdate_txt.setText(bill_date[0]);
                billno_txt1.setText(bill_no[1]);
                billamt_txt1.setText(amt[1]);
                billdate_txt1.setText(bill_date[1]);
                billno_txt2.setText(bill_no[2]);
                billamt_txt2.setText(amt[2]);
                billdate_txt2.setText(bill_date[2]);
                billno_txt3.setText(bill_no[3]);
                billamt_txt3.setText(amt[3]);
                billdate_txt3.setText(bill_date[3]);
                billno_txt4.setText(bill_no[4]);
                billamt_txt4.setText(amt[4]);
                billdate_txt4.setText(bill_date[4]);
                if(i < 2 ){
                his_rect1.setVisible(false);
                his_rect2.setVisible(false);
                his_rect3.setVisible(false);
                his_rect4.setVisible(false);
                billamt_txt1.setVisible(false);
                billamt_txt2.setVisible(false);
                billamt_txt3.setVisible(false);
                billamt_txt4.setVisible(false);
                billno_txt1.setVisible(false);
                billno_txt2.setVisible(false);
                billno_txt3.setVisible(false);
                billno_txt4.setVisible(false);
                billdate_txt1.setVisible(false);
                billdate_txt2.setVisible(false);
                billdate_txt3.setVisible(false);
                billdate_txt4.setVisible(false);
                card_img2.setVisible(false);
                card_img3.setVisible(false);
                card_img4.setVisible(false);
                card_img5.setVisible(false);
                dI_btn1.setVisible(false);
                dI_btn2.setVisible(false);
                dI_btn3.setVisible(false);
                dI_btn4.setVisible(false);
            } else if (i < 3){
                his_rect2.setVisible(false);
                his_rect3.setVisible(false);
                his_rect4.setVisible(false);
                billamt_txt2.setVisible(false);
                billamt_txt3.setVisible(false);
                billamt_txt4.setVisible(false);
                billno_txt2.setVisible(false);
                billno_txt3.setVisible(false);
                billno_txt4.setVisible(false);
                billdate_txt2.setVisible(false);
                billdate_txt3.setVisible(false);
                billdate_txt4.setVisible(false);
                card_img3.setVisible(false);
                card_img4.setVisible(false);
                card_img5.setVisible(false);
                dI_btn2.setVisible(false);
                dI_btn3.setVisible(false);
                dI_btn4.setVisible(false);
            } else if (i < 4 ){
                his_rect3.setVisible(false);
                his_rect4.setVisible(false);
                billamt_txt3.setVisible(false);
                billamt_txt4.setVisible(false);
                billno_txt3.setVisible(false);
                billno_txt4.setVisible(false);
                billdate_txt3.setVisible(false);
                billdate_txt4.setVisible(false);
                card_img4.setVisible(false);
                card_img5.setVisible(false);
                dI_btn3.setVisible(false);
                dI_btn4.setVisible(false);
            } else {
                his_rect4.setVisible(false);
                billamt_txt4.setVisible(false);
                billno_txt4.setVisible(false);
                billdate_txt4.setVisible(false);
                card_img5.setVisible(false);
                dI_btn4.setVisible(false);
            }
            } else {
                billno_txt.setText("No Transaction");
                dI_btn.setVisible(false);
                billamt_txt.setVisible(false);
                billdate_txt.setVisible(false);
                his_rect1.setVisible(false);
                his_rect2.setVisible(false);
                his_rect3.setVisible(false);
                his_rect4.setVisible(false);
                billamt_txt1.setVisible(false);
                billamt_txt2.setVisible(false);
                billamt_txt3.setVisible(false);
                billamt_txt4.setVisible(false);
                billno_txt1.setVisible(false);
                billno_txt2.setVisible(false);
                billno_txt3.setVisible(false);
                billno_txt4.setVisible(false);
                billdate_txt1.setVisible(false);
                billdate_txt2.setVisible(false);
                billdate_txt3.setVisible(false);
                billdate_txt4.setVisible(false);
                card_img2.setVisible(false);
                card_img3.setVisible(false);
                card_img4.setVisible(false);
                card_img5.setVisible(false);
                dI_btn1.setVisible(false);
                dI_btn2.setVisible(false);
                dI_btn3.setVisible(false);
                dI_btn4.setVisible(false);
            }
            // end
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
