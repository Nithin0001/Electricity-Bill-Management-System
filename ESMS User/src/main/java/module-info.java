module com.example.esms_user {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires AnimateFX;


    opens com.example.esms_user to javafx.fxml;
    exports com.example.esms_user;
}