package com.course.coursemc.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private String fielName;
    private String message;

    public FieldMessage(){
    }

    public FieldMessage(String fielName, String message) {
        super();
        this.fielName = fielName;
        this.message = message;
    }

    public String getFielName() {
        return fielName;
    }

    public void setFielName(String fielName) {
        this.fielName = fielName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFieldName() {
        return fielName;
    }
}
