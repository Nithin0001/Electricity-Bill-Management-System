package com.example.esms_app_admin;

import javafx.scene.control.Button;

public class get_msg_inbox {
     private int c_id;
     private String name;
     private String email;
     private String message;
     private Button btn;

    public get_msg_inbox(){
    }

    public get_msg_inbox(int c_id, String name, String email, String message, Button btn) {
        this.c_id = c_id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.btn = btn;
    }

    public int getC_id() {
        return c_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMsg() {
        return message;
    }

    public Button getBtn() {
        return btn;
    }
}
