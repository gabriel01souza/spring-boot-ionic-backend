package com.course.coursemc.dto;

import java.io.Serializable;

public class EmailDTO implements Serializable {

    private String email;

    public String EmailDTO(){
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
