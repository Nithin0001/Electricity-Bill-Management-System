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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginConntroller implements Initializable {
    @FXML
    private Button logout_btn, new_conn_btn, my_acc_btn, bills_btn, customer_deatils_btn, inbox_btn, del_all_btn;
    @FXML
    private ImageView img_icon, admin_logo, acc_img,new_img,bill_img,cus_img,inbox_img,logout_img;
    @FXML
    private TextField inbox_search_field;
    @FXML
    private Text waring_txt_inbox;
    @FXML
    private TableView<get_msg_inbox> inbox_table;
    @FXML
    private TableColumn<get_msg_inbox, Integer> c_id_col;
    @FXML
    private TableColumn<get_msg_inbox, String> name_col, email_col, msg_col;
    @FXML
    private TableColumn<?, ?> action_col;
    private Stage stage;
    private Scene scene;
    private Connection c;
    private Statement s;
    private ResultSet r;
    int k = 2;
    private Button[] btn = new Button[k];
    private ObservableList<get_msg_inbox> list = FXCollections.observableArrayList();
    int count = 0;

    // switching between windows
    public void new_cus(ActionEvent event) throws IOException {
        try {
            Stage stage1 = (Stage) new_conn_btn.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("new_conn_pane.fxml"));
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

    public void my_acc_pane(ActionEvent event) throws IOException{
        Stage stage1 = (Stage) inbox_btn.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(getClass().getResource("admin_UI.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        new FadeIn(root).play();
    }

    public void inbox(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) my_acc_btn.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(getClass().getResource("inbox_pane.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        new FadeIn(root).play();
    }

    public void bills_pane(ActionEvent event) throws IOException{
        Stage stage1 = (Stage) bills_btn.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(getClass().getResource("bills_pane.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        new FadeIn(root).play();
    }

    public void customer_details(ActionEvent event) throws IOException{
        Stage stage1 = (Stage) customer_deatils_btn.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(getClass().getResource("customer_details.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        new FadeIn(root).play();
    }
    // end

    // logout btn
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
                    root = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
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
    // end

    //   inbox  logic
    public void search_msg(){
        if(count == 0){
            waring_txt_inbox.setText("No Messages!");
        } else {
            if (inbox_search_field.getText().equals("")) {
                waring_txt_inbox.setText("No Name to Search!");
            } else {
                inbox_table.getItems().clear();
                waring_txt_inbox.setText("");
                String name = inbox_search_field.getText();
                String sql_queryByName = "SELECT * FROM inbox WHERE name='" + name + "'";
                try {
                    int i = 0;
                    this.s = this.c.createStatement();
                    this.r = this.s.executeQuery(sql_queryByName);
                    while (r.next()) {
                        list.add(new get_msg_inbox(r.getInt("c_id"), r.getString("name"), r.getString("email"), r.getString("message"), btn[i]));
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                c_id_col.setCellValueFactory(new PropertyValueFactory<>("c_id"));
                name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
                email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
                msg_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMsg()));
                action_col.setCellValueFactory(new PropertyValueFactory<>("btn"));
                if (list.isEmpty()) {
                    waring_txt_inbox.setText("Name does not Exists!");
                }
                inbox_table.setItems(list);
            }
        }
    }

    public void show_msg() {
        if(count == 0){
            waring_txt_inbox.setText("No Messages!");
        } else {
            inbox_table.getItems().clear();
            inbox_search_field.setText("");
            waring_txt_inbox.setText("");
            try {
                this.s = this.c.createStatement();
                String s = "SELECT * FROM inbox";
                this.r = this.s.executeQuery(s);
                int i = 0;
                while (r.next()) {
                    list.add(new get_msg_inbox(r.getInt("c_id"), r.getString("name"), r.getString("email"), r.getString("message"), btn[i]));
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            c_id_col.setCellValueFactory(new PropertyValueFactory<>("c_id"));
            name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            msg_col.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMsg()));
            action_col.setCellValueFactory(new PropertyValueFactory<>("btn"));
            inbox_table.setItems(list);
            for (int i = 0; i < count; i++) {
                btn[i].setOnAction(this::row_del_inbox);
            }
        }
    }

    public void clear_table_inbox(){
        inbox_table.getItems().clear();
        waring_txt_inbox.setText("");
        inbox_search_field.setText("");
    }

    public void row_del_inbox(ActionEvent event) {
        try {
            for (int i = 0; i < count; i++) {
                if (event.getSource() == btn[i]) {
                    inbox_table.getItems().remove(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
//        get_msg_inbox obj = new get_msg_inbox();
//        List<List<String>> arrList = new ArrayList<>();
//        //for(int i = 0;i < inbox_table.getItems().size();i++){
//            obj = inbox_table.getItems().get(0);
//            arrList.add(new ArrayList<>());
//            arrList.get(0).add(String.valueOf(obj.getC_id()));
//        //}
//        for(int i = 0;i < arrList.size();i++){
//            for(int j = 0;j < arrList.get(i).size();j++){
//                System.out.println(arrList.get(i).get(j));
//            }
    //}
    }

    public void del_table_value(){
        if(count == 0){
            waring_txt_inbox.setText("No Messages to Delete!");
        } else {
            count = 0;
            String del = "DELETE FROM inbox";
            ButtonType good_btn = new ButtonType("Delete");
            ButtonType bad_btn = new ButtonType("Cancel");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", good_btn, bad_btn);
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to empty your inbox, messages will be deleted permanently!!!");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.getDialogPane().setStyle("-fx-background-color: #2e9cca; -fx-border-color: #2e9cca; -fx-border-radius: 30; -fx-background-radius: 30; -fx-border-width: 10;");
            alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);
            new FadeIn(alert.getDialogPane()).play();
            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(res -> {
                if (res.equals(good_btn)) {
                    try {
                        this.s = this.c.createStatement();
                        this.s.executeUpdate(del);
                        r.next();
                        waring_txt_inbox.setText("Messages deleted Successfully!");
                        inbox_table.getItems().clear();
                        list = null;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
        }
    }
    // inbox logic end

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img_icon_ebms = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/icon2.png");
        img_icon.setImage(img_icon_ebms);
        Image admin_logo_img = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/admin.png");
        admin_logo.setImage(admin_logo_img);
        Image acc = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/acc.png");
        Image new_conn = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/new_conn.png");
        Image bills = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/bill.png");
        Image cus = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/group.png");
        Image inbox = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/mail.png");
        Image logout = new Image("file:/Users/nithinr/ESMS App/ESMS App admin/src/main/images/logout.png");
        acc_img.setImage(acc);
        new_img.setImage(new_conn);
        bill_img.setImage(bills);
        logout_img.setImage(logout);
        cus_img.setImage(cus);
        inbox_img.setImage(inbox);
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
            String count_string = "SELECT COUNT(c_id) FROM inbox";
            this.s = this.c.createStatement();
            this.r = this.s.executeQuery(count_string);
            r.next();
            count = r.getInt(1);
            this.btn = new Button[count];
            for(int j=0;j < count;j++){
                btn[j] = new Button();
                btn[j].setText("Delete");
                btn[j].setStyle("-fx-background-color: #b33a3a;\n" +
                        "    -fx-text-fill: white;" +
                        "-fx-background-radius: 30;");
            }
        } catch (Exception E) {
            System.out.println(E);
        }
    }
    
}

