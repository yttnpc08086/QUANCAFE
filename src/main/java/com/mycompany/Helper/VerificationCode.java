/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Helper;

/**
 *
 * @author Admin
 */
public class VerificationCode {

    private String code;
    private long timestamp;
    private static final long EXPIRATION_TIME = 60 * 1000; // 1 phút

    public VerificationCode(String code) {
        this.code = code;
        this.timestamp = System.currentTimeMillis();
    }

    public String getCode() {
        return code;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - timestamp > EXPIRATION_TIME;
    }
}
