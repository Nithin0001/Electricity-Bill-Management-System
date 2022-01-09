package com.example.esms_user;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML
    private Button exit;

    @FXML
    private TextField adminName_field;

    @FXML
    private Button login_btn;

    @FXML
    private ImageView img_icon, exit_img, login_img;

    @FXML
    private Text waring_text;

    @FXML
    private PasswordField passAdmin_field;

    @FXML
    private TextField text_slide_field;

    @FXML
    private Rectangle rect1, waring_rect;
    private Connection c = null;
    private Statement s;
    private ResultSet r;
    private Stage stage;
    private Scene scene;
    private int count = 0;
    private int count_text_slide = 0;
    private String name_field;
    private String pass_field;
    private int c_id;

    public void login(ActionEvent event) throws SQLException, IOException {
        String sql = "SELECT * FROM user_login";
        s = c.createStatement();
        r = s.executeQuery(sql);
        name_field = adminName_field.getText();
        pass_field = passAdmin_field.getText();
        while (r.next()){
            c_id = r.getInt("c_id");
            String name = r.getString("user_name");
            String password = r.getString("password");
            if(name_field.equals(name) && pass_field.equals(password)){
                log_in(event);
                break;
            }
            else{
                waring_rect.setFill(Color.WHITE);
                waring_rect.setStroke(Color.WHITE);
                waring_text.setText("User Name Or Password is Incorrect!");
            }
        }
    }

    public void log_in(ActionEvent event) {
        try {
            loginController l = new loginController();
            l.init(c_id);
            Stage stage1 = (Stage) login_btn.getScene().getWindow();
            stage1.close();
            Parent root = FXMLLoader.load(getClass().getResource("user_UI.fxml"));
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

    public void exit(){
            ButtonType good_btn = new ButtonType("Quit");
            ButtonType bad_btn = new ButtonType("Cancel");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", good_btn, bad_btn);
            new FadeIn(alert.getDialogPane()).play();
            alert.setTitle("Confirm Exit");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to quit!");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.getDialogPane().setStyle("-fx-background-color: #2e9cca; -fx-border-color: #2e9cca; -fx-border-radius: 30; -fx-background-radius: 30; -fx-border-width: 10;");
            alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);
            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(res -> {
                if (res.equals(good_btn)) {
                    Stage stage1 = (Stage) exit.getScene().getWindow();
                    stage1.close();
                }
            });
    }

    public void slideshow(){
        ArrayList<Image> img = new ArrayList<Image>();
        img.add(new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/silde_show/power-grid.jpg"));
        img.add(new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/silde_show/npower.jpg"));
        img.add(new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/silde_show/dam.jpg"));
        img.add(new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/silde_show/solarfram.jpg"));
        img.add(new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/silde_show/windmill.jpg"));
        img.add(new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/silde_show/smill.jpg"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), event -> {
            rect1.setFill(new ImagePattern(img.get(count)));
            count++;
            if (count == 6) {
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void text_slide(){
        String[] facts = new String[6];
        facts[0] = "Power Grid transmits about 50% of the total power generated in India on its transmission network.";
        facts[1] = "Power Grid's interregional capacity is 75,050 MW.";
        facts[2] = "India has 23 nuclear reactors in operation in 7 nuclear power plants, with a capacity of 7,480MW.";
        facts[3] = "India's hydroelectric power potential is estimated at 148,700 MW at 60% load factor.";
        facts[4] = "There are more than 40 Major Solar power plants in India, which generate at least 10 MW of power.";
        facts[5] = "India's total installed wind power capacity is 38.789 GW, the 4th largest wind power capacity in the world.";
        text_slide_field.setText(facts[0]);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6), event -> {
            text_slide_field.setText(facts[count_text_slide]);
            count_text_slide++;
            if(count_text_slide == 6){
                count_text_slide = 0;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image img_icon_ebms = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/icon (2)-modified.png");
            Image img1 = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/silde_show/power-grid.jpg");
            Image login = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/login.png");
            Image exit = new Image("file:/Users/nithinr/ESMS App/ESMS User/src/main/images/icons/exit.png");
            login_img.setImage(login);
            img_icon.setImage(img_icon_ebms);
            exit_img.setImage(exit);
            rect1.setFill(new ImagePattern(img1));
            slideshow();
            text_slide();
        } catch (Exception e){
            System.out.println(e);
        }
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ESM", "root", "MySQL@123");
        } catch (SQLException e) {
            waring_text.setText("Not Connected to Server Contact the ADMIN");
            System.out.println("error " + e.getMessage());
        }
    }
}