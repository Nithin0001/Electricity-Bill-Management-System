package com.example.esms_app_admin;

import java.math.BigDecimal;

public class getCustomerDetails {
    private int c_id;
    private String name;
    private String email;
    private String occ;
    private BigDecimal phone;
    private String dob;
    private int flat_no;
    private String address;
    private int pin_code;
    private String state;
    private String city;

    public getCustomerDetails() {}

    public getCustomerDetails(int c_id, String name, String email, String occ, BigDecimal phone, String dob, int flat_no, String address, int pin_code, String state, String city) {
        this.c_id = c_id;
        this.name = name;
        this.email = email;
        this.occ = occ;
        this.phone = phone;
        this.dob = dob;
        this.flat_no = flat_no;
        this.address = address;
        this.pin_code = pin_code;
        this.state = state;
        this.city = city;
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

    public String getOcc() {
        return occ;
    }

    public BigDecimal getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public int getFlat_no() {
        return flat_no;
    }

    public String getAddress() {
        return address;
    }

    public String getPin_code() {
        return Integer.toString(pin_code);
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

}
