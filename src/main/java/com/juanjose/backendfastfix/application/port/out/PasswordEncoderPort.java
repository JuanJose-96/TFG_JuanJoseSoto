package com.juanjose.backendfastfix.application.port.out;

public interface PasswordEncoderPort {
    String encode(String password);
    boolean matches(String password,String encodedPassword);
}
