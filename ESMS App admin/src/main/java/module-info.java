module com.example.esms_app_admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires AnimateFX;


    opens com.example.esms_app_admin to javafx.fxml;
    exports com.example.esms_app_admin;
}